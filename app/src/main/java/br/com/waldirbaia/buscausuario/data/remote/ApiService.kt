package br.com.waldirbaia.buscausuario.data.remote

import br.com.waldirbaia.buscausuario.domain.response.RepositoryResponse
import br.com.waldirbaia.buscausuario.domain.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{username}")
    suspend fun getUser(@Path("username") userName: String) : Response<UserResponse>

    @GET("users/{username}/repos")
    suspend fun getRepository(@Path("username") userName: String ) : Response<List<RepositoryResponse>>
}