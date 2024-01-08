package com.example.supabasepractice.model

import kotlinx.serialization.Serializable

@Serializable
data class Professor(
    val id: Int = -1,
    val name: String = "",
    val major: String = ""
)
