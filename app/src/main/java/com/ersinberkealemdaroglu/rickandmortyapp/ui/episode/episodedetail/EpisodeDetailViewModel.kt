package com.ersinberkealemdaroglu.rickandmortyapp.ui.episode.episodedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterItem
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterModel
import com.ersinberkealemdaroglu.rickandmortyapp.domain.repository.CharacterRepository
import com.ersinberkealemdaroglu.rickandmortyapp.domain.repository.EpisodeRepository
import com.ersinberkealemdaroglu.rickandmortyapp.ui.state.CharacterItemUiState
import com.ersinberkealemdaroglu.rickandmortyapp.ui.state.EpisodeItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {
        private val _data = MutableLiveData<CharacterModel>()
    val data : LiveData<CharacterModel> = _data

    init {
    }
    suspend fun setData(id: Int){
        characterRepository.getCharacterId(id).enqueue(object : Callback<CharacterModel>{
            override fun onResponse(
                call: Call<CharacterModel>,
                response: Response<CharacterModel>
            ) {
                _data.value = response.body()
            }

            override fun onFailure(call: Call<CharacterModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }


    }