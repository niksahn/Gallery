package ru.sakhno.gallery.ui.main.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
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
				.fillMaxSize(0.2f)
				.height(100.dp)
				.padding(4.dp),
			model = photo.urlsDto.small,
			contentDescription = null
		) {
			it.fitCenter().centerCrop()
		}
	}
}