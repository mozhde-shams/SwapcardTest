package com.example.user_detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.usecase.ObserveBookmarkUseCase
import com.example.core.usecase.ToggleBookmarkUseCase
import com.example.user_detail.presentation.navigation.ARG_USER_KEY
import com.example.user_detail.presentation.navigation.ARG_USER_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
    private val observeBookmarkUseCase: ObserveBookmarkUseCase,
) : ViewModel() {

    private val events = MutableSharedFlow<UserDetailsEvent>()
    private val mutableState = MutableStateFlow(UserDetailsState())
    val state: StateFlow<UserDetailsState>
        get() = mutableState

    private val userKey: String = savedStateHandle.get<String>(ARG_USER_KEY).orEmpty()
    private val userName: String = savedStateHandle.get<String>(ARG_USER_NAME).orEmpty()

    init {
        mutableState.update {
            it.copy(
                userKey = userKey,
                userName = userName
            )
        }
        observeBookmarkState()
        handleToggleBookmark()
    }

    fun dispatch(event: UserDetailsEvent) = viewModelScope.launch {
        events.emit(event)
    }

    private fun observeBookmarkState() {
        observeBookmarkUseCase(userKey)
            .onEach { bookmarked ->
                mutableState.update { it.copy(isBookmarked = bookmarked) }
            }
            .launchIn(viewModelScope)
    }

    private fun handleToggleBookmark() {
        events
            .filterIsInstance<UserDetailsEvent.ToggleBookmarkClicked>()
            .onEach { event ->
                toggleBookmarkUseCase(event.key)
                mutableState.update { it.copy(isBookmarked = !it.isBookmarked) }
            }
            .launchIn(viewModelScope)
    }
}