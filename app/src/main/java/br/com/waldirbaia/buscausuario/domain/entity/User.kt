package br.com.waldirbaia.buscausuario.domain.entity

data class User(
    val id: Int? = 0,
    val login: String? = "",
    val bio: String? = "",
    val location: String? = "",
    val followers: Int? = 0,
    val avatar_url: String? = "",
    val repos_url: String? = ""
)
