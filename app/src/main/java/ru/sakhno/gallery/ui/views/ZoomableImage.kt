package ru.sakhno.gallery.ui.views

import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import ru.sakhno.gallery.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ZoomableImage(image: String) {
	var scale by remember { mutableStateOf(1f) }
	var offset by remember { mutableStateOf(Offset.Zero) }
	val state = rememberTransformableState { zoomChange, offsetChange, _ ->
		scale *= zoomChange
		offset += offsetChange
	}
	
	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		GlideImage(
			model = image,
			modifier = Modifier
				.fillMaxSize()
				.transformable(state = state)
				.graphicsLayer(
					scaleX = scale,
					scaleY = scale,
					translationX = offset.x,
					translationY = offset.y,
				),
			contentDescription = null,
			loading = placeholder { CircularProgressIndicator() },
			failure = placeholder { Text(text = stringResource(id = R.string.load_error)) }
		)
	}
}