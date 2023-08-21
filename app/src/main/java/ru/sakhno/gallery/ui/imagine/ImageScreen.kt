package ru.sakhno.gallery.ui.imagine

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ru.sakhno.gallery.domain.models.Photo
import ru.sakhno.gallery.ui.views.ZoomableBox

@OptIn(ExperimentalGlideComposeApi::class)
@Destination
@Composable
fun ImageScreen(
	navigator: DestinationsNavigator,
	viewModel: ImageViewModel = hiltViewModel(),
	photo: Photo
) {
	val state by viewModel.screenState.collectAsStateWithLifecycle()
	LaunchedEffect(Unit) { viewModel.setPhoto(photo) }
	Card(modifier = Modifier.fillMaxSize()) {
		ZoomableBox {
			GlideImage(
				modifier = Modifier
					.fillMaxSize()
					.padding(4.dp),
				model = state.photo?.urlsDto?.full,
				contentDescription = null
			)
		}
		//{it.fitCenter().centerCrop()}
	}
}
