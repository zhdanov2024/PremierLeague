package com.androidacademy.premierleaguefixtures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidacademy.premierleaguefixtures.databinding.ItemMatchBinding

class MatchAdapter(
    private var matches: List<MatchDetails>,
    private val clickListener: (MatchDetails) -> Unit
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matches[position], clickListener)
    }

    override fun getItemCount() = matches.size

    fun updateMatches(newMatches: List<MatchDetails>) {
        matches = newMatches
        notifyDataSetChanged()
    }

    class MatchViewHolder(private val binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(match: MatchDetails, clickListener: (MatchDetails) -> Unit) {
            binding.matchDetails = match
            binding.root.setOnClickListener {
                clickListener(match)
            }
            binding.executePendingBindings()
        }
    }
}
