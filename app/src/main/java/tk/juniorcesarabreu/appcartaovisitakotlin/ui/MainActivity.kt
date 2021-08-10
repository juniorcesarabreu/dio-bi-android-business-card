package tk.juniorcesarabreu.appcartaovisitakotlin.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import tk.juniorcesarabreu.appcartaovisitakotlin.App
import tk.juniorcesarabreu.appcartaovisitakotlin.databinding.ActivityMainBinding
import tk.juniorcesarabreu.appcartaovisitakotlin.util.Image

class MainActivity : AppCompatActivity() {

    // Utilizar binding é mais seguro em relação a componentes nulos
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy {
        BusinessCardAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.rvCards.adapter = adapter

        getAllBusinessCard()

        insertListeners()


    }

    private fun insertListeners() {

        binding.fabAdd.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                AddBusinessCardActivity::class.java
            )
            startActivity(intent)
        }

        adapter.listenerShare = { card ->
            Image.share(this, card)
        }
    }

    private fun getAllBusinessCard() {

        mainViewModel.getAll().observe(
            this
        ) { businessCards ->
            adapter.submitList(businessCards)
        }
    }
}