package com.example.users.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.users.domain.GetPagedUsersUseCase
import com.example.core.usecase.ToggleBookmarkUseCase
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
class UsersViewModel @Inject constructor(
    private val getPagedUsersUseCase: GetPagedUsersUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
) : ViewModel() {

    private val events = MutableSharedFlow<UsersEvent>()
    private val mutableState = MutableStateFlow(UsersState())
    val state: StateFlow<UsersState>
        get() = mutableState

    init {
        fetchUsers()
        handleEvents()
    }

    fun dispatch(event: UsersEvent) = viewModelScope.launch {
        events.emit(event)
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            val users = getPagedUsersUseCase()
                .cachedIn(viewModelScope)

            mutableState.update {
                it.copy(
                    users = users,
                )
            }
        }
    }

    private fun handleEvents() {
        handleErrorScreenTryAgainClicked()
        handleErrorItemRetryClicked()
        handleResetRetry()
        handlePUllToRefresh()
        handleResetRefresh()
        handleToggleBookmark()
    }

    private fun handleErrorScreenTryAgainClicked() {
        events
            .filterIsInstance<UsersEvent.ErrorScreenTryAgainClicked>()
            .onEach {
                fetchUsers()
            }
            .launchIn(viewModelScope)
    }

    private fun handleErrorItemRetryClicked() {
        events
            .filterIsInstance<UsersEvent.ErrorItemRetryClicked>()
            .onEach {
                mutableState.update {
                    it.copy(
                        needsRetry = true,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun handleResetRetry() {
        events
            .filterIsInstance<UsersEvent.ResetRetry>()
            .onEach {
                mutableState.update {
                    it.copy(
                        needsRetry = false,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun handlePUllToRefresh() {
        events
            .filterIsInstance<UsersEvent.PullToRefresh>()
            .onEach {
                mutableState.update {
                    it.copy(
                        needsRefresh = true,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun handleResetRefresh() {
        events
            .filterIsInstance<UsersEvent.ResetRefresh>()
            .onEach {
                mutableState.update {
                    it.copy(
                        needsRefresh = false,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun handleToggleBookmark() {
        events
            .filterIsInstance<UsersEvent.ToggleBookmarkClicked>()
            .onEach { event ->
                toggleBookmarkUseCase(event.key)
            }
            .launchIn(viewModelScope)
    }

}
