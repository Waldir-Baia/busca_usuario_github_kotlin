package br.com.waldirbaia.buscausuario.domain.entity

import java.util.Date

data class HistoryUser(
    val idUser: Int? = 0,
    val user: String? = "",
    val avatarUrl: String? = "",
    val dataHoraPesquisa: Date? = null
)
