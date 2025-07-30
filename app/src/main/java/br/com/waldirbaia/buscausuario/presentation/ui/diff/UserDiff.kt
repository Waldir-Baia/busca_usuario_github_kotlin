package br.com.waldirbaia.buscausuario.presentation.ui.diff

import androidx.recyclerview.widget.DiffUtil
import br.com.waldirbaia.buscausuario.domain.entity.User

object UserDiff : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(
        oldItem: User,
        newItem: User
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: User,
        newItem: User
    ): Boolean {
        return oldItem == newItem
    }
}