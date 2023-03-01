package com.example.rickandmortyapp.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.repository.Repository
import com.example.rickandmortyapp.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlideshowViewModel @Inject constructor(
    private val repository: Repository,
    private val mainDispatcher: CoroutineDispatcher
): ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is slideshow Fragment"
//    }
//    val text: LiveData<String> = _text

    private val _dataList = MutableLiveData<UIState>(UIState.Empty)
    val dataList: LiveData<UIState> = _dataList

    fun getLocationList() {

        viewModelScope.launch(mainDispatcher) {
            _dataList.value = UIState.Loading
            val result = repository.getAllLocations()
            repository.rmData.collect{
                if(it is UIState.Success<*>){
                    _dataList.value = UIState.Success(result.body()!!)
                }
                else{
                    _dataList.value = UIState.Failure("Location not found!")
                }
            }
        }
    }
}