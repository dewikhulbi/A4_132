package com.example.finalpam.model

import kotlinx.serialization.Serializable

@Serializable
data class Jenis (
    val id_jenis : String,
    val nama_jenis : String,
    val deskripsi_jenis : String,
)
