package com.example.finalpam.service_api

import com.example.finalpam.model.Jenis
import com.example.finalpam.model.Manajer
import com.example.finalpam.model.Pemilik
import com.example.finalpam.model.Properti
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PropertiService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("bacaproperti.php")
    suspend fun getProperti(): List<Properti>

    @GET("baca1properti.php/{id_properti}")
    suspend fun getPropertiById(@Query("id_properti") id_properti: String): Properti

    @POST("insertproperti.php")
    suspend fun insertProperti(@Body properti: Properti)

    @PUT("editproperti.php/{id_properti}")
    suspend fun updateProperti(@Query("id_properti") id_properti: String, @Body properti: Properti)

    @DELETE("deleteproperti.php/{id_properti}")
    suspend fun deleteProperti(@Query("id_properti") id_properti: String): Response<Void>


    @GET("bacapemilik.php")
    suspend fun getPemilik(): List<Pemilik>

    @GET("baca1pemilik.php/{id_pemilik}")
    suspend fun getPemilikById(@Query("id_pemilik") id_pemilik: String): Pemilik

    @POST("insertpemilik.php")
    suspend fun insertPemilik(@Body pemilik: Pemilik)

    @PUT("editpemilik.php/{id_pemilik}")
    suspend fun updatePemilik(@Query("id_pemilik") id_pemilik: String, @Body pemilik: Pemilik)

    @DELETE("deletepemilik.php/{id_pemilik}")
    suspend fun deletePemilik(@Query("id_pemilik") id_pemilik: String): Response<Void>


    @GET("bacamanajer.php")
    suspend fun getManajer(): List<Manajer>

    @GET("baca1manajer.php/{id_manajer}")
    suspend fun getManajerById(@Query("id_manajer") id_manajer: String): Manajer

    @POST("insertmanajer.php")
    suspend fun insertManajer(@Body manajer: Manajer)

    @PUT("editmanajer.php/{id_manajer}")
    suspend fun updateManajer(@Query("id_manajer") id_manajer: String, @Body manajer: Manajer)

    @DELETE("deletemanajer.php/{id_manajer}")
    suspend fun deleteManajer(@Query("id_manajer") id_manajer: String): Response<Void>


    @GET("bacajenisproperti.php")
    suspend fun getJenis(): List<Jenis>

    @GET("baca1jenisproperti.php/{id_jenis}")
    suspend fun getJenisById(@Query("id_jenis") id_jenis: String): Jenis

    @POST("insertjenisproperti.php")
    suspend fun insertJenis(@Body jenisProperti: Jenis)

    @PUT("editjenisproperti.php/{id_jenis}")
    suspend fun updateJenis(@Query("id_jenis") id_jenis: String, @Body jenisProperti: Jenis)

    @DELETE("deletejenisproperti.php/{id_jenis}")
    suspend fun deleteJenis(@Query("id_jenis") id_jenis: String): Response<Void>

}