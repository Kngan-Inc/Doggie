package com.doggie.app.epoxy.controller

import androidx.navigation.NavController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.doggie.app.PagingViewModel_
import com.doggie.app.epoxy.model.SelectionItemModel_
import com.doggie.app.epoxy.model.selectionItem
import com.doggie.app.http.User
import com.doggie.app.model.Dog
import com.doggie.app.pagingView
import com.doggie.app.view.SearchViewModel
import java.lang.RuntimeException
import java.util.concurrent.CopyOnWriteArrayList

class SelectionController(
    private val navController: NavController,
    private val viewModel: SearchViewModel

) : PagedListEpoxyController<User>() {
    override fun buildItemModel(currentPosition: Int, item: User?): EpoxyModel<*> {
        return if (item == null) {
            SelectionItemModel_()
                .id(-currentPosition)
                .title("loading $currentPosition")
                .listener {  }
        } else {
            SelectionItemModel_()
                .id(item.uid)
                .title("${item.uid}: ${item.firstName} / ${item.lastName}")
                .listener {  }
        }
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        pagingView {
            id("header")
            name("showing ${models.size} items")
        }
        super.addModels(models)
    }

    override fun onExceptionSwallowed(exception: RuntimeException) {
        throw exception
    }
}