package ru.sakhno.gallery.ui.main.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import ru.sakhno.gallery.R
import ru.sakhno.gallery.domain.models.Photo


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Item(
	photo: Photo,
	onClickItem: (Photo) -> Unit
) {
	Card(modifier = Modifier.clickable(onClick = remember(photo) { { onClickItem(photo) } })) {
		GlideImage(
			modifier = Modifier
				.fillMaxSize()
				.height(150.dp)
				.padding(10.dp),
			model = photo.urls.small,
			contentDescription = null,
			loading = placeholder { CircularProgressIndicator() },
			failure = placeholder { Text(text = stringResource(id = R.string.load_error)) }
		) {
			it.fitCenter().centerCrop()
		}
	}
}