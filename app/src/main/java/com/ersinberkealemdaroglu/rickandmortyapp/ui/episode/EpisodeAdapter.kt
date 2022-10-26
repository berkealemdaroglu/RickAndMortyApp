package com.ersinberkealemdaroglu.rickandmortyapp.ui.episode

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.rickandmortyapp.R
import com.ersinberkealemdaroglu.rickandmortyapp.databinding.CharacterDetailEpisodeItemBinding
import com.ersinberkealemdaroglu.rickandmortyapp.ui.state.EpisodeItemUiState
import com.ersinberkealemdaroglu.rickandmortyapp.utils.ext.executeWithAction

class EpisodeAdapter() : PagingDataAdapter<EpisodeItemUiState, EpisodeAdapter.EpisodeAdapterViewHolder>(ComparatorEpisode) {

    inner class EpisodeAdapterViewHolder(private val episodeBinding : CharacterDetailEpisodeItemBinding) : RecyclerView.ViewHolder(episodeBinding.root){
        fun bind(episodeItemUiState: EpisodeItemUiState){
            episodeBinding.executeWithAction {
                this.characterEpisode = episodeItemUiState
            }

            episodeBinding.root.setOnClickListener {
                val action = EpisodeListFragmentDirections.actionEpisodeListFragmentToEpisodeDetailFragment(episodeItemUiState.getAllEpisode())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    object ComparatorEpisode : DiffUtil.ItemCallback<EpisodeItemUiState>(){
        override fun areItemsTheSame(
            oldItem: EpisodeItemUiState,
            newItem: EpisodeItemUiState
        ): Boolean {
            return oldItem.getAllEpisode() == newItem.getAllEpisode()
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: EpisodeItemUiState,
            newItem: EpisodeItemUiState
        ): Boolean {
            return oldItem == newItem
        }


    }

    override fun onBindViewHolder(holder: EpisodeAdapterViewHolder, position: Int) {
        getItem(position)?.let { episodeItemUiState ->
            holder.bind(episodeItemUiState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeAdapterViewHolder {
        val episodeBinding = DataBindingUtil.inflate<CharacterDetailEpisodeItemBinding>(
            LayoutInflater.from(parent.context), R.layout.character_detail_episode_item, parent, false
        )

        return EpisodeAdapterViewHolder(episodeBinding)
    }

}