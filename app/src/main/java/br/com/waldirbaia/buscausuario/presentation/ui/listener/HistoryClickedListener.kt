package br.com.waldirbaia.buscausuario.presentation.ui.listener

import br.com.waldirbaia.buscausuario.domain.entity.HistoryUser

interface HistoryClickedListener {
    fun onHistoryClicked(viewHistory: HistoryUser)
}