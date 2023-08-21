package ru.sakhno.gallery.ui.main

import ru.sakhno.gallery.domain.models.Photo
import ru.sakhno.gallery.utils.Event

sealed class MainScreenEvent:Event() {
	class goToImageScreen(val photo: Photo) : MainScreenEvent()
}