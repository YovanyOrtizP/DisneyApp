package com.example.disneyapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.disneyapp.R
import com.example.disneyapp.ui.characters.DisneyFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_characters, DisneyFragment())
            .commit()
    }
}