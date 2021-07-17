package com.doggie.app.epoxy.controller

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.group
import com.doggie.app.R
import com.doggie.app.epoxy.model.DoggieItemModel_
import com.doggie.app.epoxy.model.doggieItem
import com.doggie.app.epoxy.model.titleView
import java.util.concurrent.CopyOnWriteArrayList

class DoggieController(
    private var context: Context
) : EpoxyController() {

    fun submitList(list: Collection<String>) {
        doggies.clear()
        doggies.addAll(list)
        requestModelBuild()
    }

    private val doggies: CopyOnWriteArrayList<String> = CopyOnWriteArrayList()

    override fun buildModels() {
        val models: ArrayList<DoggieItemModel_> = ArrayList()

        if (doggies.isNotEmpty()) {

            doggies.map {
                models.add(
                    DoggieItemModel_()
                        .id(it)
                        .url(it)
                )
            }
            group {
                id("group")
                layout(R.layout.group_layout)
                carousel {
                    id("carousel")
                    models(models)
                    numViewsToShowOnScreen(2f)
                    paddingDp(4)
                }
                spanSizeOverride { _, _, _ -> 2 }
            }

            titleView {
                id("top_suggestion_title")
                spanSizeOverride { _, _, _ -> 1 }
                text(this@DoggieController.context.getString(R.string.top_suggestion))
                spanSizeOverride { _, _, _ -> 2 }
            }

            doggies.map {
                doggieItem {
                    id(it)
                    url(it)
                    spanSizeOverride { _, _, _ -> 1 }
                }
            }
        }
    }
}