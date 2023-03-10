package com.example.webservicesdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webservicesdemo.databinding.UserViewBinding

class UsersAdapter(var users : ArrayList<User>) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userViewBinding : UserViewBinding = UserViewBinding.bind(itemView)
    }

    override fun getItemCount(): Int {
       return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
       return UsersViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.user_view,null)
       )
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        var eachUser = users[position]
        holder.userViewBinding.txtId.text = "${eachUser.id}"
        holder.userViewBinding.txtEmail.text = "${eachUser.email}"
        holder.userViewBinding.txtFirstName.text = "${eachUser.first_name}"
        holder.userViewBinding.txtLastName.text = "${eachUser.last_name}"
        Glide.with(holder.userViewBinding.root)
            .load(users[position].avatar)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(holder.userViewBinding.imgUser)
    }
}