package app.parvin.yourdictionary.presentation.word.definitionFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.parvin.yourdictionary.R
import app.parvin.yourdictionary.databinding.FragmentDefinitionBinding
import app.parvin.yourdictionary.presentation.WordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DefinitionFragment : Fragment(R.layout.fragment_definition) {

    private var _binding: FragmentDefinitionBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val viewModel by activityViewModels<WordViewModel> ()
    private val definitionAdapter by lazy {
        DefinitionAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDefinitionBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewDefinition.adapter = definitionAdapter

        lifecycleScope.launch {

            viewModel.word.collectLatest {
                definitionAdapter.submitList(it?.definition)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}