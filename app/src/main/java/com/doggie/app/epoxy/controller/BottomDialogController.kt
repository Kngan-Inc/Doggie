package com.doggie.app.epoxy.controller

import android.content.Context
import androidx.navigation.NavController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.group
import com.doggie.app.R
import com.doggie.app.epoxy.model.DoggieItemModel_
import com.doggie.app.epoxy.model.bottomDialogItem
import com.doggie.app.epoxy.model.doggieItem
import com.doggie.app.epoxy.model.titleView
import java.util.concurrent.CopyOnWriteArrayList

class BottomDialogController(
    private var context: Context,
    private var buttonListener: () -> Unit,
) : EpoxyController() {

    fun submitList(list: ArrayList<Pair<Int, String>>) {
        menus?.clear()
        menus?.addAll(list)
        requestModelBuild()
    }

    private var menus: CopyOnWriteArrayList<Pair<Int, String>>? = CopyOnWriteArrayList(ArrayList())

    override fun buildModels() {
        menus?.map { item ->
            bottomDialogItem {
                id(item.first)
                drawable(item.first)
                title(item.second)
                listener {
                    this@BottomDialogController.buttonListener()
                }
            }
        }
    }
}