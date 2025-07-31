package br.com.waldirbaia.buscausuario.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.waldirbaia.buscausuario.R
import br.com.waldirbaia.buscausuario.databinding.ActivityMainBinding
import br.com.waldirbaia.buscausuario.domain.entity.User
import br.com.waldirbaia.buscausuario.presentation.ui.adapter.UserAdapter
import br.com.waldirbaia.buscausuario.presentation.ui.fragment.SearchUserFragment
import br.com.waldirbaia.buscausuario.presentation.ui.listener.UserClickedListener
import br.com.waldirbaia.buscausuario.presentation.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.isInitialized

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SearchUserFragment())
            .commit()


        val searchMenuItem = binding.mainBottomAppbar.menu.findItem(R.id.action_search)
        val searchView = searchMenuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let {
//                    viewModel.preparedData(it)
//                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.preparedData(it)
                }
                return true
            }

        })
    }
}