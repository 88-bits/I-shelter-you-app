package io.henrikhorbovyi.data.source.remote

import io.henrikhorbovyi.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface ServiceBuilder {

    companion object {
        inline operator fun <reified S> invoke(baseUrl: String): S {
            val httpClient = buildInterceptors()
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient)
                .build()
                .create(S::class.java)
        }

        fun buildInterceptors(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(getLoggerInterceptor())
                .build()
        }

        private fun getLoggerInterceptor(): HttpLoggingInterceptor {
            val level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
            return HttpLoggingInterceptor().apply { this.level = level }
        }
    }
}
