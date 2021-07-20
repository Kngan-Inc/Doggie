package com.doggie.app.epoxy.model

import com.doggie.app.databinding.ComponentBottomDialogItemBinding

import android.view.View
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.doggie.app.R

@EpoxyModelClass(layout = R.layout.component_bottom_dialog_item)
abstract class BottomDialogItemModel : EpoxyModelWithHolder<BottomDialogItemModel.BottomItemHolder>() {

    @field:EpoxyAttribute
    var drawable: Int? = null

    @field:EpoxyAttribute
    var title: String? = null

    @EpoxyAttribute
    lateinit var listener: () -> Unit

    override fun bind(holder: BottomItemHolder) {
        super.bind(holder)
        holder.binding.optionImageView.load(drawable!!)
        holder.binding.optionTextView.text = title
        holder.binding.root.setOnClickListener { listener() }
    }

    override fun shouldSaveViewState(): Boolean = true

    class BottomItemHolder : EpoxyHolder() {
        lateinit var binding: ComponentBottomDialogItemBinding

        override fun bindView(itemView: View) {
            binding = ComponentBottomDialogItemBinding.bind(itemView)
        }
    }

}