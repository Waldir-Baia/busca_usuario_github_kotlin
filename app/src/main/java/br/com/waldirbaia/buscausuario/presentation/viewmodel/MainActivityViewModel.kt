package br.com.waldirbaia.buscausuario.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.waldirbaia.buscausuario.data.local.interfac.HistoryRepository
import br.com.waldirbaia.buscausuario.domain.TipoFiltro
import br.com.waldirbaia.buscausuario.domain.entity.HistoryUser
import br.com.waldirbaia.buscausuario.domain.entity.Repository
import br.com.waldirbaia.buscausuario.domain.entity.User
import br.com.waldirbaia.buscausuario.domain.repository.MainActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: MainActivityRepository,
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    private val _repositoryEntity = MutableLiveData<List<Repository>>()
    val repositoryEntity: LiveData<List<Repository>> = _repositoryEntity

    private val _history = MutableLiveData<List<HistoryUser>>()
    val history: LiveData<List<HistoryUser>> = _history

    private val _filtroSelecionado = MutableLiveData<TipoFiltro>(TipoFiltro.USUARIO)
    val filtroSelecionado: LiveData<TipoFiltro> = _filtroSelecionado

    private val _erro = MutableLiveData<String?>()
    val erro: LiveData<String?> = _erro

    init {

    }

    fun preparedDataa(search: String) {
        viewModelScope.launch {
            val tipoFiltro = _filtroSelecionado.value ?: TipoFiltro.USUARIO

            when (tipoFiltro) {
                TipoFiltro.USUARIO -> {
                    _user.value = importUser(search)
                    _repositoryEntity.value = importRepository(search)
                }

                TipoFiltro.LINGUAGEM -> {

                }

                TipoFiltro.SEGUIDORES -> {

                }

                TipoFiltro.LOCALIZACAO -> {

                }

                TipoFiltro.REPOSITORIOS -> {

                }
            }
        }
    }

    fun preparedData(userName: String) {
        viewModelScope.launch {
            importUser(userName)
            _repositoryEntity.value = importRepository(userName)
        }
    }

    fun atualizaFiltroSelecionado(tipo: TipoFiltro) {
        _filtroSelecionado.value = tipo

    }

    private fun addHistory(user: User) {
        val history = HistoryUser(
            idUser = user.id,
            user = user.login,
            avatarUrl = user.avatar_url,
            dataHoraPesquisa = Date()
        )
        historyRepository.saveUserToHistory(history)
        _history.value = historyRepository.getGistory()
    }

    private suspend fun importUser(userName: String): User? {
        return try {
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
                    addHistory(user)
                    return user
                }
            } else {
                Log.e("ImportUser", "Erro: ${response.code()} - ${response.message()}")
            }
            null
        } catch (e: Exception) {
            Log.e("ImportUser", "Exceção ao importar usuário", e)
            null
        }
    }

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