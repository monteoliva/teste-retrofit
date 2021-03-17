package br.monteoliva.testeretrofit.repository.core

import okhttp3.OkHttpClient

object OkHttp3 {
    operator fun invoke(interceptor: ServiceInterceptor): OkHttpClient{
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .build()
    }
}
