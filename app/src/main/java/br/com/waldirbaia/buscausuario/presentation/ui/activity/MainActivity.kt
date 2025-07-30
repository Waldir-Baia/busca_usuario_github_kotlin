package br.com.waldirbaia.buscausuario.presentation.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.waldirbaia.buscausuario.R
import br.com.waldirbaia.buscausuario.databinding.ActivityMainBinding
import br.com.waldirbaia.buscausuario.domain.entity.User
import br.com.waldirbaia.buscausuario.presentation.ui.adapter.UserAdapter
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
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainRecyclerview.layoutManager = LinearLayoutManager(this)

        configureObservers()
    }

    private fun configureObservers(){
        viewModel.user.observe(this){result ->
            updateUIResult(listOf(result))
        }
    }

    private fun updateUIResult(listUser: List<User?>){
        if(!::userAdapter.isInitialized){
            userAdapter = UserAdapter(object : UserClickedListener{
                override fun onUserClicked(viewUser: User) {
                }
            })
            binding.mainRecyclerview.adapter = userAdapter
        }
        userAdapter.submitList(listUser)
    }






}