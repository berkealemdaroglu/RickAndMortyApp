package com.ersinberkealemdaroglu.rickandmortyapp.ui.home.homedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.ersinberkealemdaroglu.rickandmortyapp.R
import com.ersinberkealemdaroglu.rickandmortyapp.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private lateinit var detailBinding: FragmentCharacterDetailBinding
    private val detailFragmentArgs : CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_detail, container, false)
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setNavArgs()
    }

    private fun init(){

    }

    private fun setNavArgs(){
        detailFragmentArgs.let {
            detailBinding.characterItem = it.characterItem
        }
    }

}