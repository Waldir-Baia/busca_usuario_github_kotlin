package br.com.waldirbaia.buscausuario.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.waldirbaia.buscausuario.data.local.interfac.HistoryRepository
import br.com.waldirbaia.buscausuario.domain.entity.HistoryUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private val _historyUser = MutableLiveData<List<HistoryUser>>()
    val historyUser: LiveData<List<HistoryUser>> = _historyUser


    init {
        _historyUser.value = historyRepository.getGistory()
    }

}