package com.doggie.app.epoxy.model

import android.view.View
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.doggie.app.R
import com.doggie.app.databinding.ComponentDoggieBinding

@EpoxyModelClass(layout = R.layout.component_doggie)
abstract class DoggieItemModel : EpoxyModelWithHolder<DoggieItemModel.SelectionHolder>() {

    @field:EpoxyAttribute
    var url: String? = null

    @field:EpoxyAttribute
    var doggieName: String? = null

    @EpoxyAttribute
    lateinit var listener: () -> Unit

    @EpoxyAttribute
    lateinit var buttonListener: () -> Unit

    @EpoxyAttribute
    lateinit var removeListener: () -> Unit

    override fun bind(holder: SelectionHolder) {
        super.bind(holder)
        holder.binding.coilImageView
            .load(url) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
                error(R.drawable.ic_launcher_foreground)
            }
        holder.binding.doggieNameTextView.text = doggieName
        holder.binding.root.setOnClickListener { listener() }
        holder.binding.buttonDoggie.setOnClickListener { buttonListener() }
        holder.binding.buttonRemove.setOnClickListener{ removeListener() }
    }

    override fun shouldSaveViewState(): Boolean = true

    class SelectionHolder : EpoxyHolder() {
        lateinit var binding: ComponentDoggieBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentDoggieBinding.bind(itemView)
        }
    }

}