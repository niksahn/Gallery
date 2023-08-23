package ru.sakhno.gallery.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import ru.sakhno.gallery.domain.repositories.ApiReposotory
import ru.sakhno.gallery.ui.models.PhotoUi
import ru.sakhno.gallery.ui.models.toPhotoUi
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	apiReposotory: ApiReposotory
) : ViewModel() {
	private val _event = MutableSharedFlow<MainScreenEvent>(extraBufferCapacity = 1)
	val event = _event.asSharedFlow()
	val photosPaging =
		apiReposotory.getPhoto().cachedIn(viewModelScope).map { it.map { it.toPhotoUi() } }
	
	fun onClickItem(photo: PhotoUi) {
		_event.tryEmit(MainScreenEvent.goToImageScreen(photo))
	}
}