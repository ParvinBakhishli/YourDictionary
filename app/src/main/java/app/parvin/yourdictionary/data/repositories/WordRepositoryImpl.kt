package app.parvin.yourdictionary.data.repositories

import android.util.Log
import app.parvin.yourdictionary.data.network.WordService
import app.parvin.yourdictionary.domain.WordRepository
import app.parvin.yourdictionary.model.domain.Definition
import app.parvin.yourdictionary.model.domain.Word
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val wordService: WordService
) : WordRepository {

    override suspend fun fetchWord(word: String): Word {
        val myWord = wordService.getWord(word)?.firstOrNull()

        val definitions = myWord?.meanings?.map { meaning ->
            meaning.definitions.map {
                Definition(meaning.partOfSpeech, it.definition, it.example)
            }
        }?.flatten()

        val synonyms = myWord?.meanings?.flatMap {
            it.synonyms ?: listOf()
        }

        val antonyms = myWord?.meanings?.flatMap {
            it.antonyms ?: listOf()
        }


        val wordItself = myWord?.word
        var phonetic = ""
        var audio = ""
        val audios = mutableListOf<String>()

        if (myWord?.phonetic != null && myWord.phonetic.isNotEmpty()) {
            phonetic = myWord.phonetic
        }

        if (myWord?.phonetics != null && myWord.phonetics.isNotEmpty()) {
            for (i in 0..<myWord.phonetics.size) {
                myWord.phonetics[0].audio?.let {
                    if (it.isNotEmpty()){
                        audios.add(it)
                    }
                }
            }
        }

        if (audios.isNotEmpty()){
            audio = audios[0]
        }




        return Word(wordItself.orEmpty(), phonetic, audio ,definitions.orEmpty(), synonyms.orEmpty(), antonyms.orEmpty())


//            ?.map {
//                val sizeMeaning = it.meanings.size
//                val wordList = mutableListOf<Word>()
//
//                for (i in 0..<sizeMeaning) {
//                    val sizeDefinition = it.meanings[i].definitions.size
//                    val definitionList = mutableListOf<Definition>()
//
//                    for (j in 0..<sizeDefinition) {
//                        val definition = Definition(
//                            it.meanings[i].partOfSpeech,
//                            it.meanings[i].definitions[j].definition,
//                            it.meanings[i].definitions[j].example ?: ""
//                        )
//                        definitionList.add(definition)
//                    }
//
//                    wordList.add(word)
//
//
//                    val word = Word(definitionList, arrayListOf(), arrayListOf())
//                }
//
//                return Word()djhfyvydfg
//
//            }
    }

}