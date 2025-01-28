package com.example.finalpam.model

import kotlinx.serialization.Serializable

@Serializable
data class Manajer (
    val id_manajer : String,
    val nama_manajer : String,
    val kontak_manajer : String,
)
