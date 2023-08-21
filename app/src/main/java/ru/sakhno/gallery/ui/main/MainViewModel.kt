package ru.sakhno.gallery.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.sakhno.gallery.domain.models.Photo
import ru.sakhno.gallery.domain.repositories.ApiReposotory
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	apiReposotory: ApiReposotory
) : ViewModel() {
	private val _event = MutableSharedFlow<MainScreenEvent>(extraBufferCapacity = 1)
	val event = _event.asSharedFlow()
	val photosPaging = apiReposotory.getPhoto().cachedIn(viewModelScope)
	
	fun onClickItem(photo: Photo) {
		_event.tryEmit(MainScreenEvent.goToImageScreen(photo))
	}
}