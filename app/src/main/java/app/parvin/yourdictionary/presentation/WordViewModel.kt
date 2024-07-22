package app.parvin.yourdictionary.presentation

import android.content.res.Resources.NotFoundException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.parvin.yourdictionary.domain.WordRepository
import app.parvin.yourdictionary.model.domain.Definition
import app.parvin.yourdictionary.model.domain.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class WordViewModel @Inject constructor(
    private val repository: WordRepository
) : ViewModel() {

    private val _event = Channel<WordEvent>()
    val event = _event.receiveAsFlow()

    private val _word = MutableStateFlow<Word?>(null)
    val word =  _word.asStateFlow()


    fun fetchWord(word: String) {
        viewModelScope.launch {
            try {
                _word.value = repository.fetchWord(word)
                _event.send(WordEvent.WordSuccessfullyFetched)
            } catch (e: IOException) {
                _event.send(WordEvent.Error)
            } catch (e: HttpException) {
                _event.send(WordEvent.NoWordFound)
            }
        }
    }
}


sealed interface WordEvent {
    data object WordSuccessfullyFetched : WordEvent
    data object Error : WordEvent
    data object NoWordFound : WordEvent
}
