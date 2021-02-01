package com.shuxin.mtxdadmin.di

import android.app.Application
import com.shuxin.mtxdadmin.Constant
import com.shuxin.mtxdadmin.utils.network.ResApi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by Buzz on 2019/4/23.
 * Email :lmx2060918@126.com
 */
@Module
class HttpModule {

    companion object {
        const val DEFAULT_TIMEOUT = 10
    }


    /****************************************CACHE*************************************************/
    @Singleton @Provides
    fun provideOkHttpCache(app: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cacheFile = File(app.cacheDir, "cache")
        return Cache(cacheFile, cacheSize.toLong())
    }
    /****************************************CACHE*************************************************/



    /**************************************Interceptor*********************************************/
    @Singleton @Provides @Named("default_header")
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            chain: Interceptor.Chain -> chain.proceed(chain.request().newBuilder()
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .build())
        }
    }
    /**************************************Interceptor*********************************************/






    /****************************************Client************************************************/
    @Singleton @Provides @Named("default_client")
    fun provideOkHttpClient(@Named("default_header")headerInterceptor: Interceptor): OkHttpClient {
        val logInterceptor:HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (Constant.getIsShowLog()) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        val builder:OkHttpClient.Builder = OkHttpClient.Builder()
        return builder.readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .retryOnConnectionFailure(true).build()
    }

    @Singleton @Provides @Named("movie_client")
    fun provideMovieOkHttpClient(): OkHttpClient {
        val logInterceptor:HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (Constant.getIsShowLog()) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        val builder:OkHttpClient.Builder = OkHttpClient.Builder()
        return builder.readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(logInterceptor)
            .retryOnConnectionFailure(true).build()
    }

    /****************************************Client************************************************/





    /****************************************Service***********************************************/
    @Singleton @Provides @Named("default")
    fun provideRemoteService(@Named("default_client") okHttpClient: OkHttpClient): ResApi {
        val retrofit:Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(Constant.getInterFaceUrl())
                .build()
        return retrofit.create(ResApi::class.java)
    }

    @Singleton @Provides @Named("shop")
    fun provideShopService(@Named("default_client") okHttpClient: OkHttpClient): ResApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(Constant.getShopInterfaceUrl())
            .build()
        return retrofit.create(ResApi::class.java)
    }

    @Singleton @Provides @Named("movie")
    fun provideMovieService(@Named("movie_client") okHttpClient: OkHttpClient): ResApi {
        val retrofit:Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(Constant.BASE_URL)
            .build()
        return retrofit.create(ResApi::class.java)
    }


    /****************************************Service***********************************************/
}
