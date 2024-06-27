package com.androidacademy.premierleaguefixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.androidacademy.premierleaguefixtures.databinding.FragmentFirstBinding

class FirstFragment : Fragment(), MatchAdapter.OnMatchClickListener {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val matchList = listOf(
        MatchDetails(1, 1, "2023-08-11 19:00:00Z", "Turf Moor", "Burnley", "Man City", null, 0, 3),
        MatchDetails(2, 1, "2023-08-12 12:00:00Z", "Emirates Stadium", "Arsenal", "Nottingham Forest", null, 2, 1),
        MatchDetails(3, 1, "2023-08-12 14:00:00Z", "Vitality Stadium", "Bournemouth", "West Ham", null, 1, 1),
        MatchDetails(4, 1, "2023-08-12 14:00:00Z", "Amex Stadium", "Brighton", "Luton", null, 4, 1),
        MatchDetails(5, 1, "2023-08-12 14:00:00Z", "Goodison Park", "Everton", "Fulham", null, 0, 1),
        MatchDetails(6, 1, "2023-08-12 14:00:00Z", "Bramall Lane", "Sheffield Utd", "Crystal Palace", null, 0, 1),
        MatchDetails(7, 1, "2023-08-12 16:30:00Z", "St. James' Park", "Newcastle", "Aston Villa", null, 5, 1),
        MatchDetails(8, 1, "2023-08-13 13:00:00Z", "Gtech Community Stadium", "Brentford", "Spurs", null, 2, 2),
        MatchDetails(9, 1, "2023-08-13 15:30:00Z", "Stamford Bridge", "Chelsea", "Liverpool", null, 1, 1),
        MatchDetails(10, 1, "2023-08-14 19:00:00Z", "Old Trafford", "Man Utd", "Wolves", null, 1, 0),
        MatchDetails(17, 2, "2023-08-18 18:45:00Z", "The City Ground", "Nottingham Forest", "Sheffield Utd", null, 2, 1)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MatchAdapter(matchList, this)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 1) // 1 columns
        binding.recyclerView.adapter = adapter
    }

    override fun onMatchClick(match: MatchDetails) {
        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(match)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}