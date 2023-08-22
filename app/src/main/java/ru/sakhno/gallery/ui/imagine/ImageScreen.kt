package ru.sakhno.gallery.ui.imagine

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ru.sakhno.gallery.ui.models.PhotoUi
import ru.sakhno.gallery.ui.views.ZoomableImage

@Destination
@Composable
fun ImageScreen(
	navigator: DestinationsNavigator,
	photo: PhotoUi
) {
	ZoomableImage(
		image = photo.urls.regular
	)
}
