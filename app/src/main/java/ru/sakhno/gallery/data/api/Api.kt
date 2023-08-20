package ru.sakhno.gallery.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.sakhno.gallery.data.models.PhotoDto

interface PhotoApiService {
	@GET("photos/")
	suspend fun getPhotos(
		@Query("client_id") client_id: String,
		@Query("page") page: Int
	): List<PhotoDto>
}
