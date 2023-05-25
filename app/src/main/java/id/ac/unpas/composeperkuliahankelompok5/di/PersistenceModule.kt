package id.ac.unpas.composeperkuliahankelompok5.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import id.ac.unpas.composeperkuliahankelompok5.persistences.AppDatabase
import id.ac.unpas.composeperkuliahankelompok5.persistences.DosenDao
import id.ac.unpas.composeperkuliahankelompok5.persistences.MahasiswaDao
import id.ac.unpas.composeperkuliahankelompok5.persistences.MatakuliahDao

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                "pengelolaan-perkuliahan"
            )
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideDosenDao(appDatabase: AppDatabase): DosenDao {
        return appDatabase.dosenDao()
    }
    @Provides
    @Singleton
    fun provideMahasiswaDao(appDatabase: AppDatabase): MahasiswaDao {
        return appDatabase.mahasiswaDao()
    }
    @Provides
    @Singleton
    fun provideMatakuliahDao(appDatabase: AppDatabase): MatakuliahDao {
        return appDatabase.matakuliahDao()
    }
}