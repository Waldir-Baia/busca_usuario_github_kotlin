package br.com.waldirbaia.buscausuario.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.waldirbaia.buscausuario.R
import br.com.waldirbaia.buscausuario.domain.entity.HistoryUser
import br.com.waldirbaia.buscausuario.presentation.ui.diff.HistoryDiff
import br.com.waldirbaia.buscausuario.presentation.ui.listener.HistoryClickedListener
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import java.text.SimpleDateFormat
import java.util.Locale

class HistoryAdapter(
    private val listener: HistoryClickedListener
) : ListAdapter<HistoryUser, HistoryAdapter.HistoryViewHolder>(HistoryDiff){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HistoryViewHolder,
        position: Int
    ) {
        val history = getItem(position)

        val formatData = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val dataFormatada = formatData.format(history.dataHoraPesquisa)

        holder.txtUserName.text = history.user
        holder.txtUserDataHora.text = dataFormatada

        holder.imgUserAvatar.load(history.avatarUrl){
            crossfade(true)
            placeholder(R.drawable.icon_outline_person)
            error(R.drawable.icon_outline_person)
        }

        holder.itemView.setOnClickListener {
            listener.onHistoryClicked(history)
        }

    }


    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtUserName: TextView = itemView.findViewById(R.id.txt_card_history_name)
        val txtUserDataHora: TextView = itemView.findViewById(R.id.txt_card_history_data_hora)
        val imgUserAvatar: ShapeableImageView = itemView.findViewById(R.id.img_card_history_avatar)
    }
}