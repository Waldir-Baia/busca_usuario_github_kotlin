package br.com.waldirbaia.buscausuario.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.waldirbaia.buscausuario.R
import br.com.waldirbaia.buscausuario.domain.entity.Repository
import br.com.waldirbaia.buscausuario.presentation.ui.diff.RepositoryDiff
import br.com.waldirbaia.buscausuario.presentation.ui.listener.RepositoryClickedListener

class RepositoryAdapter(
    private val listener: RepositoryClickedListener
) : ListAdapter<Repository, RepositoryAdapter.RepositoryViewHolder>(RepositoryDiff){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RepositoryViewHolder,
        position: Int
    ) {
        val repository = getItem(position)

        holder.txtRepositoryName.text = repository.name
        holder.txtRepositoryDescription.text = repository.description

        holder.itemView.setOnClickListener {
            listener.onRepositoryClicked(repository)
        }

    }

    class RepositoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtRepositoryName: TextView = itemView.findViewById(R.id.txt_card_repository_name)
        val txtRepositoryDescription: TextView = itemView.findViewById(R.id.txt_card_repository_description)
    }
}