package com.example.disneyapp.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disneyapp.data.model.DisneyData
import com.example.disneyapp.data.repository.DisneyRepository
import com.example.disneyapp.util.ResponseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisneyViewModel @Inject constructor(
    private val disneyRepository: DisneyRepository
) :ViewModel(){

    var disneyObject: DisneyData? = null

    private val _result = MutableLiveData<ResponseType<List<DisneyData>>>()
    val result: LiveData<ResponseType<List<DisneyData>>> = _result

    fun flowCharacters() {
        viewModelScope.launch {
            disneyRepository.getCharactersFlow().collect {
                _result.postValue(it)
            }
        }
    }

}