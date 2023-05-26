package id.ac.unpas.composeperkuliahankelompok5.networks

import com.skydoves.sandwich.ApiResponse
import id.ac.unpas.composeperkuliahankelompok5.model.Dosen
import retrofit2.http.*

interface DosenApi {
    @GET("api/dosen")
    suspend fun all(): ApiResponse<DosenGetResponse>
    @GET("api/dosen/{id}")
    suspend fun find(@Path("id") id: String):
            ApiResponse<DosenSingleGetResponse>
    @POST("api/dosen")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: Dosen):
            ApiResponse<DosenSingleGetResponse>
    @PUT("api/dosen/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") pathId: String,
                       @Body item: Dosen):
            ApiResponse<DosenSingleGetResponse>
    @DELETE("api/dosen/{id}")
    suspend fun delete(@Path("id") id: String):
            ApiResponse<DosenSingleGetResponse>
}