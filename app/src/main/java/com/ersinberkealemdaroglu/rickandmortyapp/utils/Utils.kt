package com.ersinberkealemdaroglu.rickandmortyapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ersinberkealemdaroglu.rickandmortyapp.R

fun ImageView.apiDownloadFromUri(url: String?) {

    url.let {
        Glide.with(context)
            .load(url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
            )
            .into(this)
    }
}

@BindingAdapter("android:downloadImageUrl")
fun downloadImage(view: ImageView, url: String?) {
    view.apiDownloadFromUri(url)
}