package ru.sakhno.gallery.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<STATE : State, EVENT : Event>(initialState: STATE) : ViewModel() {
	
	private val _screenState = MutableStateFlow(initialState)
	
	/** Состояние экрана */
	val screenState = _screenState.asStateFlow()
	
	fun updateState(block: (STATE) -> STATE) =
		_screenState.update(block)
	
	private val _event = MutableSharedFlow<EVENT>(extraBufferCapacity = 1)
	
	/** События для экрана */
	val event = _event.asSharedFlow()
	
	/** Текущее состояние экрана */
	protected val currentState: STATE
		get() = _screenState.value
}