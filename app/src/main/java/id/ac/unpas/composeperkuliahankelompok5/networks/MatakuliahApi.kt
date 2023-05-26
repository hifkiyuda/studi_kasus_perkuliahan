package id.ac.unpas.composeperkuliahankelompok5.networks

import com.skydoves.sandwich.ApiResponse
import id.ac.unpas.composeperkuliahankelompok5.model.Matakuliah
import retrofit2.http.*

interface MatakuliahApi {
    @GET("api/matakuliah")
    suspend fun all(): ApiResponse<MatakuliahGetResponse>
    @GET("api/matakuliah/{id}")
    suspend fun find(@Path("id") id: String):
            ApiResponse<MatakuliahSingleGetResponse>
    @POST("api/matakuliah")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: Matakuliah):
            ApiResponse<MatakuliahSingleGetResponse>
    @PUT("api/matakuliah/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") pathId: String,
                       @Body item: Matakuliah
    ):
            ApiResponse<MatakuliahSingleGetResponse>
    @DELETE("api/matakuliah/{id}")
    suspend fun delete(@Path("id") id: String):
            ApiResponse<MatakuliahSingleGetResponse>
}