package app.parvin.yourdictionary.model.domain

data class Word(
    val word: String,
    val phonetic: String,
    val audio: String,
    val definition: List<Definition>,
    val synonyms: List<String>,
    val antonyms: List<String>
)
