package com.doggie.app.view.chat.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.doggie.app.R
import com.doggie.app.model.Dog
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView


class ChatAdapter : ListAdapter<Dog, ChatAdapter.ItemViewHolder>(ChatDiff()) {

    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var dogName: MaterialTextView = itemView.findViewById(R.id.doggieNameTextView)
        private var dogImage: ShapeableImageView = itemView.findViewById(R.id.doggieImageView)
        private var root: LinearLayout = itemView.findViewById(R.id.root)

        @SuppressLint("SetTextI18n")
        fun bind(item: Dog, isActivated: Boolean = false) = with(itemView) {
            dogImage.load(item.dogProfile)
            dogName.text = "Dog ${layoutPosition + 1}"
            itemView.isSelected = isActivated
            if (isActivated) {
                dogName.setTextColor(context.getColor(R.color.white))
                root.animate().duration = 6
                root.setBackgroundResource(R.drawable.border_doggie)
            } else {
                dogName.setTextColor(context.getColor(R.color.black))
                root.setBackgroundResource(R.drawable.non_border_doggie)
            }
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> = object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = layoutPosition
                override fun getSelectionKey(): Long = itemId
                override fun inSelectionHotspot(e: MotionEvent): Boolean {
                    return true
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.component_chat_item, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item = item)
        tracker?.let { holder.bind(item, it.isSelected(position.toLong())) }

    }

    class ChatDiff : DiffUtil.ItemCallback<Dog>() {
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem == newItem
        }
    }
}