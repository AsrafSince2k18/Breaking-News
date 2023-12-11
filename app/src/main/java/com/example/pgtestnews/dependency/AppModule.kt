package com.example.pgtestnews.dependency

import com.example.pgtestnews.domain.api.NewsApi
import com.example.pgtestnews.domain.api.NewsApi.Companion.BASE_URL
import com.example.pgtestnews.domain.repository.Repository
import com.example.pgtestnews.domain.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi() : NewsApi{
        val retrofites = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            return retrofites.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(newsApi: NewsApi) : Repository{
        return RepositoryImpl(newsApi=newsApi)
    }

}