package com.example.finalpam.repository

import com.example.finalpam.model.Properti
import com.example.finalpam.service_api.PropertiService
import java.io.IOException

interface PropertiRepository{
    suspend fun getProperti(): List<Properti>
    suspend fun insertProperti(properti: Properti)
    suspend fun updateProperti(id_properti: String, properti: Properti)
    suspend fun deleteProperti(id_properti: String)
    suspend fun getPropertiById(id_properti: String): Properti
}

class NetworkPropertiRepository(
    private val propertiApiService: PropertiService
) : PropertiRepository {
    override suspend fun insertProperti(properti: Properti) {
        propertiApiService.insertProperti(properti)
    }

    override suspend fun updateProperti(id_properti: String, properti: Properti) {
        propertiApiService.updateProperti(id_properti,properti)
    }

    override suspend fun deleteProperti(id_properti: String) {
        try {
            val response = propertiApiService.deleteProperti(id_properti)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete properti. HTTP status code : ${response.code()} ")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }

    override suspend fun getProperti(): List<Properti> = propertiApiService.getProperti()
    override suspend fun getPropertiById(id_properti: String): Properti {
        return propertiApiService.getPropertiById(id_properti)
    }

}