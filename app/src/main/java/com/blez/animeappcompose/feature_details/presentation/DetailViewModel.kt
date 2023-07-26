package com.blez.animeappcompose.feature_details.presentation

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.blez.animeappcompose.common.HistoryManager
import com.blez.animeappcompose.core.util.ResultState
import com.blez.animeappcompose.feature_details.domain.model.DetailModel
import com.blez.animeappcompose.feature_details.domain.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val repo: DetailRepository,val historyManager: HistoryManager) : ViewModel() {
    sealed class DetailEvent {
        data class ShowSnackbar(val message: String) : DetailEvent()
        data class AnimeDetail(val data: DetailModel) : DetailEvent()
        data class Failure(val message: String) : DetailEvent()
    }

    private val _animeDetail = mutableStateOf<DetailInfoState>(DetailInfoState())
    val animeDetail : State<DetailInfoState> = _animeDetail

    private val _eventFlow = MutableSharedFlow<DetailEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    private val _animeData =  MutableSharedFlow<DetailModel>()
    val animeData = _animeData.asSharedFlow()

    private var fetchDetailJob : Job?=null
    fun getAnimeDetail(animeId: String) {
        fetchDetailJob?.cancel()
        fetchDetailJob = viewModelScope.launch {

            val result = repo.getAnimeDetail(animeId)
            when (result) {
                is ResultState.Error -> {
                    result.data?.let {
                        _animeDetail.value.copy(
                            it,
                            isLoading = false
                        )
                    }
                    _eventFlow.emit(DetailEvent.ShowSnackbar(result.message ?: "Something went wrong"))
                }

                is ResultState.Loading -> {

                }

                is ResultState.Success -> {
                    result.data?.let {
                        _animeData.emit(it)
                        if (it.releasedDate?.toInt()!! <= 2020){
                           historyManager.savePreviousAnime(it.animeTitle ?: "Bleach")
                        }
                        _animeDetail.value.copy(
                            it,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

}