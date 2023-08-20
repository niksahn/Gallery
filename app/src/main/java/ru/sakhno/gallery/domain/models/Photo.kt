package ru.sakhno.gallery.domain.models

data class Photo(
	val id: String,
	val urlsDto: Urls
)

data class Urls(
	val raw: String,
	val full: String,
	val regular: String,
	val small: String,
	val thumb: String,
	val small_s3: String
)