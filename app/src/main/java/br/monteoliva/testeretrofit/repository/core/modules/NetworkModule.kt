package br.monteoliva.testeretrofit.repository.core.modules

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

import br.monteoliva.testeretrofit.repository.core.OkHttp3
import br.monteoliva.testeretrofit.repository.core.preferences.Preferences
import br.monteoliva.testeretrofit.repository.core.preferences.SharedPreferencesImpl
import br.monteoliva.testeretrofit.repository.core.RepositoryServer
import br.monteoliva.testeretrofit.repository.api.ApiService
import br.monteoliva.testeretrofit.repository.core.RetrofitMobile
import br.monteoliva.testeretrofit.repository.core.ServiceInterceptor

var networkModule = module {
    single<Preferences> { SharedPreferencesImpl(androidContext()) }
    single { RetrofitMobile(get()) }
    single {(get() as retrofit2.Retrofit).create(ApiService::class.java) }

    factory { OkHttp3(get()) }
    factory { ServiceInterceptor() }
    factory { RepositoryServer(get()) }
}