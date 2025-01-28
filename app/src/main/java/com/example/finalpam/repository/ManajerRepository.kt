package com.example.finalpam.repository

import com.example.finalpam.model.Manajer
import com.example.finalpam.service_api.PropertiService
import java.io.IOException

interface ManajerRepository{
    suspend fun getManajer(): List<Manajer>
    suspend fun insertManajer(manajer: Manajer)
    suspend fun updateManajer(id_manajer: String, manajer: Manajer)
    suspend fun deleteManajer(id_manajer: String)
    suspend fun getManajerById(id_manajer: String): Manajer
}

class NetworkManajerRepository(
    private val manajerApiService: PropertiService
) : ManajerRepository {
    override suspend fun insertManajer(manajer: Manajer) {
        manajerApiService.insertManajer(manajer)
    }

    override suspend fun updateManajer(id_manajer: String, manajer: Manajer) {
        manajerApiService.updateManajer(id_manajer,manajer)
    }

    override suspend fun deleteManajer(id_manajer: String) {
        try {
            val response = manajerApiService.deleteManajer(id_manajer)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete manajer. HTTP status code : ${response.code()} ")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }

    override suspend fun getManajer(): List<Manajer> = manajerApiService.getManajer()
    override suspend fun getManajerById(id_manajer: String): Manajer {
        return manajerApiService.getManajerById(id_manajer)
    }

}