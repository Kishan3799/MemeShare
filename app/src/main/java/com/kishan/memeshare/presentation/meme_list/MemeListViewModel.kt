package com.kishan.memeshare.presentation.meme_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kishan.memeshare.common.Resources
import com.kishan.memeshare.domain.use_case.GetMemesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MemeListViewModel @Inject constructor(
    private val getMemesUseCase: GetMemesUseCase
):ViewModel() {
    private val _state = mutableStateOf(MemeListState())
    val state : State<MemeListState> = _state

    init {
        getMemes()
    }

    private fun getMemes(){
        getMemesUseCase().onEach { results->
            when (results){
                is Resources.Success->{
                    _state.value = results.data?.let { MemeListState(memes = it.memes) }!!
                }
                is Resources.Error->{
                    _state.value = MemeListState(
                        error = results.message ?: "Un Expected Error"
                    )
                }
                is Resources.Loading->{
                    _state.value = MemeListState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}