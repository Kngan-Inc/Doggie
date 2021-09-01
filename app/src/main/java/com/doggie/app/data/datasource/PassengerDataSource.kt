package com.doggie.app.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.doggie.app.data.repository.MainRepository
import com.doggie.app.model.Passenger
import com.doggie.app.util.ResultOf

class PassengerDataSource(
    private var mainRepository: MainRepository
) : PagingSource<Int, Passenger>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Passenger> {
        return try {
            val nextPageNumber = params.key ?: 0
            when (val response =
                mainRepository.getPassenger(size = 10, page = nextPageNumber)) {
                is ResultOf.Success -> {
                    LoadResult.Page(
                        data = response.data.data,
                        prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                        nextKey = if (nextPageNumber < response.data.totalPages) nextPageNumber + 1 else null
                    )
                }
                is ResultOf.Error -> {
                    LoadResult.Error(response.exception)
                }
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Passenger>): Int? {
        TODO("Not yet implemented")
    }
}
