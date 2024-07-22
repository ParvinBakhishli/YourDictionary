package app.parvin.yourdictionary.presentation.word.synonymsFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.parvin.yourdictionary.R
import app.parvin.yourdictionary.databinding.FragmentSynonymBinding
import app.parvin.yourdictionary.presentation.WordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SynonymFragment : Fragment(R.layout.fragment_synonym) {
    private var _binding : FragmentSynonymBinding? = null
    private val binding: FragmentSynonymBinding get() = _binding!!

    private val viewModel by activityViewModels<WordViewModel>()
    private val synonymAdapter by lazy {
        SynonymAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSynonymBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewSynonyms.adapter = synonymAdapter

        lifecycleScope.launch {
            viewModel.word.collectLatest {
                synonymAdapter.submitList(it?.synonyms)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}