package ru.sakhno.gallery.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.sakhno.gallery.domain.repositories.ApiReposotory
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	apiReposotory: ApiReposotory
) : ViewModel() {
	val photosPaging = apiReposotory.getPhoto().cachedIn(viewModelScope)
}