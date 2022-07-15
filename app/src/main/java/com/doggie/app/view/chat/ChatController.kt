package com.doggie.app.view.chat

import com.airbnb.epoxy.EpoxyController
import com.doggie.app.model.Dog
import java.util.concurrent.CopyOnWriteArrayList

class ChatController(
    private val onSelectedChangeListener: (Dog) -> Unit
) : EpoxyController() {

    private var doggies: CopyOnWriteArrayList<Dog> = CopyOnWriteArrayList()

    fun submitList(list: ArrayList<Dog>) {
        doggies.clear()
        doggies.addAll(list)
        requestModelBuild()
    }

    override fun buildModels() {
        if (doggies.isNotEmpty()) {
            doggies.forEachIndexed { index, doggie ->
                chatItem {
                    id("chat_id_$doggie")
                    url(doggie.dogProfile)
                    selected(doggie.selected)
                    doggieName("Doggie ${index + 1}")
                    listener {
                        doggie.selected = !doggie.selected
                        this@ChatController.onSelectedChangeListener(doggie)
                    }
                }
            }
        }
    }
}