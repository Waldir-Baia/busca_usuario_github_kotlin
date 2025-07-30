package br.com.waldirbaia.buscausuario.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _erro = MutableLiveData<String?>()
    val erro: LiveData<String?> = _erro

    init {
        viewModelScope.launch {
            importUser()
        }
    }

    private suspend fun importUser() {
        val response = repository.getUser("octocat")
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



}