package app.parvin.yourdictionary.di

import app.parvin.yourdictionary.data.network.WordService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesJson() = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideWordClient(
        json: Json
    ) = Retrofit.Builder()
        .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Provides
    @Singleton
    fun provideWordService(client: Retrofit) =
        client.create<WordService>()

}