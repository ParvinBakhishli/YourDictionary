package app.parvin.yourdictionary.domain

import app.parvin.yourdictionary.model.domain.Word

interface WordRepository {
    suspend fun fetchWord(word: String): Word
}