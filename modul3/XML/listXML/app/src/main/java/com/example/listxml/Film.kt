package com.example.listxml
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film (
    val title : String? = null,
    val year : String? = null,
    val desc : String? = null,
    val image : Int = 0,
    val imdb : String = "",
    val detail : String = "", // URL for the detail page
): Parcelable
