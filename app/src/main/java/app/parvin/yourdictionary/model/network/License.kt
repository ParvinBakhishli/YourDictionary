package app.parvin.yourdictionary.model.network

import kotlinx.serialization.Serializable

@Serializable
data class License(
    val name: String? = null,
    val url: String? = null
)
