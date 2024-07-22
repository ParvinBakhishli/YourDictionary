package app.parvin.yourdictionary.presentation.word.antonymFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.parvin.yourdictionary.R
import app.parvin.yourdictionary.databinding.FragmentAntonymBinding
import app.parvin.yourdictionary.presentation.WordViewModel
import app.parvin.yourdictionary.presentation.word.synonymsFragment.SynonymAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AntonymFragment : Fragment(R.layout.fragment_antonym) {

    private var _binding: FragmentAntonymBinding? = null
    private val binding: FragmentAntonymBinding get() = _binding!!

    private val viewModel by activityViewModels<WordViewModel> ()
    private val antonymAdapter by lazy {
        SynonymAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentAntonymBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewAntonyms.adapter = antonymAdapter

        lifecycleScope.launch {
            viewModel.word.collectLatest {
                antonymAdapter.submitList(it?.antonyms)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}