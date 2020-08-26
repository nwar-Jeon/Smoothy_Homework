package com.nwar.smoothy_homework.datasource

import androidx.paging.PageKeyedDataSource
import com.nwar.smoothy_homework.domain.entity.SearchImage

class SearchImageDataSource(val loadData : (Int) -> Unit): PageKeyedDataSource<Int, SearchImage>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, SearchImage>
    ) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, SearchImage>) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, SearchImage>) {
        TODO("Not yet implemented")
    }

    fun changeItem(items : List<SearchImage>) {
    }
}