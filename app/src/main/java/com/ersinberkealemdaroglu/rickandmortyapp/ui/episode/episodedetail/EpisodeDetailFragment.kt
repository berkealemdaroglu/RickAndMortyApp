package com.ersinberkealemdaroglu.rickandmortyapp.ui.episode.episodedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.ersinberkealemdaroglu.rickandmortyapp.R
import com.ersinberkealemdaroglu.rickandmortyapp.databinding.FragmentEpisodeDetailBinding
import com.ersinberkealemdaroglu.rickandmortyapp.ui.home.EpisodeDetailCastAdapter
import com.ersinberkealemdaroglu.rickandmortyapp.ui.home.HomeCharacterListAdapter
import com.ersinberkealemdaroglu.rickandmortyapp.ui.home.HomeFragmentViewModel
import com.ersinberkealemdaroglu.rickandmortyapp.ui.state.CharacterItemUiState
import com.ersinberkealemdaroglu.rickandmortyapp.ui.state.CharacterUiState
import com.ersinberkealemdaroglu.rickandmortyapp.utils.ext.collect
import com.ersinberkealemdaroglu.rickandmortyapp.utils.ext.collectLast
import com.ersinberkealemdaroglu.rickandmortyapp.utils.ext.executeWithAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeDetailFragment : Fragment() {
    private lateinit var episodeDetailBinding: FragmentEpisodeDetailBinding
    private val navArgs: EpisodeDetailFragmentArgs by navArgs()
    private lateinit var episodeDetailCastAdapter : EpisodeDetailCastAdapter
    private val episodeDetailViewModel: EpisodeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //collectLast(episodeDetailViewModel, ::setUser)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        episodeDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_episode_detail, container, false)
        return episodeDetailBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setEpisodeDetailItem()
        setAdapter()
        setData()
    }

    private fun setEpisodeDetailItem() {
        navArgs.let { episodeData ->
            this.episodeDetailBinding.episodeDetailItem = episodeData.episodeItem
        }
    }

    private fun init() {
        episodeDetailCastAdapter = EpisodeDetailCastAdapter()
        episodeDetailCastAdapter.characterDetailData()
        episodeDetailBinding.episodeDetailRecyclerview.layoutManager = GridLayoutManager(context, 2)
    }

    private fun setAdapter() {
        collect(flow = episodeDetailCastAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setUsersUiState
        )

        episodeDetailBinding.episodeDetailRecyclerview.adapter = episodeDetailCastAdapter
    }

    private fun setUsersUiState(loadState: LoadState) {
        episodeDetailBinding.executeWithAction {
            characterVisibility = CharacterUiState(loadState)
        }
    }


    private suspend fun setUser(userItemsPagingData: PagingData<CharacterItemUiState>) {
        episodeDetailCastAdapter.submitData(userItemsPagingData)
    }

    private fun setData(){
        episodeDetailCastAdapter.characterDetailData()
        episodeDetailViewModel.data.observe(viewLifecycleOwner){ data ->
            episodeDetailCastAdapter.setData(data)
            CoroutineScope(Dispatchers.IO).launch {
                for (datas in data.results){
                    episodeDetailViewModel.setData(datas.id)
                }
            }
        }
    }



}