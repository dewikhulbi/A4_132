package com.example.finalpam.repository

import com.example.finalpam.model.Jenis
import com.example.finalpam.service_api.PropertiService
import java.io.IOException

interface JenisRepository{
    suspend fun getJenis(): List<Jenis>
    suspend fun insertJenis(jenis: Jenis)
    suspend fun updateJenis(id_jenis: String, jenis: Jenis)
    suspend fun deleteJenis(id_jenis: String)
    suspend fun getJenisById(id_jenis: String): Jenis
}

class NetworkJenisRepository(
    private val jenisApiService: PropertiService
) : JenisRepository {
    override suspend fun insertJenis(jenis: Jenis) {
        jenisApiService.insertJenis(jenis)
    }

    override suspend fun updateJenis(id_jenis: String, jenis: Jenis) {
        jenisApiService.updateJenis(id_jenis,jenis)
    }

    override suspend fun deleteJenis(id_jenis: String) {
        try {
            val response = jenisApiService.deleteJenis(id_jenis)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete jenis. HTTP status code : ${response.code()} ")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }

    override suspend fun getJenis(): List<Jenis> = jenisApiService.getJenis()
    override suspend fun getJenisById(id_jenis: String): Jenis {
        return jenisApiService.getJenisById(id_jenis)
    }

}