package ru.sakhno.gallery.ui.imagine

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.sakhno.gallery.ui.models.PhotoUi
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor() : ViewModel() {
	private val _screenState = MutableStateFlow(ImageScreenState())
	val screenState = _screenState.asStateFlow()
	
	fun setPhoto(photo: PhotoUi) {
		_screenState.update { it.copy(photo = photo) }
	}
}