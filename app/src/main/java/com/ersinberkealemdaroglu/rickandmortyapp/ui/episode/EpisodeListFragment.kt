package com.ersinberkealemdaroglu.rickandmortyapp.ui.episode

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.ersinberkealemdaroglu.rickandmortyapp.R
import com.ersinberkealemdaroglu.rickandmortyapp.databinding.FragmentEpisodeListBinding
import com.ersinberkealemdaroglu.rickandmortyapp.ui.state.EpisodeItemUiState
import com.ersinberkealemdaroglu.rickandmortyapp.ui.state.EpisodeUiState
import com.ersinberkealemdaroglu.rickandmortyapp.utils.ext.collect
import com.ersinberkealemdaroglu.rickandmortyapp.utils.ext.collectLast
import com.ersinberkealemdaroglu.rickandmortyapp.utils.ext.executeWithAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class EpisodeListFragment : Fragment() {
    private lateinit var episodeBinding: FragmentEpisodeListBinding
    private val episodeListViewModel: EpisodeListViewModel by viewModels()
    private lateinit var episodeAdapter: EpisodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        collectLast(episodeListViewModel.episodeItemUiState, ::setEpisode)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        episodeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_episode_list, container, false)
        return episodeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setAdapter()
    }

    private fun init() {
        episodeAdapter = EpisodeAdapter()
        episodeBinding.episodeRecyclerview.layoutManager = GridLayoutManager(context, 3)
    }

    private fun setAdapter() {
        collect(flow = episodeAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setUsersUiState
        )


        episodeBinding.episodeRecyclerview.adapter = episodeAdapter

    }

    private fun setUsersUiState(loadState: LoadState) {
        episodeBinding.executeWithAction {
            episodeUiState = EpisodeUiState(loadState)
        }
    }

    private suspend fun setEpisode(episodeItem: PagingData<EpisodeItemUiState>) {
        episodeAdapter.submitData(episodeItem)
    }

}