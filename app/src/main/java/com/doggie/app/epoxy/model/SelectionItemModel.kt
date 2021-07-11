package com.doggie.app.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.doggie.app.R
import com.doggie.app.databinding.ComponentSelectionBinding


@EpoxyModelClass(layout = R.layout.component_selection)
abstract class SelectionItemModel : EpoxyModelWithHolder<SelectionItemModel.SelectionHolder>() {

    @field:EpoxyAttribute
    var title: String? = null

    @EpoxyAttribute
    lateinit var listener: () -> Unit

    override fun bind(holder: SelectionHolder) {
        super.bind(holder)
        Glide.with(holder.binding.imageView)
            .load(title)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.binding.imageView)

        holder.binding.root.setOnClickListener { listener() }
    }

    class SelectionHolder : EpoxyHolder() {
        lateinit var binding: ComponentSelectionBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentSelectionBinding.bind(itemView)
        }
    }
}