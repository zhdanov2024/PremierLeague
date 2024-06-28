package com.androidacademy.premierleaguefixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.androidacademy.premierleaguefixtures.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MatchViewModel by lazy {
        MatchViewModel(MatchRepository(NetworkModule.matchApiService))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MatchAdapter(emptyList()) { match ->
            val bundle = Bundle().apply {
                putParcelable("matchDetails", match)
            }
            // Navigate to SecondFragment with the match details
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment, bundle)
        }
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3) // 3 columns
        binding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.matches.collect { result ->
                when (result) {
                    is Result.Loading -> {
                    }
                    is Result.Success -> {
                        adapter.updateMatches(result.data)
                    }
                    is Result.Error -> {
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}