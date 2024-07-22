package app.parvin.yourdictionary.model.network

import kotlinx.serialization.Serializable

@Serializable
data class Definition(
    val definition: String,
    val example: String? = null
)
