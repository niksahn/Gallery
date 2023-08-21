package ru.sakhno.gallery.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
	val id: String,
	val urlsDto: Urls,
	val name:String
) : Parcelable

@Parcelize
data class Urls(
	val raw: String,
	val full: String,
	val regular: String,
	val small: String,
	val thumb: String,
	val small_s3: String
) : Parcelable