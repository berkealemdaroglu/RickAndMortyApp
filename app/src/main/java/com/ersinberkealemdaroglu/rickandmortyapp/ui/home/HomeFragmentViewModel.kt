package com.ersinberkealemdaroglu.rickandmortyapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterItem
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterModel
import com.ersinberkealemdaroglu.rickandmortyapp.domain.usecase.CharacterUseCase
import com.ersinberkealemdaroglu.rickandmortyapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val characterUseCase: CharacterUseCase) : ViewModel() {

    private val _characterData = MutableLiveData<List<CharacterItem>>()
    val characterData : LiveData<List<CharacterItem>> = _characterData

    private val _loadingData = MutableLiveData<Boolean>()
    val loadingData : LiveData<Boolean> = _loadingData


    init {
        getCharacterData()
    }

    private fun getCharacterData(page : Int? = null){
        _loadingData.postValue(true)
        viewModelScope.launch {
            val result = characterUseCase.getCharacter(page)
            when(result.status){
                Status.SUCCESS -> {
                    _characterData.value = result.data!!.results
                    _loadingData.postValue(false)
                }
                Status.ERROR -> {
                    println("ERROR!!")
                    _loadingData.postValue(true)
                }
                else -> {}
            }

        }
    }
}