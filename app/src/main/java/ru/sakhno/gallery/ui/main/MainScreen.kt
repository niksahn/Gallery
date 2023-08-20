package ru.sakhno.gallery.ui.main

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ru.sakhno.gallery.domain.models.Photo

@RootNavGraph(start = true)
@Destination
@Composable
fun MainScreen(
	navigator: DestinationsNavigator,
	viewModel: MainViewModel = hiltViewModel()
) {
	val pagingPhoto = viewModel.photosPaging.collectAsLazyPagingItems()
	val context = LocalContext.current
	when (pagingPhoto.loadState.append) {
		is LoadState.NotLoading -> Unit
		LoadState.Loading -> {
			Box(modifier = Modifier.fillMaxSize())
			{
				CircularProgressIndicator(modifier = Modifier.align(Alignment.BottomCenter))
			}
		}
		is LoadState.Error -> {
			Toast.makeText(
				context,
				(pagingPhoto.loadState.append as LoadState.Error).error.message.toString(),
				Toast.LENGTH_LONG
			).show()
		}
	}
	MainScreenContent(pagingPhoto = pagingPhoto, context = context)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MainScreenContent(
	pagingPhoto: LazyPagingItems<Photo>,
	context: Context
) {
	LazyVerticalGrid(columns = GridCells.Fixed(2)) {
		items(pagingPhoto.itemCount)
		{ index ->
			pagingPhoto[index]?.let { photo ->
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
		when (pagingPhoto.loadState.append) {
			is LoadState.NotLoading -> Unit
			LoadState.Loading -> {
				item {
					CircularProgressIndicator()
				}
			}
			
			is LoadState.Error -> {
				Toast.makeText(
					context,
					(pagingPhoto.loadState.append as LoadState.Error).error.message.toString(),
					Toast.LENGTH_LONG
				).show()
			}
		}
	}
}
