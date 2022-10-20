package com.ersinberkealemdaroglu.rickandmortyapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersinberkealemdaroglu.rickandmortyapp.R
import com.ersinberkealemdaroglu.rickandmortyapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding
    private val homeFragmentViewModel : HomeFragmentViewModel by viewModels()
    private lateinit var homeCharacterListAdapter : HomeCharacterListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observeCharacterItem()
        loadingDataProgressBar()
    }

    private fun init(){
        homeCharacterListAdapter = HomeCharacterListAdapter()
        homeBinding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        homeBinding.recyclerView.adapter = homeCharacterListAdapter
    }

    private fun observeCharacterItem(){
        homeFragmentViewModel.characterData.observe(viewLifecycleOwner){ data ->
            homeCharacterListAdapter.setCharacterData(data)
        }
    }

    private fun loadingDataProgressBar(){
        homeFragmentViewModel.loadingData.observe(viewLifecycleOwner){ isLoading ->
            isLoading.let {
            homeBinding.apply {
                if (it){
                    recyclerView.visibility = View.INVISIBLE
                    progressBar.visibility = View.VISIBLE
                }else{
                    recyclerView.visibility = View.VISIBLE
                    progressBar.visibility = View.INVISIBLE
                }
            }
            }
        }
    }

}