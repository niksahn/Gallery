package ru.sakhno.gallery.domain.repositories

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.sakhno.gallery.domain.models.Photo

interface ApiReposotory {
	fun getPhoto(): Flow<PagingData<Photo>>
}