package br.com.waldirbaia.buscausuario.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") userName: String
    )
}