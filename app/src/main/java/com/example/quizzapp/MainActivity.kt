package com.example.quizzapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizzapp.ui.HomePageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments[0].childFragmentManager.fragments.find{ it is HomePageFragment} != null
            &&
            supportFragmentManager.backStackEntryCount == 0) {
            val dialog = AlertDialog.Builder(this)
                .setTitle("Already leaving?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Are you sure you want to close the app?")
                .setPositiveButton("OK") { _, _ ->
                    this.finish()
                }
                .setNegativeButton("Cancel", null)
                .create()

            dialog.show()
        } else {
            super.onBackPressed()
        }
    }
}