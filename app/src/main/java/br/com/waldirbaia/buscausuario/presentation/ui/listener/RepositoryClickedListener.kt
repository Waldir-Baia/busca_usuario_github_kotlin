package br.com.waldirbaia.buscausuario.presentation.ui.listener

import br.com.waldirbaia.buscausuario.domain.entity.Repository

interface RepositoryClickedListener {
    fun onRepositoryClicked(viewRepository: Repository)
}