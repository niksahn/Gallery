package ru.sakhno.gallery.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoUi(
	val id: String,
	val urls: UrlsUi,
) : Parcelable

@Parcelize
data class UrlsUi(
	val raw: String,
	val full: String,
	val regular: String,
	val small: String,
	val thumb: String,
	val small_s3: String
) : Parcelable