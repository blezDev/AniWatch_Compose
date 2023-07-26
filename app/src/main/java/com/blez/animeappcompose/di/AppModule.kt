package com.blez.animeappcompose.di

import android.app.Application
import android.content.Context
import com.blez.animeappcompose.common.HistoryManager
import com.blez.animeappcompose.core.Constants.BASE_URL
import com.blez.animeappcompose.feature_details.data.remote.DetailsApi
import com.blez.animeappcompose.feature_details.data.repository.DetailRepositoryImpl
import com.blez.animeappcompose.feature_details.domain.repository.DetailRepository
import com.blez.animeappcompose.feature_popular.data.remote.PopularAPI
import com.blez.animeappcompose.feature_popular.data.repository.PopRepositoryImpl
import com.blez.animeappcompose.feature_popular.domain.repository.PopRepository
import com.blez.animeappcompose.feature_recent_release.data.remote.RecentAPI
import com.blez.animeappcompose.feature_recent_release.data.repository.RecentRepositoryImpl
import com.blez.animeappcompose.feature_recent_release.domain.repository.RecentRepository
import com.blez.animeappcompose.feature_recommendation.data.remote.ML_API
import com.blez.animeappcompose.feature_recommendation.data.repository.MLRepositoryImpl
import com.blez.animeappcompose.feature_recommendation.domain.repository.MLRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES) // write timeout
            .readTimeout(1, TimeUnit.MINUTES) // read timeout
            .build()

    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    }

    @Singleton
    @Provides
    fun providesRecentApi(retrofit: Retrofit) : RecentAPI{
        return retrofit.create(RecentAPI::class.java)
    }


    @Singleton
    @Provides
    fun providesPopularApi(retrofit: Retrofit) : PopularAPI{
        return retrofit.create(PopularAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesDetailsApi(retrofit: Retrofit) : DetailsApi{
        return retrofit.create(DetailsApi::class.java)
    }

    @Singleton
    @Provides
    fun providesMLApi(retrofit: Retrofit) : ML_API{
        return retrofit.create(ML_API::class.java)
    }


    @Singleton
    @Provides
    fun providesHistoryManager(@ApplicationContext context: Context) : HistoryManager = HistoryManager(context)

    @Singleton
    @Provides
    fun providesApplicationContext(@ApplicationContext context: ApplicationContext): ApplicationContext = context

    @Singleton
    @Provides
    fun providesRecentRepository(recentApi : RecentAPI,@ApplicationContext  context: Context): RecentRepository = RecentRepositoryImpl(recentApi,context)


    @Singleton
    @Provides
    fun providesPopularRepository(popularAPI: PopularAPI, @ApplicationContext context: Context) : PopRepository = PopRepositoryImpl(context, popularAPI)

    @Singleton
    @Provides
    fun providesDetailsRepository(detailsApi: DetailsApi, @ApplicationContext context: Context) : DetailRepository = DetailRepositoryImpl(context, detailsApi)


    @Singleton
    @Provides
    fun providesMLRepository(detailsApi: ML_API, @ApplicationContext context: Context) : MLRepository = MLRepositoryImpl(context, detailsApi)

}