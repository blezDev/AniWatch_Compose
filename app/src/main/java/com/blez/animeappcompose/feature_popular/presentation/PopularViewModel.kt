package com.blez.animeappcompose.feature_popular.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blez.animeappcompose.core.util.ResultState
import com.blez.animeappcompose.feature_popular.domain.model.PopularModelItem
import com.blez.animeappcompose.feature_popular.domain.repository.PopRepository
import com.blez.animeappcompose.feature_recent_release.domain.model.RecentModelItem
import com.blez.animeappcompose.feature_recent_release.presentation.RecentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject  constructor(val repository: PopRepository) : ViewModel() {
    sealed class PopularEvent{
        object Idle : PopularEvent()

        object Loading : PopularEvent()

        data class Popular(val data : List<PopularModelItem>) : PopularEvent()

        data class Failure(val message : String) : PopularEvent()
    }
    private val _popularState  = MutableStateFlow<PopularEvent>(PopularEvent.Idle)
    val popularState : StateFlow<PopularEvent>
        get() = _popularState

    private val _popularData = MutableStateFlow<List<PopularModelItem>>(emptyList())
    val popularData = _popularData.asStateFlow()
    init {
        getPopularReleases()
    }

    fun getPopularReleases(){
        viewModelScope.launch {
            _popularState.emit(PopularEvent.Idle)
            val result =  repository.getPopularList()
            when(result){
                is ResultState.Error -> {
                    _popularState.emit(PopularEvent.Failure(result.message.toString()))
                }
                is ResultState.Loading -> {
                    _popularState.emit(PopularEvent.Loading)
                }
                is ResultState.Success -> {
                    _popularData.emit(result.data ?: emptyList())
                    _popularState.emit(PopularEvent.Popular(result.data ?: emptyList()))
                }
            }
        }
    }


}