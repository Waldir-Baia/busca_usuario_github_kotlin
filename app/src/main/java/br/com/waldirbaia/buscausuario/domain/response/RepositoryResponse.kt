package br.com.waldirbaia.buscausuario.domain.response

data class RepositoryResponse(
    val id: Int? = 0,
    val name: String? = "",
    val full_name: String? = "",
    val html_url: String? = "",
    val description: String? = ""
)
