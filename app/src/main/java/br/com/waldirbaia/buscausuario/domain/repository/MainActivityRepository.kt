package br.com.waldirbaia.buscausuario.domain.repository

import br.com.waldirbaia.buscausuario.data.remote.ApiService
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


}