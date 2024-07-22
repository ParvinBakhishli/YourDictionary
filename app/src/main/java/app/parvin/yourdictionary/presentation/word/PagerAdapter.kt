package app.parvin.yourdictionary.presentation.word

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import app.parvin.yourdictionary.presentation.word.antonymFragment.AntonymFragment
import app.parvin.yourdictionary.presentation.word.definitionFragment.DefinitionFragment
import app.parvin.yourdictionary.presentation.word.synonymsFragment.SynonymFragment

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> DefinitionFragment()
            1 -> SynonymFragment()
            2 -> AntonymFragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
}