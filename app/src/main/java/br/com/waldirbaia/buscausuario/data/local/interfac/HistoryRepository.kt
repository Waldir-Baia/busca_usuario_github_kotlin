package br.com.waldirbaia.buscausuario.data.local.interfac

import br.com.waldirbaia.buscausuario.domain.entity.HistoryUser

interface HistoryRepository {
    fun saveUserToHistory(user: HistoryUser)
    fun getGistory(): List<HistoryUser>
    fun clearHistory()
}