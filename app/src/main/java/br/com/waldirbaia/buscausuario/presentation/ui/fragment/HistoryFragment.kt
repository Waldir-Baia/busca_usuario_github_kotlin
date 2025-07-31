package br.com.waldirbaia.buscausuario.presentation.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.waldirbaia.buscausuario.R
import br.com.waldirbaia.buscausuario.databinding.FragmentHistoryBinding
import br.com.waldirbaia.buscausuario.domain.entity.HistoryUser
import br.com.waldirbaia.buscausuario.presentation.ui.adapter.HistoryAdapter
import br.com.waldirbaia.buscausuario.presentation.ui.listener.HistoryClickedListener
import br.com.waldirbaia.buscausuario.presentation.viewmodel.HistoryViewModel
import br.com.waldirbaia.buscausuario.presentation.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {
    private lateinit var historyAdapter: HistoryAdapter
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val historyViewModel: HistoryViewModel by viewModels()
    private val mainViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainToolbar.setNavigationOnClickListener {
            val searchUser = SearchUserFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, searchUser)
                .addToBackStack(null)
                .commit()
        }

        setupRecyclerView()
        configureObservers()

    }

    private fun setupRecyclerView() {
        historyAdapter = HistoryAdapter(object : HistoryClickedListener {
            override fun onHistoryClicked(viewHistory: HistoryUser) {
                mainViewModel.preparedData(viewHistory.user.toString())
                val searchUser = SearchUserFragment()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, searchUser)
                    .addToBackStack(null)
                    .commit()
            }
        })
        binding.fragHistoryRecyler.adapter = historyAdapter
        binding.fragHistoryRecyler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun configureObservers() {
        historyViewModel.historyUser.observe(viewLifecycleOwner) { result ->
            historyAdapter.submitList(result)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }
}