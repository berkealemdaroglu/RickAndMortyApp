package com.ersinberkealemdaroglu.rickandmortyapp.ui.episode.episodedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.rickandmortyapp.R
import com.ersinberkealemdaroglu.rickandmortyapp.databinding.EpisodeCastItemBinding
import com.ersinberkealemdaroglu.rickandmortyapp.ui.state.CharacterItemUiState
import com.ersinberkealemdaroglu.rickandmortyapp.ui.state.EpisodeItemUiState
import com.ersinberkealemdaroglu.rickandmortyapp.utils.ext.executeWithAction

class EpisodeDetailCastAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val episodeItem = ArrayList<CharacterItemUiState>()
    private lateinit var binding: EpisodeCastItemBinding

    class EpisodeCastAdapterViewHolder(private val episodeCastBinding: EpisodeCastItemBinding) :
        RecyclerView.ViewHolder(episodeCastBinding.root) {
        fun bind(episodeItemUiState: CharacterItemUiState) {
            episodeCastBinding.executeWithAction {
                this.characterItem = episodeItemUiState
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.episode_cast_item,
            parent,
            false)
        return EpisodeCastAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as EpisodeCastAdapterViewHolder).bind(episodeItem[position])
    }

    override fun getItemCount(): Int {
        return episodeItem.size
    }

    fun setEpisodeCast(episodeItemUiState: CharacterItemUiState) {
        this.episodeItem.add(episodeItemUiState)
        notifyDataSetChanged()
    }
}