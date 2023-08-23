package ru.sakhno.gallery.ui.main

import ru.sakhno.gallery.ui.models.PhotoUi
import ru.sakhno.gallery.utils.Event

sealed class MainScreenEvent : Event() {
	class goToImageScreen(val photo: PhotoUi) : MainScreenEvent()
}