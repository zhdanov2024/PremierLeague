package com.androidacademy.premierleaguefixtures
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.androidacademy.premierleaguefixtures.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val matchDetails = MatchDetails(
            mathNumber = 1,
            roundNumber = 1,
            dateUtc = "2024-08-13 19:00:00z",
            location = "Munich Stadium",
            homeTeam = "Bayern",
            awayTeam = "Arsenal",
            group = null,
            homeTeamScore = 2,
            awayTeamScore = 0
        )

        binding.matchDetails = matchDetails

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_second_to_first)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}