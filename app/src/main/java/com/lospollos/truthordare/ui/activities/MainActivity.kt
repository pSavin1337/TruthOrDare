package com.lospollos.truthordare.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.lospollos.truthordare.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onSupportNavigateUp()
            = findNavController(R.id.nav_host).navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host)
    }

    fun openDefinitionPlayersFragment() {
        navController.navigate(R.id.action_launcherFragment_to_definitionPlayersFragment)
    }

    fun openGameFragment() {
        navController.navigate(R.id.action_definitionPlayersFragment_to_gameFragment)
    }

}