package br.com.waldirbaia.buscausuario.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.waldirbaia.buscausuario.R
import br.com.waldirbaia.buscausuario.domain.entity.User
import br.com.waldirbaia.buscausuario.presentation.ui.diff.UserDiff
import br.com.waldirbaia.buscausuario.presentation.ui.listener.UserClickedListener
import coil.load
import com.google.android.material.imageview.ShapeableImageView

class UserAdapter(
    private val listener: UserClickedListener
) : ListAdapter<User, UserAdapter.UserViewHolder>(UserDiff) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val user = getItem(position)

        holder.txtUserName.text = user.login
        holder.txtUserBio.text = user.bio
        holder.txtUserLocation.text = user.location
        holder.txtUserFollowers.text = user.followers.toString()

        holder.imgUserAvatar.load(user.avatar_url){
            crossfade(true)
            placeholder(R.drawable.icon_outline_person)
            error(R.drawable.icon_outline_person)
        }

        holder.itemView.setOnClickListener {
            listener.onUserClicked(user)
        }

    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtUserName: TextView = itemView.findViewById(R.id.txt_card_user_name)
        val txtUserBio: TextView = itemView.findViewById(R.id.txt_card_user_bio)
        val txtUserLocation: TextView = itemView.findViewById(R.id.txt_card_user_location)
        val txtUserFollowers: TextView = itemView.findViewById(R.id.txt_card_user_number_followers)
        val imgUserAvatar: ShapeableImageView = itemView.findViewById(R.id.img_card_user_avatar)
    }

}