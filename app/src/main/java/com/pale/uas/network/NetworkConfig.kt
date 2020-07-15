package com.pale.uas.network

import com.pale.uas.model.ResultData
import com.pale.uas.model.ResultStatus
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object NetworkConfig {
    fun getInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return okHttpClient
    }

    //    retrofit
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rosid.my.id/index.php/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() =
        getRetrofit().create(DataService::class.java)
}

interface DataService {
    //    Fungsi create Data
    @FormUrlEncoded
    @POST("person/insert")
    fun addStaff(
        @Field("name") name: String,
        @Field("phone") hp: String,
        @Field("address") alamat: String
    ):
            Call<ResultStatus>

    //Fungsi Get Data
    @GET("person")
    fun getData(): Call<ResultData>

    //Fungsi Delete Data
    @FormUrlEncoded
//    @DELETE("deleteStaff")
    @HTTP(method = "DELETE", path = "person/delete", hasBody = true)
    fun deleteStaff(@Field("id") id: String?):
            Call<ResultStatus>

    //Fungsi Update Data
    @FormUrlEncoded
//    @PUT("updateStaff")
    @HTTP(method = "PUT", path = "person/update", hasBody = true)
    fun updateStaff(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("phone") hp: String,
        @Field("address") alamat: String
    ): Call<ResultStatus>


}