package com.example.viewbindingdemo.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewbindingdemo.DetailActivity
import com.example.viewbindingdemo.databinding.ItemContactBinding
import com.example.viewbindingdemo.model.Contact
import com.example.viewbindingdemo.R

class ContactAdapter(private val contacts: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        val context = holder.itemView.context

        holder.binding.apply {
            tvName.text = contact.name
            tvPhone.text = contact.phone
            imgAvatar.setImageResource(contact.avatarRes)

            root.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("name", contact.name)
                    putExtra("phone", contact.phone)
                    putExtra("email", "${contact.name.replace(" ", ".").lowercase()}@gmail.com")
                    putExtra("avatar", contact.avatarRes)
                }
                context.startActivity(intent)
                (context as Activity).overridePendingTransition(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
            }
        }
    }

    override fun getItemCount() = contacts.size
}
