package io.fajarca.project.movie.util

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import io.fajarca.project.movie.R

internal fun ImageView.loadImage(imageUrl: String) {
    if (imageUrl.isEmpty()) return
    Glide.with(this.context)
        .load(imageUrl.createImageUrl())

        .transition(DrawableTransitionOptions.withCrossFade())
        .thumbnail(MovieConstant.GLIDE_THUMBNAIL_SIZE_MULTIPLIER)
        .apply(RequestOptions.fitCenterTransform())
        .into(this)
}

private fun String.createImageUrl() = MovieConstant.IMAGE_BASE_URL_POSTER + this
private fun String.createImageUrlBackdrop() = MovieConstant.IMAGE_BASE_URL_BACKDROP + this