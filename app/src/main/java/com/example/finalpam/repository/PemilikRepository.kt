package com.example.finalpam.repository

import com.example.finalpam.model.Pemilik
import com.example.finalpam.service_api.PropertiService
import java.io.IOException

interface PemilikRepository{
    suspend fun getPemilik(): List<Pemilik>
    suspend fun insertPemilik(pemilik: Pemilik)
    suspend fun updatePemilik(id_pemilik: String, pemilik: Pemilik)
    suspend fun deletePemilik(id_pemilik: String)
    suspend fun getPemilikById(id_pemilik: String): Pemilik
}

class NetworkPemilikRepository(
    private val pemilikApiService: PropertiService
) : PemilikRepository {
    override suspend fun insertPemilik(pemilik: Pemilik) {
        pemilikApiService.insertPemilik(pemilik)
    }

    override suspend fun updatePemilik(id_pemilik: String, pemilik: Pemilik) {
        pemilikApiService.updatePemilik(id_pemilik,pemilik)
    }

    override suspend fun deletePemilik(id_pemilik: String) {
        try {
            val response = pemilikApiService.deletePemilik(id_pemilik)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete pemilik. HTTP status code : ${response.code()} ")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }

    override suspend fun getPemilik(): List<Pemilik> = pemilikApiService.getPemilik()
    override suspend fun getPemilikById(id_pemilik: String): Pemilik {
        return pemilikApiService.getPemilikById(id_pemilik)
    }

}