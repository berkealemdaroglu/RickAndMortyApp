package com.ersinberkealemdaroglu.rickandmortyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ersinberkealemdaroglu.rickandmortyapp.R
import com.ersinberkealemdaroglu.rickandmortyapp.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private lateinit var detailBinding: FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_detail, container, false)
        return detailBinding.root
    }

}