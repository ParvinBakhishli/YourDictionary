package app.parvin.yourdictionary.model.network

import kotlinx.serialization.Serializable

@Serializable
data class WordResponse(
    val word: String,
    val phonetic: String? = null,
    val phonetics: List<Phonetic>,
    val meanings: List<Meaning>,
    val license: License? = null,
    val sourceUrls: List<String>? = null
)
