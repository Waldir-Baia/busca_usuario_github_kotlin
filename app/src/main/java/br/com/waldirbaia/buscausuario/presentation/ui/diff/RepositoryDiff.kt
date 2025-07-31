package br.com.waldirbaia.buscausuario.presentation.ui.diff

import androidx.recyclerview.widget.DiffUtil
import br.com.waldirbaia.buscausuario.domain.entity.Repository

object RepositoryDiff : DiffUtil.ItemCallback<Repository>(){
    override fun areItemsTheSame(
        oldItem: Repository,
        newItem: Repository
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Repository,
        newItem: Repository
    ): Boolean {
        return oldItem == newItem
    }
}