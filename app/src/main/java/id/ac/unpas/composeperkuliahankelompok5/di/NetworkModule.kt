package id.ac.unpas.composeperkuliahankelompok5.di

import android.content.Context
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.ac.unpas.composeperkuliahankelompok5.networks.DosenApi
import id.ac.unpas.composeperkuliahankelompok5.networks.MahasiswaApi
import id.ac.unpas.composeperkuliahankelompok5.networks.MatakuliahApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context:
                            Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(
                "https://setoran-sampah-api.gusdya.net/"
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
            .build()
    }
    @Provides
    @Singleton
    fun provideDosenApi(retrofit: Retrofit):
            DosenApi {
        return retrofit.create(DosenApi::class.java)
    }
    @Provides
    @Singleton
    fun provideMahasiswaApi(retrofit: Retrofit):
            MahasiswaApi {
        return retrofit.create(MahasiswaApi::class.java)
    }
    @Provides
    @Singleton
    fun provideMatakuliahApi(retrofit: Retrofit):
            MatakuliahApi {
        return retrofit.create(MatakuliahApi::class.java)
    }
}