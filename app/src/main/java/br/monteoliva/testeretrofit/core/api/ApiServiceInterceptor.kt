package br.monteoliva.testeretrofit.core.api

import okhttp3.Interceptor
import okhttp3.Response

import br.monteoliva.testeretrofit.core.Constants

class ApiServiceInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val requestBuilder = request.newBuilder()
            .header("token", Constants.API_TOKEN)

        request = requestBuilder.build()
        return chain.proceed(request)
    }
}
