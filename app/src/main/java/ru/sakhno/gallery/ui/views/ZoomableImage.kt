package ru.sakhno.gallery.ui.views

import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.sakhno.gallery.R


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ZoomableImage(image: Any) {
	
	// set up all transformation states
	var scale by remember { mutableStateOf(1f) }
	//var rotation by remember { mutableStateOf(0f) }
	var offset by remember { mutableStateOf(Offset.Zero) }
	val state = rememberTransformableState { zoomChange, offsetChange, _ ->
		scale *= zoomChange
		//rotation += rotationChange
		offset += offsetChange
	}
	var loading by remember {
		mutableStateOf(true)
	}
	var error by remember {
		mutableStateOf(false)
	}
	if (loading && !error) {
		Box(modifier = Modifier.fillMaxSize()) {
			CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
		}
	} else if (error) {
		Toast.makeText(
			LocalContext.current,
			stringResource(id = R.string.load_error),
			Toast.LENGTH_LONG
		).show()
	}
	GlideImage(
		model = image,
		modifier = Modifier
			.fillMaxSize()
			.transformable(state = state)
			.graphicsLayer(
				scaleX = scale,
				scaleY = scale,
				//	rotationZ = rotation,
				translationX = offset.x,
				translationY = offset.y
			),
		contentDescription = null,
	) {
		it.addListener(object : RequestListener<Drawable> {
			override fun onLoadFailed(
				e: GlideException?,
				model: Any?,
				target: Target<Drawable>?,
				isFirstResource: Boolean
			): Boolean {
				error = true
				return false
			}
			
			override fun onResourceReady(
				resource: Drawable?,
				model: Any?,
				target: Target<Drawable>?,
				dataSource: DataSource?,
				isFirstResource: Boolean
			): Boolean {
				loading = false
				return false
			}
		})
	}
}

/*
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
			loading = placeholder {
				Box(modifier = Modifier.fillMaxSize()) {
					CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
				}
			},
			failure = placeholder { Text(text = stringResource(id = R.string.load_error)) }
		)
	}
}

 */