package br.com.waldirbaia.buscausuario.data.remote

import br.com.waldirbaia.buscausuario.domain.response.RepositoryResponse
import br.com.waldirbaia.buscausuario.domain.response.RepositorySearchResponse
import br.com.waldirbaia.buscausuario.domain.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users/{username}")
    suspend fun getUser(@Path("username") userName: String) : Response<UserResponse>

    @GET("users/{username}/repos")
    suspend fun getRepository(@Path("username") userName: String ) : Response<List<RepositoryResponse>>

    @GET("search/repositories")
    suspend fun getRepositoriesByLanguage(
        @Query("q") query: String,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): Response<RepositorySearchResponse>

    @GET("search/users")
    suspend fun getUsersByLocation(
        @Query("q") query: String,
        @Query("sort") sort: String = "followers", // ou repositories
        @Query("order") order: String = "desc"
    ): Response<RepositorySearchResponse>

}