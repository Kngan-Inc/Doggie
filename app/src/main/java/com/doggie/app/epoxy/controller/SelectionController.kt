package com.doggie.app.epoxy.controller

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.group
import com.doggie.app.R
import com.doggie.app.epoxy.model.DoggieItemModel_
import com.doggie.app.epoxy.model.doggieItem
import java.util.concurrent.CopyOnWriteArrayList

class SelectionController : EpoxyController() {

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
            }

            doggies.map {
                doggieItem {
                    id(it)
                    url(it)
                }
            }
        }


    }
}