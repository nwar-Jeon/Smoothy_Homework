package com.nwar.smoothy_homework.presenter.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.nwar.smoothy_homework.R
import com.nwar.smoothy_homework.app.scheduler.AndroidSchedulerStorage
import com.nwar.smoothy_homework.domain.entity.SearchImage
import com.nwar.smoothy_homework.domain.usecase.FavoriteClickUseCase
import com.nwar.smoothy_homework.domain.usecase.SaveImageUseCase
import com.nwar.smoothy_homework.domain.usecase.SearchImageUseCase
import io.reactivex.rxjava3.subscribers.DisposableSubscriber

class MainFragmentViewModel : BaseViewModel() {

    private val searchImageUseCase = SearchImageUseCase(AndroidSchedulerStorage)
    private val favoriteUseCase = FavoriteClickUseCase(AndroidSchedulerStorage)
    private val saveImageUseCase = SaveImageUseCase(AndroidSchedulerStorage)

    val searchText = MutableLiveData<String>()

    val searchListLiveData by lazy { MutableLiveData<MutableList<ObservableField<SearchImage>>>(searchList) }
    val searchList = mutableListOf<ObservableField<SearchImage>>()

    override fun registerUseCase() {
        registerUseCases(favoriteUseCase, saveImageUseCase, searchImageUseCase)
    }

    fun requestSearch(page : Int) {
        searchList.clear()
        val text = if(searchText.value.isNullOrEmpty()) "random Image" else searchText.value!!
        searchImageUseCase.excute(page to text, searchResultSubscriber)
    }

    fun requestShowDetail(pos : Int) {
        val item = searchList[pos].get() ?: return
        saveImageUseCase.excute(item, saveImageSubscriber)
    }

    override fun onResume() {
        super.onResume()
        requestSearch(1)
    }

    fun toggleFavorite(position : Int) {
        val image = searchList[position].get() ?: return
        favoriteUseCase.excute(position to image, favoriteClickSubscriber)
    }

    private val searchResultSubscriber get() = object : DisposableSubscriber<SearchImage>() {
        override fun onComplete() { searchListLiveData.postValue(searchList) }

        override fun onError(t: Throwable?) { toast.postValue(t?.message) }

        override fun onNext(t: SearchImage?) { if (t != null) searchList.add(ObservableField(t)) }
    }

    private val favoriteClickSubscriber get() = object : DisposableSubscriber<Int>() {
        override fun onNext(t: Int?) {
            t ?: return
            searchList[t].get()?.toggleFavorite()
            searchList[t].notifyChange()
        }

        override fun onComplete() {}

        override fun onError(t: Throwable?) { toast.postValue(t?.message) }
    }

    private val saveImageSubscriber get() = object : DisposableSubscriber<Unit>() {
        override fun onComplete() { nextFragment.postValue(R.id.action_mainFragment_to_detailImageFragment) }

        override fun onNext(t: Unit?) {}

        override fun onError(t: Throwable?) { toast.postValue(t?.message) }
    }
}