package io.henrikhorbovyi.ishelteryou

import android.app.Application
import io.henrikhorbovyi.data.di.dataModule
import io.henrikhorbovyi.ishelteryou.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        injectionDependencySetup()
    }

    private fun injectionDependencySetup() {
        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@App)
            modules(appModule, dataModule)
        }
    }
}
