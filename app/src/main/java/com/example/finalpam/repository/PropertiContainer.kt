package com.example.finalpam.repository

import com.example.finalpam.service_api.PropertiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val jenisRepository: JenisRepository
    val pemilikRepository: PemilikRepository
    val manajerRepository: ManajerRepository
    val propertiRepository: PropertiRepository

}

class PropertiContainer : AppContainer {
    private val baseUrl = "http://192.168.58.250/tugasakhir/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val jenisService: PropertiService by lazy { retrofit.create(PropertiService::class.java) }
    override val jenisRepository: JenisRepository by lazy { NetworkJenisRepository(jenisService) }

    private val pemilikService: PropertiService by lazy { retrofit.create(PropertiService::class.java) }
    override val pemilikRepository: PemilikRepository by lazy { NetworkPemilikRepository(pemilikService) }

    private val manajerService: PropertiService by lazy { retrofit.create(PropertiService::class.java) }
    override val manajerRepository: ManajerRepository by lazy { NetworkManajerRepository(manajerService) }

    private val propertiService: PropertiService by lazy { retrofit.create(PropertiService::class.java) }
    override val propertiRepository: PropertiRepository by lazy { NetworkPropertiRepository(propertiService) }
}