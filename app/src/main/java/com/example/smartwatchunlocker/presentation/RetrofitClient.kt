package com.example.smartwatchunlocker.presentation

import com.example.smartwatchunlocker.presentation.RetrofitClient.YOUR_TOKEN
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class TokenInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {

        //rewrite the request to add bearer token
        val newRequest: Request = chain.request().newBuilder()
            .header("Authorization", "Bearer $YOUR_TOKEN")
            .build()
        return chain.proceed(newRequest)
    }
}

object RetrofitClient {
    const val YOUR_TOKEN = "patsczbGUrVLtvJxb.a0626cc3ab68cabcbf57510ef2a57667d30200c444e506949f0f2d8e6cf2dcd9"

    private val mHttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private var interceptor = TokenInterceptor()

    private val mOkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .addInterceptor(mHttpLoggingInterceptor)
        .build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.airtable.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(mOkHttpClient)
        .build()

    var service: ApiInterface = retrofit.create(ApiInterface::class.java)


}