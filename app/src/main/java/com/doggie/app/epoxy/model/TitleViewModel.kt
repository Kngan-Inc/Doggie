package com.doggie.app.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.doggie.app.R
import com.doggie.app.databinding.ComponentTitleBinding

@EpoxyModelClass(layout = R.layout.component_title)
abstract class TitleViewModel : EpoxyModelWithHolder<TitleViewModel.TitleViewHolder>() {

    @field:EpoxyAttribute
    var text: String? = null

    override fun bind(holder: TitleViewHolder) {
        super.bind(holder)
        holder.binding.titleTextView.text = text
    }

    class TitleViewHolder : EpoxyHolder() {
        lateinit var binding: ComponentTitleBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentTitleBinding.bind(itemView)
        }
    }
}