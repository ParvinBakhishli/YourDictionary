package app.parvin.yourdictionary.presentation.word

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import app.parvin.yourdictionary.R
import app.parvin.yourdictionary.databinding.ActivityMainBinding
import app.parvin.yourdictionary.databinding.FragmentWordBinding
import app.parvin.yourdictionary.presentation.WordViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.awaitAll

@AndroidEntryPoint
class WordFragment : Fragment(R.layout.fragment_word) {

    private var _binding: FragmentWordBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<WordViewModel>()

    private val args: WordFragmentArgs by navArgs()
    private val audio by lazy { viewModel.word.value?.audio }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentWordBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)


        viewModel.fetchWord(args.word)

        binding.textViewWord.text = viewModel.word.value?.word ?: ""
        binding.textViewPhonetic.text = viewModel.word.value?.phonetic ?: ""

        val pagerAdapter = PagerAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = pagerAdapter


        setUpTabLayout()
        makeTabInvisible()
        binding.setUpListeners()

    }

    private fun playAudio(url : String) {
        val mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare()
            start()
        }
    }

    private fun FragmentWordBinding.setUpListeners() {
        binding.imageViewAudio.setOnClickListener {
            if (audio?.isNotEmpty() == true) {
                playAudio(audio!!)
            } else {
                binding.imageViewAudio.visibility = View.GONE
            }
        }
    }

    private fun setUpTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = "Definitions"
                1 -> tab.text = "Synonyms"
                2 -> tab.text = "Antonyms"
            }
        }.attach()
    }

    private fun makeTabInvisible() {
        val tabSynonym = binding.tabLayout.getTabAt(1)
        val tabAntonym = binding.tabLayout.getTabAt(2)

        if (viewModel.word.value?.synonyms?.isEmpty() == true) {
            tabSynonym?.view?.visibility = View.GONE

        } else {
            tabSynonym?.view?.visibility = View.VISIBLE
        }

        if (viewModel.word.value?.antonyms?.isEmpty() == true) {
            tabAntonym?.view?.visibility = View.GONE
        } else {
            tabAntonym?.view?.visibility = View.VISIBLE
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}