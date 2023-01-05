package com.example.learningblibli.feature_search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.learningblibli.core.base.BaseViewModel
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.usecase.contract.SearchMealUseCase
import com.example.learningblibli.lib_model.model.Meal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor (private val searchMealUseCase: SearchMealUseCase) : BaseViewModel() {

    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> = _meals


    fun searchMeals(name:String){
        setLoading(true)
        val result = searchMealUseCase(name)

            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                when(it){
                    is Resource.Success->{
                        it.data?.let {meals->
                            _meals.postValue(meals)
                        }
                        setLoading(false)
                    }
                    is Resource.Error->{
                        showError(it.message?:"Something wrong with server")
                        setLoading(false)
                    }
                    else -> {
                        setLoading(false)
                    }
                }

            }

        addDisposable(result)

    }
}