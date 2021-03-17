package br.monteoliva.testeretrofit

import android.app.Application

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

import br.monteoliva.testeretrofit.repository.core.modules.networkModule
import br.monteoliva.testeretrofit.repository.core.modules.viewModelModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            loadKoinModules(listOf(networkModule, viewModelModule))
        }
    }
}