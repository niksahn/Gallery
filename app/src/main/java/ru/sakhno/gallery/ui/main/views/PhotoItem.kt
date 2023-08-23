package ru.sakhno.gallery.ui.main.views

import android.graphics.drawable.Drawable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.sakhno.gallery.R
import ru.sakhno.gallery.ui.models.PhotoUi


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Item(
	photo: PhotoUi,
	onClickItem: (PhotoUi) -> Unit
) {
	var loading by remember {
		mutableStateOf(true)
	}
	var error by remember {
		mutableStateOf(false)
	}
	Card(
		elevation = 10.dp,
		modifier = Modifier.clickable(onClick = remember(photo) { { onClickItem(photo) } })
	) {
		if (loading && !error) {
			Box(modifier = Modifier.fillMaxSize()) {
				CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
			}
		} else if (error) {
			Text(stringResource(id = R.string.load_error))
		}
		GlideImage(
			modifier = Modifier
				.fillMaxSize()
				.height(150.dp)
				.padding(8.dp),
			model = photo.urls.small,
			contentDescription = null,
		) {
			it.fitCenter().centerCrop().addListener(object : RequestListener<Drawable> {
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
}