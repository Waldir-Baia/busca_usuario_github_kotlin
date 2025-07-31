package br.com.waldirbaia.buscausuario.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.waldirbaia.buscausuario.domain.entity.Repository
import br.com.waldirbaia.buscausuario.domain.entity.User
import br.com.waldirbaia.buscausuario.domain.repository.MainActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: MainActivityRepository
) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    private val _repositoryEntity = MutableLiveData<List<Repository>>()
    val repositoryEntity: LiveData<List<Repository>> = _repositoryEntity

    private val _erro = MutableLiveData<String?>()
    val erro: LiveData<String?> = _erro

    init {

    }

    fun preparedData(userName: String){
        viewModelScope.launch {
            importUser(userName)
            _repositoryEntity.value =  importRepository(userName)
        }
    }

    private suspend fun importUser(userName: String) {
        val response = repository.getUser(userName)
        if (response.isSuccessful) {
            val result = response.body()
            if (result != null) {
                val user = User(
                    id = result.id,
                    login = result.login,
                    bio = result.bio,
                    location = result.location,
                    followers = result.followers,
                    avatar_url = result.avatar_url,
                    repos_url = result.repos_url
                )
                _user.value = user
            }
        } else {
            Log.e("ImportUser", "Erro: ${response.code()} - ${response.message()}")
        }
    }

    //Neste dois metodos de importação eu mudei a forma, um vai receber o valor diretamente dentro do metodo depois que faço o mapeamento.
    // Já neste segundo eu já declarei ele falando que ele vai me retornar uma lista de repository

    private suspend fun importRepository(userName: String): List<Repository> {
        return try {
            val response = repository.getRepository(userName)

            if (response.isSuccessful) {
                response.body()?.let { responseList ->
                    val listRepository = responseList.map { repoResponse ->
                        Repository(
                            id = repoResponse.id,
                            name = repoResponse.name,
                            full_name = repoResponse.full_name,
                            html_url = repoResponse.html_url,
                            description = repoResponse.description
                        )
                    }
                    return listRepository
                }
            } else {
                _erro.value = response.errorBody()?.string() ?: "Erro desconhecido"
            }
            emptyList()
        } catch (e: Exception) {
            _erro.value = e.message ?: "Erro inesperado"
            emptyList()
        }
    }

}