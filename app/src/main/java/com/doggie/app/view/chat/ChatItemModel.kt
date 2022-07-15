package com.doggie.app.view.chat

import android.view.View
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.doggie.app.R
import com.doggie.app.databinding.ComponentChatItemBinding

@EpoxyModelClass
abstract class ChatItemModel : EpoxyModelWithHolder<ChatItemModel.ChatItemViewHolder>() {

    @field:EpoxyAttribute
    var url: String? = null

    @field:EpoxyAttribute
    var doggieName: String? = null

    @EpoxyAttribute
    lateinit var listener: () -> Unit

    @EpoxyAttribute
    var selected: Boolean = false


    override fun getDefaultLayout(): Int = R.layout.component_chat_item

    override fun bind(holder: ChatItemViewHolder) {
        super.bind(holder)
        holder.binding.doggieImageView.load(url) {
            placeholder(R.mipmap.ic_launcher_round)
        }
        holder.binding.doggieNameTextView.text = doggieName
        holder.binding.root.setOnClickListener {
            listener.invoke()
        }
        if(selected) {
            holder.binding.root.setBackgroundResource(R.drawable.border_doggie)
        } else {
            holder.binding.root.setBackgroundResource(R.drawable.non_border_doggie)
        }
    }

    inner class ChatItemViewHolder : EpoxyHolder() {
        lateinit var binding: ComponentChatItemBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentChatItemBinding.bind(itemView)
        }
    }

}