package id.ac.unpas.composeperkuliahankelompok5.networks

import com.skydoves.sandwich.ApiResponse
import id.ac.unpas.composeperkuliahankelompok5.model.Mahasiswa
import retrofit2.http.*

interface MahasiswaApi {
    @GET("api/mahasiswa")
    suspend fun all(): ApiResponse<MahasiswaGetResponse>
    @GET("api/mahasiswa/{id}")
    suspend fun find(@Path("id") id: String):
            ApiResponse<MahasiswaSingleGetResponse>
    @POST("api/mahasiswa")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: Mahasiswa):
            ApiResponse<MahasiswaSingleGetResponse>
    @PUT("api/mahasiswa/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") pathId: String,
                       @Body item: Mahasiswa):
            ApiResponse<MahasiswaSingleGetResponse>
    @DELETE("api/mahasiswa/{id}")
    suspend fun delete(@Path("id") id: String):
            ApiResponse<MahasiswaSingleGetResponse>
}