package br.com.waldirbaia.buscausuario.presentation.viewmodel

import androidx.lifecycle.ViewModel
import br.com.waldirbaia.buscausuario.domain.repository.MainActivityRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val repository: MainActivityRepository
) : ViewModel() {



}