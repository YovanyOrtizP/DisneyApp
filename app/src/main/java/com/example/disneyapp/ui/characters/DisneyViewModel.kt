package com.example.disneyapp.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disneyapp.data.model.DisneyResponse
import com.example.disneyapp.data.repository.DisneyRepository
import com.example.disneyapp.util.ResponseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisneyViewModel @Inject constructor(
    private val disneyRepository: DisneyRepository
) :ViewModel(){

    var disneyObject: DisneyResponse? = null

    private val _result = MutableLiveData<ResponseType>()
    val result: LiveData<ResponseType> = _result

    fun getCharacter(){
        viewModelScope.launch {
            _result.postValue(ResponseType.LOADING)
            val response = disneyRepository.getCharacters()
            if (response.isSuccessful){
                _result.postValue(ResponseType.SUCCESS(response.body()!!))
            }else{
                _result.postValue(ResponseType.ERROR(response.message()))
            }
        }
    }
}