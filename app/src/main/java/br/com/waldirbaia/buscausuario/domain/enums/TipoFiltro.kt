package br.com.waldirbaia.buscausuario.domain.enums

enum class TipoFiltro(val descricao: String) {
    LOCALIZACAO("Buscar por Localização"),
    LINGUAGEM("Buscar por Linguagem"),
    SEGUIDORES("Buscar por Seguidores"),
    REPOSITORIOS("Buscar por Repositorio"),
    USUARIO("Buscar por Usuário")
}