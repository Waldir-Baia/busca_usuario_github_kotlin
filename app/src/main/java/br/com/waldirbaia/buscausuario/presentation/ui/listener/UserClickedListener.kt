package br.com.waldirbaia.buscausuario.presentation.ui.listener

import br.com.waldirbaia.buscausuario.domain.entity.User

interface UserClickedListener {
    fun onUserClicked(viewUser: User)
}