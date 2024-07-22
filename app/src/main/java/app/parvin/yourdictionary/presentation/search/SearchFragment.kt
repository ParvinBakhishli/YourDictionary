package app.parvin.yourdictionary.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import app.parvin.yourdictionary.R
import app.parvin.yourdictionary.databinding.FragmentSearchBinding
import app.parvin.yourdictionary.presentation.WordEvent
import app.parvin.yourdictionary.presentation.WordViewModel
import app.parvin.yourdictionary.utils.showNoInternet
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    private val viewModel by activityViewModels<WordViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSearchBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
        binding.textInputLayoutSearch.editText?.setOnEditorActionListener { v, actionId, event ->
            viewModel.fetchWord(v.text.toString())
            true
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.event.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    WordEvent.Error -> {
                        (activity as? AppCompatActivity)?.showNoInternet()
                    }
                    WordEvent.WordSuccessfullyFetched -> {
                        val word = binding.textInputLayoutSearch.editText?.text?.toString()
                        searchForDefinition(word ?: return@collectLatest)
                    }

                    WordEvent.NoWordFound -> {
                        Toast.makeText(context, "No word found", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun searchForDefinition(word: String) {
        //findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToWordFragment(word))
        safeNavigateFromNavController(SearchFragmentDirections.actionSearchFragmentToWordFragment(word))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun Fragment.safeNavigateFromNavController(directions: NavDirections) {
        val navController = findNavController()
        when (val destination = navController.currentDestination) {
            is FragmentNavigator.Destination -> {
                if (javaClass.name == destination.className) {
                    navController.navigate(directions)
                }
            }
            is DialogFragmentNavigator.Destination -> {
                if (javaClass.name == destination.className) {
                    navController.navigate(directions)
                }
            }
        }
    }

}