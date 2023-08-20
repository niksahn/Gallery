package ru.sakhno.gallery.data.models

import com.google.gson.annotations.SerializedName


data class Topic_submissions (

	@SerializedName("wallpapers") val wallpapers : Wallpapers
)