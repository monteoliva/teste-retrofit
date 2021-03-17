package br.monteoliva.testeretrofit.core.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

import br.monteoliva.testeretrofit.model.contracts.balance.Balance
import br.monteoliva.testeretrofit.model.contracts.detail.Detail
import br.monteoliva.testeretrofit.model.contracts.statements.Statements

interface ApiService {
    @GET("/myBalance")
    fun getBalance() : Call<Balance>

    @GET("/myStatement/{limit}/{offset}")
    fun getStatement(@Path("limit") limit: Int,
                     @Path("offset") offset : Int) : Call<Statements>

    @GET("/myStatement/detail/{id}")
    fun getDetail(@Path("id") id: String) : Call<Detail>
}