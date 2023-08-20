package ru.sakhno.gallery.data.models

import com.google.gson.annotations.SerializedName


data class Wallpapers (
	
	@SerializedName("status") val status : String,
	@SerializedName("approved_on") val approved_on : String
)