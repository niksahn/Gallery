package ru.sakhno.gallery.ui.main

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ru.sakhno.gallery.R
import ru.sakhno.gallery.domain.models.Photo
import ru.sakhno.gallery.domain.models.toPhotoUi
import ru.sakhno.gallery.ui.destinations.ImageScreenDestination
import ru.sakhno.gallery.ui.main.views.Item

@RootNavGraph(start = true)
@Destination
@Composable
fun MainScreen(
	navigator: DestinationsNavigator,
	viewModel: MainViewModel = hiltViewModel()
) {
	val pagingPhoto = viewModel.photosPaging.collectAsLazyPagingItems()
	val context = LocalContext.current
	LaunchedEffect(Unit) {
		viewModel.event.collect() {
			when (it) {
				is MainScreenEvent.goToImageScreen -> navigator.navigate(
					ImageScreenDestination(it.photo.toPhotoUi())
				)
			}
		}
	}
	when (pagingPhoto.loadState.append) {
		is LoadState.NotLoading -> Unit
		is LoadState.Loading -> {
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
	MainScreenContent(pagingPhoto = pagingPhoto, onClickItem = viewModel::onClickItem)
}

@Composable
fun MainScreenContent(
	pagingPhoto: LazyPagingItems<Photo>,
	onClickItem: (Photo) -> Unit
) {
	Scaffold(
		topBar = {
			TopAppBar(backgroundColor = MaterialTheme.colors.primary) {
				Text(
					text = stringResource(id = R.string.app_name),
					textAlign = TextAlign.Center,
					modifier = Modifier.fillMaxWidth(),
					color = Color.Black
				)
			}
		}) {
		if (pagingPhoto.itemCount > 0) {
			LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(it)) {
				items(pagingPhoto.itemCount)
				{ index ->
					pagingPhoto[index]?.let { photo ->
						Item(
							photo = photo,
							onClickItem = onClickItem
						)
					}
				}
			}
		} else {
			Box(modifier = Modifier.fillMaxSize()) {
				CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
			}
		}
	}
}

