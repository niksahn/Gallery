package ru.sakhno.gallery.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.sakhno.gallery.domain.models.Photo

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

fun Photo.toPhotoUi() = PhotoUi(
	id = id,
	urls = UrlsUi(
		raw = urls.raw,
		full = urls.full,
		regular = urls.regular,
		small = urls.small,
		thumb = urls.thumb,
		small_s3 = urls.small_s3
	),
)

