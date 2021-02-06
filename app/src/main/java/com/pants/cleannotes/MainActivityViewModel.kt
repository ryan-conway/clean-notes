package com.pants.cleannotes

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    private var _fabClickListener = MutableLiveData<View.OnClickListener?>()
    val fabClickListener: LiveData<View.OnClickListener?>
        get() = _fabClickListener

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun setFabClickListener(listener: View.OnClickListener?) = _fabClickListener.postValue(listener)

    fun setLoading(isLoading: Boolean) = _loading.postValue(isLoading)
}