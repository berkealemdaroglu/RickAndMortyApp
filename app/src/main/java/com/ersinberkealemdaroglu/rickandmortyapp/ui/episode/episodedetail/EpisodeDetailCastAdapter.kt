package com.ersinberkealemdaroglu.rickandmortyapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.rickandmortyapp.R
import com.ersinberkealemdaroglu.rickandmortyapp.databinding.CharacterListItemBinding
import com.ersinberkealemdaroglu.rickandmortyapp.databinding.EpisodeCastItemBinding
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterItem
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterModel
import com.ersinberkealemdaroglu.rickandmortyapp.ui.state.CharacterItemUiState
import com.ersinberkealemdaroglu.rickandmortyapp.utils.CharacterItemOnClickListener
import com.ersinberkealemdaroglu.rickandmortyapp.utils.ext.executeWithAction

class EpisodeDetailCastAdapter :
    PagingDataAdapter<CharacterItemUiState, EpisodeDetailCastAdapter.HomeCharacterListAdapterViewHolder>(
        ComparatorCharacter
    ) {

    private var listData = ArrayList<CharacterModel>()

    fun characterDetailData(){
        for (data in listData){
            for (dataBind in data.results){
                println(dataBind.toString() + "OSMAN")
            }
        }
    }

    class HomeCharacterListAdapterViewHolder(
        private val characterBinding: EpisodeCastItemBinding,
    ) :
        RecyclerView.ViewHolder(characterBinding.root) {
        fun characterBind(characterItemUiState: CharacterItemUiState) {
            characterBinding.executeWithAction {
                this.characterItem = characterItemUiState
            }

            characterBinding.root.rootView.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToCharacterDetailFragment(
                    characterItemUiState.getAllCharacter()
                )
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun onBindViewHolder(holder: HomeCharacterListAdapterViewHolder, position: Int) {
        getItem(position)?.let { userItemUiState -> holder.characterBind(userItemUiState) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeCharacterListAdapterViewHolder {
        val characterBinding = DataBindingUtil.inflate<EpisodeCastItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.episode_cast_item,
            parent,
            false
        )

        return HomeCharacterListAdapterViewHolder(characterBinding)
    }

    object ComparatorCharacter : DiffUtil.ItemCallback<CharacterItemUiState>() {

        override fun areItemsTheSame(
            oldItem: CharacterItemUiState,
            newItem: CharacterItemUiState
        ): Boolean {
            return oldItem.getCharacterId() == newItem.getCharacterId()
        }

        override fun areContentsTheSame(
            oldItem: CharacterItemUiState,
            newItem: CharacterItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setData(characterItem: CharacterModel){
        this.listData.add(characterItem)
        notifyDataSetChanged()
        characterDetailData()
    }
}