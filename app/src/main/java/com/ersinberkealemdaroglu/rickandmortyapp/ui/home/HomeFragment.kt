package com.ersinberkealemdaroglu.rickandmortyapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.ersinberkealemdaroglu.rickandmortyapp.R
import com.ersinberkealemdaroglu.rickandmortyapp.databinding.FragmentHomeBinding
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterItem
import com.ersinberkealemdaroglu.rickandmortyapp.ui.CharacterItemUiState
import com.ersinberkealemdaroglu.rickandmortyapp.ui.CharacterUiState
import com.ersinberkealemdaroglu.rickandmortyapp.utils.CharacterItemOnClickListener
import com.ersinberkealemdaroglu.rickandmortyapp.utils.ext.collect
import com.ersinberkealemdaroglu.rickandmortyapp.utils.ext.collectLast
import com.ersinberkealemdaroglu.rickandmortyapp.utils.ext.executeWithAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()
    private lateinit var homeCharacterListAdapter: HomeCharacterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        collectLast(homeFragmentViewModel.userItemsUiStates, ::setUsers)
    }

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
        setAdapter()
    }

    private fun init() {
        homeCharacterListAdapter = HomeCharacterListAdapter()
        homeBinding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        homeBinding.recyclerView.adapter = homeCharacterListAdapter
    }
    private fun setAdapter() {
        collect(flow = homeCharacterListAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setUsersUiState
        )
        //homeBinding.progressBar.visibility = View.INVISIBLE
        homeBinding.recyclerView.adapter = homeCharacterListAdapter
    }

    private fun setUsersUiState(loadState: LoadState) {
        homeBinding.executeWithAction {
            characterUiState = CharacterUiState(loadState)
        }
    }

    private fun observeCharacterItem() {

/*        homeCharacterListAdapter.setCharacterItemOnClickListener(object :
            CharacterItemOnClickListener {
            override fun characterOnClickListener(characterItem: CharacterItem?) {
                val action = characterItem?.let {
                    HomeFragmentDirections.actionHomeFragmentToCharacterDetailFragment(it)
                }
                findNavController().navigate(action!!)
            }
        })*/
    }

    private fun loadingDataProgressBar() {
        homeFragmentViewModel.loadingData.observe(viewLifecycleOwner) { isLoading ->
            isLoading.let {
                homeBinding.apply {
                    if (it) {
                        recyclerView.visibility = View.INVISIBLE
                        progressBar.visibility = View.VISIBLE
                    } else {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }


    private suspend fun setUsers(userItemsPagingData: PagingData<CharacterItemUiState>) {
        homeCharacterListAdapter.submitData(userItemsPagingData)
    }

}