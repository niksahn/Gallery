package ru.sakhno.gallery.ui.imagine

import ru.sakhno.gallery.domain.models.Photo
import ru.sakhno.gallery.utils.State

data class ImageScreenState(
	val photo: Photo? = null
) : State()