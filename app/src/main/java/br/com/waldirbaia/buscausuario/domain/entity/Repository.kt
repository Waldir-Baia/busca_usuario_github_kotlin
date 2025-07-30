package br.com.waldirbaia.buscausuario.domain.entity

data class Repository(
    val id: Int? = 0,
    val name: String? = "",
    val full_name: String? = "",
    val html_url: String? = "",
    val description: String? = ""
)
