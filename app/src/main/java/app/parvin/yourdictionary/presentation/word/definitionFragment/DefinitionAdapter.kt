package app.parvin.yourdictionary.presentation.word.definitionFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.parvin.yourdictionary.databinding.ItemDefinitionBinding
import app.parvin.yourdictionary.model.domain.Definition

class DefinitionAdapter :
    ListAdapter<Definition, DefinitionAdapter.DefinitionViewHolder>(callback) {

    inner class DefinitionViewHolder(private val binding: ItemDefinitionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(definition: Definition) {
            with(binding) {
                textViewDefinition.text = definition.definition
                textViewExample.text = "Ex: ${definition.example}"
                textViewPartOfSpeech.text = definition.partOfSpeech

                if (definition.example.isNullOrEmpty()) {
                    textViewExample.isVisible = false
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
        return DefinitionViewHolder(
            ItemDefinitionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

private val callback = object : DiffUtil.ItemCallback<Definition>() {
    override fun areItemsTheSame(oldItem: Definition, newItem: Definition): Boolean {
        return oldItem.definition == newItem.definition
    }

    override fun areContentsTheSame(oldItem: Definition, newItem: Definition): Boolean {
        return oldItem == newItem
    }
}