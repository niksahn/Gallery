package ru.sakhno.gallery.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import ru.sakhno.gallery.data.api.PhotoApiService
import ru.sakhno.gallery.data.paging.PhotoPagingSource
import ru.sakhno.gallery.domain.repositories.ApiReposotory
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
	private val photoApiService: PhotoApiService
) : ApiReposotory {
	override fun getPhoto() = Pager(
		config = PagingConfig(
			pageSize = 15,
		),
		pagingSourceFactory = {
			PhotoPagingSource(photoApiService)
		}
	).flow
}
