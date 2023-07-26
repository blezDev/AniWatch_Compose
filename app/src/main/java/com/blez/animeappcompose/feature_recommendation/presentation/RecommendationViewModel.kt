package com.blez.animeappcompose.feature_recommendation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blez.animeappcompose.core.util.ResultState
import com.blez.animeappcompose.feature_popular.domain.model.PopularModelItem
import com.blez.animeappcompose.feature_popular.presentation.PopularViewModel
import com.blez.animeappcompose.feature_recommendation.domain.model.PredictionModelItem
import com.blez.animeappcompose.feature_recommendation.domain.repository.MLRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor (val mlRepository: MLRepository) : ViewModel()
{
    sealed class RecommendationEvent{
        object Idle : RecommendationEvent()

        object Loading : RecommendationEvent()

        data class Popular(val data : List<PredictionModelItem>) : RecommendationEvent()

        data class Failure(val message : String) : RecommendationEvent()
    }



    private val _predictionState = MutableStateFlow<List<PredictionModelItem>>(emptyList())
    val popularData = _predictionState.asStateFlow()

    fun getRecommendation(animeName : String){
        viewModelScope.launch {
            val result =  mlRepository.getRecommendation(animeName = animeName)
            when(result){
                is ResultState.Error -> {
                }
                is ResultState.Loading -> {
                }
                is ResultState.Success -> {
                    _predictionState.emit(result.data ?: emptyList())

                }
            }
        }
    }

}