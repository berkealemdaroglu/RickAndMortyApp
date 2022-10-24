package com.ersinberkealemdaroglu.rickandmortyapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.ersinberkealemdaroglu.rickandmortyapp.data.ApiService
import com.ersinberkealemdaroglu.rickandmortyapp.data.CharacterPagingSource
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterItem
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterModel
import com.ersinberkealemdaroglu.rickandmortyapp.domain.repository.CharacterRepository
import com.ersinberkealemdaroglu.rickandmortyapp.domain.usecase.CharacterUseCase
import com.ersinberkealemdaroglu.rickandmortyapp.ui.CharacterItemUiState
import com.ersinberkealemdaroglu.rickandmortyapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val characterRepository: CharacterRepository) : ViewModel() {

/*    private val _characterData = MutableLiveData<Flow<PagingData<CharacterItem>>>()
    val characterData: LiveData<Flow<PagingData<CharacterItem>>> = _characterData
*/
    private val _loadingData = MutableLiveData<Boolean>(false)
    val loadingData: LiveData<Boolean> = _loadingData


            val userItemsUiStates = characterRepository.getCharacter()
                .map { pagingData ->
                    pagingData.map { userModel -> CharacterItemUiState(userModel)
                    }
                }.cachedIn(viewModelScope)

    init {
        //getCharacterData()
    }
/*
    fun getCharacterData() : Flow<PagingData<CharacterItem>>{
        _loadingData.postValue(true)
            return Pager (config = PagingConfig(pageSize = 1, maxSize = 12),
                pagingSourceFactory = {CharacterPagingSource(apiService)}).flow.cachedIn(viewModelScope)
        }*/

}
