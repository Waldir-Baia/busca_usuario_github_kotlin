package br.com.waldirbaia.buscausuario.presentation.ui.diff

import androidx.recyclerview.widget.DiffUtil
import br.com.waldirbaia.buscausuario.domain.entity.HistoryUser

object HistoryDiff : DiffUtil.ItemCallback<HistoryUser>(){
    override fun areItemsTheSame(
        oldItem: HistoryUser,
        newItem: HistoryUser
    ): Boolean {
        return oldItem.idUser == newItem.idUser
    }

    override fun areContentsTheSame(
        oldItem: HistoryUser,
        newItem: HistoryUser
    ): Boolean {
        return oldItem == newItem
    }
}