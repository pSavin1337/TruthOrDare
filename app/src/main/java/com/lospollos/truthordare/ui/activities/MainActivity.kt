package com.lospollos.truthordare.ui.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.lospollos.truthordare.Constants.REQUEST_CODE
import com.lospollos.truthordare.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private var isPermissionsGranted = true
    /*private val permissionRequestLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.forEach { permission ->
                isPermissionsGranted = permission.value && isPermissionsGranted
            }
        }*/

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host).navigateUp()

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                grantResults.forEach { permission ->
                    isPermissionsGranted =
                        (permission == PackageManager.PERMISSION_GRANTED) && isPermissionsGranted
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host)
        //todo launching permissions request is not working
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.MANAGE_DOCUMENTS
            ),
            REQUEST_CODE
        )
        /*permissionRequestLauncher.launch(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.MANAGE_DOCUMENTS
            )
        )*/
    }

    fun openTaskChoosingFragment() {
        navController.navigate(R.id.action_launcherFragment_to_taskChoosingFragment)
    }

    fun openDefinitionPlayersFragment() {
        navController.navigate(R.id.action_taskChoosingFragment_to_definitionPlayersFragment)
    }

    fun openGameFragment() {
        navController.navigate(R.id.action_definitionPlayersFragment_to_gameFragment)
    }

}