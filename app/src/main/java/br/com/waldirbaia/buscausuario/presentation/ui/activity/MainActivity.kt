package br.com.waldirbaia.buscausuario.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.waldirbaia.buscausuario.R
import br.com.waldirbaia.buscausuario.databinding.ActivityMainBinding
import br.com.waldirbaia.buscausuario.domain.enums.TipoFiltro
import br.com.waldirbaia.buscausuario.domain.entity.User
import br.com.waldirbaia.buscausuario.presentation.ui.adapter.UserAdapter
import br.com.waldirbaia.buscausuario.presentation.ui.fragment.HistoryFragment
import br.com.waldirbaia.buscausuario.presentation.ui.fragment.SearchUserFragment
import br.com.waldirbaia.buscausuario.presentation.ui.listener.UserClickedListener
import br.com.waldirbaia.buscausuario.presentation.viewmodel.MainActivityViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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

        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true

        @Suppress("DEPRECATION")
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)


        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SearchUserFragment())
            .commit()


        val searchMenuItem = binding.mainBottomAppbar.menu.findItem(R.id.action_search)
        val searchView = searchMenuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let {
//                    viewModel.preparedData(it)
//                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.preparedDataa(it)
                }
                return true
            }

        })

        binding.mainBottomAppbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_filter -> {
                    showDialogFilter()
                    true
                }

                R.id.action_history -> {
                    navigationHistory()
                    true
                }
                else -> false
            }
        }
    }

    private fun navigationHistory() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HistoryFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun showDialogFilter() {
        val opcoes = TipoFiltro.values().map { it.descricao }.toTypedArray()

        MaterialAlertDialogBuilder(this)
            .setTitle("Filtrar busca")
            .setItems(opcoes) { _, index ->
                val tipoSelecionado = TipoFiltro.values()[index]
                viewModel.setTipoEnum(tipoSelecionado)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }



}