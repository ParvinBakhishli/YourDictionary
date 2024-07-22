package app.parvin.yourdictionary.model.domain

data class Definition(
    val partOfSpeech: String,
    val definition: String,
    val example: String?
)
