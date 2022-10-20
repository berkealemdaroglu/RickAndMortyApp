package com.ersinberkealemdaroglu.rickandmortyapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.rickandmortyapp.BR
import com.ersinberkealemdaroglu.rickandmortyapp.R
import com.ersinberkealemdaroglu.rickandmortyapp.databinding.CharacterListItemBinding
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterItem
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterModel

class HomeCharacterListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var characterBinding: ViewDataBinding
    private val characterModel: CharacterModel = CharacterModel(null,listOf())

   class HomeCharacterListAdapterViewHolder(private val characterBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(characterBinding.root) {
        fun characterBind(characterItem: CharacterItem) {
            characterBinding as CharacterListItemBinding
            characterBinding.setVariable(BR.characterItem, characterItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        characterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.character_list_item,
            parent,
            false
        )

        return HomeCharacterListAdapterViewHolder(characterBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeCharacterListAdapterViewHolder).characterBind(characterModel.results[position])
    }

    override fun getItemCount(): Int {
        return characterModel.results.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCharacterData(characterItem: List<CharacterItem>){
        this.characterModel.results = characterItem
        notifyDataSetChanged()
    }
}