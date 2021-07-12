package com.doggie.app.epoxy.controller

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.doggie.app.epoxy.model.SelectionItemModel_
import com.doggie.app.model.Passenger

class SelectionController : PagedListEpoxyController<Passenger>() {
    override fun buildItemModel(currentPosition: Int, item: Passenger?): EpoxyModel<*> {
        return if (item == null) {
            SelectionItemModel_()
                .id(-currentPosition)
                .title("loading $currentPosition")
                .listener { }
        } else {
            SelectionItemModel_()
                .id(item._id)
                .title(item.airline.logo)
                .listener { }
        }
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        super.addModels(models)
    }

    override fun onExceptionSwallowed(exception: RuntimeException) {
        throw exception
    }
}