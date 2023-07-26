package com.blez.animeappcompose.feature_recent_release.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.blez.animeappcompose.core.util.ResultState
import com.blez.animeappcompose.feature_recent_release.domain.model.RecentModelItem
import com.blez.animeappcompose.feature_recent_release.domain.repository.RecentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecentViewModel @Inject constructor(val recentRepository: RecentRepository) : ViewModel() {

    sealed class RecentEvent{
        object Idle : RecentEvent()

        object Loading : RecentEvent()

        data class RecentData(val data : List<RecentModelItem>) : RecentEvent()

        data class Failure(val message : String) : RecentEvent()
    }

    private val _recentState  = MutableStateFlow<RecentEvent>(RecentEvent.Idle)
    val recentState = _recentState.asStateFlow()

    private val _recentData = MutableStateFlow<List<RecentModelItem>>(emptyList())
    val recentData = _recentData.asStateFlow()

    init {
        getRecentReleases()
    }


    val recentPagingData = recentRepository.getPagingRecentRelease().flow.cachedIn(viewModelScope)


    fun getRecentReleases(){
        viewModelScope.launch {
            _recentState.emit(RecentEvent.Idle)
           val result =  recentRepository.getRecentRelease()
            when(result){
                is ResultState.Error -> {
                    _recentState.emit(RecentEvent.Failure(result.message.toString()))
                }
                is ResultState.Loading -> {
                    _recentState.emit(RecentEvent.Loading)
                }
                is ResultState.Success -> {
                    _recentData.emit(result.data?: emptyList())
                    _recentState.emit(RecentEvent.RecentData(result.data ?: emptyList()))
                }
            }
        }
    }
}