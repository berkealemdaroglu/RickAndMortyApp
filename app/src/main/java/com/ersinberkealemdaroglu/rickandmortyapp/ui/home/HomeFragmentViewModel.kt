package com.ersinberkealemdaroglu.rickandmortyapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.ersinberkealemdaroglu.rickandmortyapp.domain.repository.CharacterRepository
import com.ersinberkealemdaroglu.rickandmortyapp.ui.state.CharacterItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {

    val userItemsUiStates = characterRepository.getCharacter()
        .map { pagingData ->
            pagingData.map { userModel ->
                CharacterItemUiState(userModel)
            }
        }.cachedIn(viewModelScope)
}
