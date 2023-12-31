package ru.sakhno.gallery.data.models

import com.google.gson.annotations.SerializedName


data class Social (
	
	@SerializedName("instagram_username") val instagram_username : String,
	@SerializedName("portfolio_url") val portfolio_url : String,
	@SerializedName("twitter_username") val twitter_username : String,
	@SerializedName("paypal_email") val paypal_email : String
)