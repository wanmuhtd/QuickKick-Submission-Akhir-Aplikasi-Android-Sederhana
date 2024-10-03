package com.dicoding.quickkick

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shoes(
    val name: String, val type: String, val price: String, val color: String, val description: String, val photo: Int
) : Parcelable
