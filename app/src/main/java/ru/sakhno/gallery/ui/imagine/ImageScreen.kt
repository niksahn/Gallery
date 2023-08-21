package ru.sakhno.gallery.ui.imagine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ru.sakhno.gallery.domain.models.Photo
import ru.sakhno.gallery.ui.views.ZoomableImage

@Destination
@Composable
fun ImageScreen(
	navigator: DestinationsNavigator,
	viewModel: ImageViewModel = hiltViewModel(),
	photo: Photo
) {
	val context = LocalContext.current
	val state by viewModel.screenState.collectAsStateWithLifecycle()
	LaunchedEffect(Unit) { viewModel.setPhoto(photo) }
	state.photo?.urlsDto?.let {
		ZoomableImage(
			image = it.regular
		)
	}
}
