package com.ersinberkealemdaroglu.rickandmortyapp.ui.episode.episodedetail

import android.content.pm.LauncherActivityInfo
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.ersinberkealemdaroglu.rickandmortyapp.R
import com.ersinberkealemdaroglu.rickandmortyapp.databinding.FragmentEpisodeDetailBinding
import com.ersinberkealemdaroglu.rickandmortyapp.databinding.FragmentEpisodeListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment : Fragment() {
    private lateinit var episodeDetailBinding: FragmentEpisodeDetailBinding
    private val navArgs : EpisodeDetailFragmentArgs by navArgs()
    private lateinit var episodeDetailCastAdapter: EpisodeDetailCastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        episodeDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_episode_detail, container, false)
        return episodeDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setEpisodeDetailItem()
    }

    private fun init(){
        episodeDetailCastAdapter = EpisodeDetailCastAdapter()
        episodeDetailBinding.episodeDetailRecyclerview.layoutManager = GridLayoutManager(context, 2)
        episodeDetailBinding.episodeDetailRecyclerview.adapter = episodeDetailCastAdapter
    }

    private fun setEpisodeDetailItem(){
        navArgs.let { episodeData ->
            this.episodeDetailBinding.episodeDetailItem = episodeData.episodeItem
        }


    }

}