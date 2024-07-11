package com.androidacademy.premierleaguefixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
        MatchViewModel(MatchRepository(AppDatabase.getDatabase(requireContext()).matchDetailsDao(), NetworkModule.matchApiService))
    }
    private lateinit var adapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MatchAdapter(emptyList()) { match ->
            val bundle = Bundle().apply {
                putParcelable("matchDetails", match)
            }
            // Navigate to SecondFragment with the match details
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment, bundle)
        }
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3) // 3 columns
        binding.recyclerView.adapter = adapter

        binding.composeView.setContent {
            MaterialTheme {
                Surface {
                    Column {
                        SearchBar { query ->
                            viewModel.searchMatches(query)
                        }
                        MatchList()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.matches.collect { result ->
                when (result) {
                    is Result.Loading -> {
                        // need to add loading indicator maybe
                    }
                    is Result.Success -> {
                        adapter.updateMatches(result.data)
                    }
                    is Result.Error -> {
                        // need to handle error message
                    }
                }
            }
        }
    }

    @Composable
    fun SearchBar(onSearch: (String) -> Unit) {
        var query by remember { mutableStateOf("") }

        BasicTextField(
            value = query,
            onValueChange = {
                query = it
                onSearch(it)
            },
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 50.dp)
        )
        if (query.isEmpty()) {
            Text(
                text = "write here the name of the team",
                style = LocalTextStyle.current.copy(color = Color.Black),
                modifier = Modifier.padding(start = 70.dp)
            )
        }

    }

    @Composable
    fun MatchList() {
        // Not implemented yet some UI work

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}