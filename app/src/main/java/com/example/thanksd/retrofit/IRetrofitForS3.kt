package com.example.thanksd.retrofit

import com.example.thanksd.utils.API
import com.google.gson.JsonElement
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query
import retrofit2.http.Url

interface IRetrofitForS3 {
    @PUT
    fun uploadImageToPresignedUrl(@Url preSignedUrl: String, @Body file: RequestBody): Call<JsonElement>
}