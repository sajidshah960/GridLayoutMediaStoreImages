package com.itverizon.gridlayoutmediastoreimages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    // Create a MutableLiveData with a span count
    private var _spanCount = MutableLiveData<Int>()

    // Create a LiveData with a span count
    val spanCount: LiveData<Int>get() = _spanCount

    // Initialize the span count to 1
    init {
        _spanCount.value = 3
    }

    // Update the span count

    fun updateSpanCount(spanCount: Int) {
        _spanCount.value = spanCount
    }
}