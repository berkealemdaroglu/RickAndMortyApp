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
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterItem
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterModel
import com.ersinberkealemdaroglu.rickandmortyapp.ui.CharacterItemUiState
import com.ersinberkealemdaroglu.rickandmortyapp.utils.CharacterItemOnClickListener
import com.ersinberkealemdaroglu.rickandmortyapp.utils.ext.executeWithAction

class HomeCharacterListAdapter :
    PagingDataAdapter<CharacterItemUiState, HomeCharacterListAdapter.HomeCharacterListAdapterViewHolder>(
        Comparator
    ) {
    private val characterModel: CharacterModel = CharacterModel(null, listOf())
    private var characterItemOnClickListener: CharacterItemOnClickListener? = null

    class HomeCharacterListAdapterViewHolder(
        private val characterBinding: CharacterListItemBinding,
    ) :
        RecyclerView.ViewHolder(characterBinding.root) {
        fun characterBind(characterItemUiState: CharacterItemUiState) {
            characterBinding.executeWithAction {
                this.characterItems = characterItemUiState
            }

            characterBinding.root.rootView.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToCharacterDetailFragment(characterItemUiState.getAllCharacter())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    fun setCharacterItemOnClickListener(characterItemOnClick: CharacterItemOnClickListener) {
        this.characterItemOnClickListener = characterItemOnClick
    }

    override fun onBindViewHolder(holder: HomeCharacterListAdapterViewHolder, position: Int) {
        getItem(position)?.let { userItemUiState -> holder.characterBind(userItemUiState) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeCharacterListAdapterViewHolder {
        val characterBinding = DataBindingUtil.inflate<CharacterListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.character_list_item,
            parent,
            false
        )

        return HomeCharacterListAdapterViewHolder(characterBinding)
    }

    object Comparator : DiffUtil.ItemCallback<CharacterItemUiState>() {
        override fun areItemsTheSame(oldItem: CharacterItemUiState, newItem: CharacterItemUiState): Boolean {
            return oldItem.getCharacterImage() == newItem.getCharacterImage()
        }

        override fun areContentsTheSame(
            oldItem: CharacterItemUiState,
            newItem: CharacterItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }
}