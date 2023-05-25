package id.ac.unpas.composeperkuliahankelompok5.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.ac.unpas.composeperkuliahankelompok5.networks.DosenApi
import id.ac.unpas.composeperkuliahankelompok5.networks.MahasiswaApi
import id.ac.unpas.composeperkuliahankelompok5.networks.MatakuliahApi
import id.ac.unpas.composeperkuliahankelompok5.persistences.DosenDao
import id.ac.unpas.composeperkuliahankelompok5.persistences.MahasiswaDao
import id.ac.unpas.composeperkuliahankelompok5.persistences.MatakuliahDao
import id.ac.unpas.composeperkuliahankelompok5.repositories.DosenRepository
import id.ac.unpas.composeperkuliahankelompok5.repositories.MahasiswaRepository
import id.ac.unpas.composeperkuliahankelompok5.repositories.MatakuliahRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideDosenRepository(
        api: DosenApi,
        dao: DosenDao
    ): DosenRepository {
        return DosenRepository(api, dao)
    }
    @Provides
    @ViewModelScoped
    fun provideMahasiswaRepository(
        api: MahasiswaApi,
        dao: MahasiswaDao
    ): MahasiswaRepository {
        return MahasiswaRepository(api, dao)
    }
    @Provides
    @ViewModelScoped
    fun provideMatakuliahRepository(
        api: MatakuliahApi,
        dao: MatakuliahDao
    ): MatakuliahRepository {
        return MatakuliahRepository(api, dao)
    }
}