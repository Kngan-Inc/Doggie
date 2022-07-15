package com.doggie.app.epoxy.controller

import android.content.Context
import androidx.navigation.NavController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.group
import com.doggie.app.R
import com.doggie.app.epoxy.model.DoggieItemModel_
import com.doggie.app.epoxy.model.doggieItem
import com.doggie.app.epoxy.model.titleView
import com.doggie.app.model.Dog
import java.util.concurrent.CopyOnWriteArrayList

class DoggieController(
    private var context: Context,
    private var navController: NavController,
    private var buttonListener: (Dog) -> Unit,
    private var removeListener: (Dog) -> Unit,
) : EpoxyController() {

    fun submitList(list: ArrayList<Dog>) {
        doggies.clear()
        doggies.addAll(list)
        requestModelBuild()
    }

    private var doggies: CopyOnWriteArrayList<Dog> = CopyOnWriteArrayList()

    override fun buildModels() {

        val models: ArrayList<DoggieItemModel_> = ArrayList()

        if (doggies.isNotEmpty()) {
            doggies.forEachIndexed{ index, item ->
                models.add(
                    DoggieItemModel_()
                        .id("carousel_item_id_$index")
                        .url(item.dogProfile)
                        .doggieName("${this@DoggieController.context.getString(R.string.dog)} ${index + 1}")
                        .listener {
                            this@DoggieController.navController.navigate(R.id.action_searchFragment2_to_searchOneFragment)
                        }
                        .buttonListener {
                            this@DoggieController.buttonListener(item)
                        }
                        .removeListener {
                            doggies.remove(item)
                            this@DoggieController.removeListener(item)
                            requestModelBuild()
                        }
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
                shouldSaveViewState(true)
                spanSizeOverride { _, _, _ -> 2 }
            }

            titleView {
                id("top_suggestion_title")
                spanSizeOverride { _, _, _ -> 1 }
                text(this@DoggieController.context.getString(R.string.top_suggestion))
                spanSizeOverride { _, _, _ -> 2 }
            }

            doggies.forEachIndexed { index, item ->
                doggieItem {
                    id("grid_id_$index")
                    url(item.dogProfile)
                    doggieName("${this@DoggieController.context.getString(R.string.dog)} ${index+ 1}")
                    spanSizeOverride { _, _, _ -> 1 }
                    listener {
                        this@DoggieController.navController.navigate(R.id.action_searchFragment2_to_searchOneFragment)
                    }
                    buttonListener {
                        this@DoggieController.buttonListener(item)
                    }
                    removeListener {
                        this@DoggieController.doggies.remove(item)
                        this@DoggieController.requestModelBuild()
                    }
                }
            }
        }
    }
}