package io.henrikhorbovyi.data.di

import android.location.Geocoder
import io.henrikhorbovyi.data.repository.HostRepository
import io.henrikhorbovyi.data.repository.RealHostRepository
import io.henrikhorbovyi.data.source.local.AppDatabase
import io.henrikhorbovyi.data.source.local.HostDao
import io.henrikhorbovyi.data.source.local.entity.LocationClient
import io.henrikhorbovyi.data.source.remote.HostService
import io.henrikhorbovyi.data.source.remote.ServiceBuilder
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    // GeoCoder
    factory<Geocoder> { Geocoder(androidContext()) }
    factory<LocationClient> { LocationClient(get()) }

    // Database
    single { AppDatabase.getInstance(androidApplication()) }
    factory<HostDao> { get<AppDatabase>().hostDao() }

    // Remote Service
    single<HostService> { ServiceBuilder(baseUrl = "http://10.0.2.2:8080") }

    // HostRepository
    factory<HostRepository> { RealHostRepository(get(), get(), get()) }
}