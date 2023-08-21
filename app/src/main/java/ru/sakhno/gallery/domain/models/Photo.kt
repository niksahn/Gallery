package ru.sakhno.gallery.domain.models

import ru.sakhno.gallery.ui.models.PhotoUi
import ru.sakhno.gallery.ui.models.UrlsUi

data class Photo(
	val id: String,
	val urls: Urls,
)

data class Urls(
	val raw: String,
	val full: String,
	val regular: String,
	val small: String,
	val thumb: String,
	val small_s3: String
)

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

