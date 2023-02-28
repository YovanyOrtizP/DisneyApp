package com.example.disneyapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.disneyapp.R
import com.example.disneyapp.ui.characters.DisneyFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        supportActionBar?.hide()
        setupActionBarWithNavController(navController)

//        supportFragmentManager.beginTransaction().replace(R.id.fragment_characters, DisneyFragment())
//            .commit()
    }
}