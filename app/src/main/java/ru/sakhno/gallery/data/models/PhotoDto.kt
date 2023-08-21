package ru.sakhno.gallery.data.models

import com.google.gson.annotations.SerializedName
import ru.sakhno.gallery.domain.models.Photo
import ru.sakhno.gallery.domain.models.Urls

data class PhotoDto(
	@SerializedName("id") val id: String,
	@SerializedName("slug") val slug: String,
	@SerializedName("created_at") val created_at: String,
	@SerializedName("updated_at") val updated_at: String,
	@SerializedName("promoted_at") val promoted_at: String,
	@SerializedName("width") val width: Int,
	@SerializedName("height") val height: Int,
	@SerializedName("color") val color: String,
	@SerializedName("blur_hash") val blur_hash: String,
	@SerializedName("description") val description: String,
	@SerializedName("alt_description") val alt_description: String,
	@SerializedName("breadcrumbs") val breadcrumbs: List<String>,
	@SerializedName("urls") val urls: UrlsDto,
	@SerializedName("links") val links: Links,
	@SerializedName("likes") val likes: Int,
	@SerializedName("liked_by_user") val liked_by_user: Boolean,
	@SerializedName("current_user_collections") val current_user_collections: List<String>,
	@SerializedName("sponsorship") val sponsorship: Sponsorship,
	@SerializedName("topic_submissions") val topic_submissions: Topic_submissions,
	@SerializedName("user") val user: User
)


fun PhotoDto.mapToDomain() = Photo(id = id, urls = urls.mapToDomain(),
	name = description
)
fun UrlsDto.mapToDomain() = Urls(
	raw = raw,
	full = full,
	regular = regular,
	small = small,
	thumb = thumb,
	small_s3 = small_s3
)