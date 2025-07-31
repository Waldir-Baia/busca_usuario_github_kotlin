package br.com.waldirbaia.buscausuario.presentation.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.waldirbaia.buscausuario.R
import br.com.waldirbaia.buscausuario.databinding.FragmentDetailUserBinding
import br.com.waldirbaia.buscausuario.domain.entity.Repository
import br.com.waldirbaia.buscausuario.domain.entity.User
import br.com.waldirbaia.buscausuario.presentation.ui.adapter.RepositoryAdapter
import br.com.waldirbaia.buscausuario.presentation.ui.listener.RepositoryClickedListener
import br.com.waldirbaia.buscausuario.presentation.viewmodel.MainActivityViewModel
import coil.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailUserFragment : Fragment() {
    private lateinit var repositoryAdapter: RepositoryAdapter

    private var _binding: FragmentDetailUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailUserBinding.inflate(inflater, container, false)
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

    private fun setupRecyclerView(){
        repositoryAdapter = RepositoryAdapter(object : RepositoryClickedListener{
            override fun onRepositoryClicked(viewRepository: Repository) {
                val url = viewRepository.html_url
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        })
        binding.fragDetailRecycler.adapter = repositoryAdapter
        binding.fragDetailRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun configureObservers(){
        viewModel.repositoryEntity.observe(viewLifecycleOwner){result ->
            repositoryAdapter.submitList(result)
        }

        viewModel.user.observe(viewLifecycleOwner){result ->
            updateUI(result)
        }
    }

    private fun updateUI(user: User?){
        binding.userName.text = user?.login
        binding.userImage.load(user?.avatar_url){
            crossfade(true)
            placeholder(R.drawable.icon_outline_person)
            error(R.drawable.icon_outline_person)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): DetailUserFragment {
            return DetailUserFragment()
        }
    }
}
