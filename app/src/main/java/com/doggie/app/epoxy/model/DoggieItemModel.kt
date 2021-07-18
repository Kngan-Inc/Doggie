package com.doggie.app.epoxy.model

import android.widget.LinearLayout
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.doggie.app.R
import com.doggie.app.util.KotlinEpoxyHolder
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

@EpoxyModelClass(layout = R.layout.component_doggie)
abstract class DoggieItemModel : EpoxyModelWithHolder<DoggieItemModel.SelectionHolder>() {

    @field:EpoxyAttribute
    var url: String? = null

    @field:EpoxyAttribute
    var doggieName: String? = null

    @EpoxyAttribute
    lateinit var listener: () -> Unit

    override fun bind(holder: SelectionHolder) {
        super.bind(holder)
        holder.coilImageView
            .load(url) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
                error(R.drawable.ic_launcher_foreground)
            }
        holder.doggieName.text = doggieName
        holder.root.setOnClickListener { listener() }
    }

    override fun shouldSaveViewState(): Boolean = true

    class SelectionHolder : KotlinEpoxyHolder() {
        val coilImageView by bind<ShapeableImageView>(R.id.coilImageView)
        val doggieName by bind<MaterialTextView>(R.id.doggieNameTextView)
        val root by bind<LinearLayout>(R.id.root)
    }
}