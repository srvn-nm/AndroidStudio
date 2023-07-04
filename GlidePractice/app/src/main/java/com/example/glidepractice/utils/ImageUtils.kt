package com.example.glidepractice.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@Composable
fun loadPictures(url: String, @DrawableRes defaltImage: Int): MutableState<Bitmap?> {
    val bitmapState: MutableState<Bitmap?> = remember{mutableStateOf(null)}

    //Downloading the default image
    Glide.with(LocalContext.current).asBitmap().load(defaltImage)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })


    //Downloading the real image

    return bitmapState
}