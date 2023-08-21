package ru.sakhno.gallery.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.sakhno.gallery.data.api.PhotoApiService
import ru.sakhno.gallery.data.models.PhotoDto
import ru.sakhno.gallery.data.models.mapToDomain
import ru.sakhno.gallery.domain.models.Photo
import ru.sakhno.gallery.utils.Constants
import javax.inject.Inject


class PhotoPagingSource @Inject constructor(
	private val photoApiService: PhotoApiService,
) : PagingSource<Int, Photo>() {
	override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
				?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
		}
	}
	
	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
		return try {
			val page = params.key ?: 1
			val response = photoApiService.getPhotos(client_id = Constants.accessKey, page = page)
				.map { it.mapToDomain() }
			LoadResult.Page(
				data = response,
				prevKey = if (page == 1) null else page.minus(1),
				nextKey = if (response.isEmpty()) null else page.plus(1),
			)
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}
}
