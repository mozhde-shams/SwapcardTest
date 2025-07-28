package com.example.user_detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.user_detail.presentation.navigation.ARG_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val userName: String = savedStateHandle
        .get<String>(ARG_NAME)
        ?: "No user name provided"
}