package com.papermoon.spaceapp.data.datasource.remote.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.papermoon.spaceapp.BuildConfig
import com.papermoon.spaceapp.data.datasource.remote.astronaut.di.networkAstronautModule
import com.papermoon.spaceapp.data.datasource.remote.celestialBody.di.networkCelestialBodyModule
import com.papermoon.spaceapp.data.datasource.remote.event.di.networkEventModule
import com.papermoon.spaceapp.data.datasource.remote.launch.di.networkLaunchModule
import com.papermoon.spaceapp.data.datasource.remote.spacestation.di.networkSpaceStationModule
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get()) }
    single { provideLoggingInterceptor() }
    includes(
        networkLaunchModule,
        networkAstronautModule,
        networkSpaceStationModule,
        networkCelestialBodyModule,
        networkEventModule
    )
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
}

fun provideLoggingInterceptor(): Interceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}
