package br.com.caelum.twittelumappweb.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.databinding.ActivityMainBinding
import br.com.caelum.twittelumappweb.fragment.BuscadorDeTweetsFragment
import br.com.caelum.twittelumappweb.fragment.ListaTweetsFragment
import br.com.caelum.twittelumappweb.fragment.MapaFragment
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.SupportMapFragment


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private val tweetViewModel: TweetViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tweetViewModel.carregaLista()

        listenerBottomNavigation()
        binding.bottomNavigation.selectedItemId = R.id.menu_tweets

        binding.fabMain.setOnClickListener {
            val intent = Intent(this, TweetActivity::class.java)
            startActivity(intent)
        }
    }

    private fun listenerBottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_tweets -> {
                    exibe(ListaTweetsFragment())
                    true
                }
                R.id.menu_busca -> {
                    exibe(BuscadorDeTweetsFragment())
                    true
                }

                R.id.menu_mapa -> {
                    exibe(MapaFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun exibe(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_principal, fragment)
        transaction.commit()
    }

}

