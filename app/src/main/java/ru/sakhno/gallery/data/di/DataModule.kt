package ru.sakhno.gallery.data.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.sakhno.gallery.data.api.PhotoApiService
import ru.sakhno.gallery.data.repositories.ApiRepositoryImpl
import ru.sakhno.gallery.domain.repositories.ApiReposotory
import ru.sakhno.gallery.utils.Constants
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {
	@Singleton
	@Provides
	fun procvidesApiRepository(apiService: PhotoApiService): ApiReposotory =
		ApiRepositoryImpl(apiService)
	
	@Singleton
	@Provides
	fun providesRetrofit(): PhotoApiService {
		val retrofit = Retrofit.Builder()
			.baseUrl(Constants.baseUrl)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
		return retrofit.create(PhotoApiService::class.java)
		
	}
}



