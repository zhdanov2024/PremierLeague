package com.androidacademy.premierleaguefixtures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidacademy.premierleaguefixtures.databinding.ItemMatchBinding

class MatchAdapter(
    private val matchList: List<MatchDetails>,
    private val clickListener: OnMatchClickListener
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    interface OnMatchClickListener {
        fun onMatchClick(match: MatchDetails)
    }

    inner class MatchViewHolder(private val binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(match: MatchDetails) {
            binding.matchDetails = match
            binding.root.setOnClickListener {
                clickListener.onMatchClick(match)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMatchBinding.inflate(inflater, parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matchList[position])
    }

    override fun getItemCount(): Int = matchList.size
}
