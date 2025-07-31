package br.com.waldirbaia.buscausuario.domain.repository

import br.com.waldirbaia.buscausuario.data.remote.ApiService
import br.com.waldirbaia.buscausuario.domain.response.RepositoryResponse
import br.com.waldirbaia.buscausuario.domain.response.RepositorySearchResponse
import br.com.waldirbaia.buscausuario.domain.response.UserResponse
import retrofit2.Response
import javax.inject.Inject

class MainActivityRepository @Inject constructor(
    private val apiService: ApiService
){
    //Remoto
    suspend fun getUser(userName: String): Response<UserResponse>{
        return apiService.getUser(userName)
    }

    suspend fun getRepository(userName: String): Response<List<RepositoryResponse>>{
        return apiService.getRepository(userName)
    }

    suspend fun getRepositoriesByLanguage(name: String): Response<RepositorySearchResponse>{
        return apiService.getRepositoriesByLanguage(name)
    }

    suspend fun getUsersByLocation(name: String): Response<RepositorySearchResponse>{
        return apiService.getUsersByLocation(name)
    }


}