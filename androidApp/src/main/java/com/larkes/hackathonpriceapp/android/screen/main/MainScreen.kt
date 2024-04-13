package com.larkes.hackathonpriceapp.android.screen.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.larkes.hackathonpriceapp.android.navigation.Screen
import com.larkes.hackathonpriceapp.android.screen.main.views.CameraView
import com.larkes.hackathonpriceapp.di.InjectUseCase
import com.larkes.hackathonpriceapp.domain.model.AuthData
import com.larkes.hackathonpriceapp.domain.model.PerformedPrice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    navController:NavController
) {

    val permission = Manifest.permission.CAMERA
    val context = LocalContext.current


    val cameraIsGranted = remember{
        mutableStateOf<Boolean?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        cameraIsGranted.value = isGranted
    }

    LaunchedEffect(Unit){
        checkAndRequestCameraPermission(context, permission, launcher)
    }

    CameraView(){

    }


    Text("main")
    Button(onClick = {
        navController.navigate(Screen.HistoryScreen.route)
    }) {
        Text("click")
    }
}

fun checkAndRequestCameraPermission(
    context: Context,
    permission: String,
    launcher: ManagedActivityResultLauncher<String, Boolean>
) {
    val permissionCheckResult = ContextCompat.checkSelfPermission(context, permission)
    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
        // Open camera because permission is already granted
    } else {
        // Request a permission
        launcher.launch(permission)
    }
}