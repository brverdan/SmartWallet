package com.infnet.smartwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationTickets.setupWithNavController(findNavController(R.id.navHostFragment))

       // val piru: BottomNavigationView = findViewById(R.id.bottomNavigationTickets)
        // piru.visibility = View.GONE

    }
}