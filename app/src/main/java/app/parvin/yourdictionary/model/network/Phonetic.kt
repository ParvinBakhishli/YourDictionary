package app.parvin.yourdictionary.model.network

import kotlinx.serialization.Serializable

@Serializable
data class Phonetic(
    val text: String = "",
    val audio: String? = null,
    val sourceUrl: String? = null,
    val license: License? = null
)
