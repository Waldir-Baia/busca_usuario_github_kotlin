package br.com.waldirbaia.buscausuario.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.waldirbaia.buscausuario.data.local.interfac.HistoryRepository
import br.com.waldirbaia.buscausuario.domain.enums.TipoFiltro
import br.com.waldirbaia.buscausuario.domain.entity.HistoryUser
import br.com.waldirbaia.buscausuario.domain.entity.Repository
import br.com.waldirbaia.buscausuario.domain.entity.User
import br.com.waldirbaia.buscausuario.domain.repository.MainActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: MainActivityRepository,
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private val _user = MutableLiveData<List<User?>>()
    val user: LiveData<List<User?>> = _user

    private val _userSelected = MutableLiveData<User?>()
    val userSelected: LiveData<User?> = _userSelected

    private val _listUser = MutableLiveData<List<User?>>()
    val listUser: LiveData<List<User?>> = _listUser

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
    fun setUserSelected(user: User?){
        _userSelected.value = user

    }

    fun preparedDataa(search: String) {
        viewModelScope.launch {
            val tipoFiltro = _filtroSelecionado.value ?: TipoFiltro.USUARIO

            when (tipoFiltro) {
                TipoFiltro.USUARIO -> {
                    val user = importUser(search)
                    _user.value = listOf(user)
                   // _repositoryEntity.value = importRepository(search)
                }

                TipoFiltro.LINGUAGEM -> {
                    _user.value = importUsersByLanguage(search)
                }

                TipoFiltro.SEGUIDORES -> {

                }

                TipoFiltro.LOCALIZACAO -> {
                    _user.value = importUsersByLocation(search)
                }

                TipoFiltro.REPOSITORIOS -> {

                }
            }
        }
    }

    fun preparedData(userName: String) {
        viewModelScope.launch {
            importUser(userName)
            importRepository(userName)
        }
    }

    fun setTipoEnum(tipo: TipoFiltro) {
        _filtroSelecionado.value = tipo

    }

    fun addHistory(user: User) {
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
                    _user.value = listOf(user)
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
    fun importRepository(userName: String) {
        viewModelScope.launch {
            try {
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
                        _repositoryEntity.value = listRepository
                    } ?: run {
                        _repositoryEntity.value = emptyList()
                    }
                } else {
                    _erro.postValue(response.errorBody()?.string() ?: "Erro desconhecido")
                    _repositoryEntity.value = emptyList()
                }
            } catch (e: Exception) {
                _erro.postValue(e.message ?: "Erro inesperado")
                _repositoryEntity.value = emptyList()
            }
        }
    }
    private suspend fun importUsersByLanguage(name:String) : List<User?>{
        return try {
            val response = repository.getRepositoriesByLanguage(name)
            if (response.isSuccessful){
                response.body()?.let { result ->
                    val listRepository = result.items.map { users ->
                        User(
                            id = users.id,
                            login = users.login,
                            bio = users.bio,
                            location = users.location,
                            followers = users.followers,
                            avatar_url = users.avatar_url,
                            repos_url = users.repos_url
                        )
                    }
                    return listRepository
                }
            }else{
                _erro.value = response.errorBody()?.string() ?: "Erro desconhecido"
            }
            emptyList()
        }catch (e: Exception){
            _erro.value = e.toString()
            emptyList()
        }
    }

    private suspend fun importUsersByLocation(name:String) : List<User?>{
        return try {
            val response = repository.getUsersByLocation(name)
            if (response.isSuccessful){
                response.body()?.let { result ->
                    val listRepository = result.items.map { users ->
                        User(
                            id = users.id,
                            login = users.login,
                            bio = users.bio,
                            location = users.location,
                            followers = users.followers,
                            avatar_url = users.avatar_url,
                            repos_url = users.repos_url
                        )
                    }
                    return listRepository
                }
            }else{
                _erro.value = response.errorBody()?.string() ?: "Erro desconhecido"
            }
            emptyList()
        }catch (e: Exception){
            _erro.value = e.toString()
            emptyList()
        }
    }

}