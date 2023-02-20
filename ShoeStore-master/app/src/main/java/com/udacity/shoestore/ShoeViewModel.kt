package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {
    private val _shoeListItem = MutableLiveData<MutableList<Shoe>>()
    val shoeListItem: LiveData<MutableList<Shoe>> get() = _shoeListItem
    init {
        Timber.i("Initial")
        _shoeListItem.value = mutableListOf()
        addShoe(Shoe("sss", 1.7, "ww", "jjj"))
        addShoe(Shoe("sss", 1.7, "ww", "jjj"))
    }
    fun addShoe(shoe: Shoe) {
        Timber.i(shoe.name)
        _shoeListItem.value?.add(shoe)
    }
}