package com.infnet.smartwallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationTickets.setupWithNavController(findNavController(R.id.navHostFragment))

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationTickets)
        bottomNavigationView.visibility = View.GONE

        MobileAds.initialize(this)

        val mAdView = this.findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()

        mAdView.loadAd(adRequest)
    }
}