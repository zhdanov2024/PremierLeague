package com.androidacademy.premierleaguefixtures
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        val matchDetails = arguments?.getParcelable<MatchDetails>("matchDetails")

        matchDetails?.let {
            binding.matchDetails = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}