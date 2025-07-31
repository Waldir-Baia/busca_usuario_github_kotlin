package br.com.waldirbaia.buscausuario.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.activityViewModels
import br.com.waldirbaia.buscausuario.R
import br.com.waldirbaia.buscausuario.databinding.FragmentSearchUserBinding
import br.com.waldirbaia.buscausuario.domain.entity.User
import br.com.waldirbaia.buscausuario.presentation.ui.adapter.UserAdapter
import br.com.waldirbaia.buscausuario.presentation.ui.listener.UserClickedListener
import br.com.waldirbaia.buscausuario.presentation.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchUserFragment : Fragment() {
    private var _binding: FragmentSearchUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var userAdapter: UserAdapter

    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        configureObservers()
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter(object : UserClickedListener {
            override fun onUserClicked(viewUser: User) {
                val detailUserFragment = DetailUserFragment()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, detailUserFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })
        binding.recyclerViewUsers.adapter = userAdapter
        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun configureObservers() {
        viewModel.user.observe(viewLifecycleOwner) { result ->
            val listUser = if (result != null) listOf(result) else emptyList()
            userAdapter.submitList(listUser)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): SearchUserFragment {
            return SearchUserFragment()
        }
    }
}

