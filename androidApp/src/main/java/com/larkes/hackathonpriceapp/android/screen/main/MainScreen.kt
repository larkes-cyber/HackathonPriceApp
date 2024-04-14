package com.larkes.hackathonpriceapp.android.screen.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
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
import com.larkes.hackathonpriceapp.android.screen.main.views.CompletedSheet
import com.larkes.hackathonpriceapp.android.screen.main.views.EditPriceSheetView
import com.larkes.hackathonpriceapp.android.screen.main.views.MainView
import com.larkes.hackathonpriceapp.android.viewmodels.auth.AuthViewModel
import com.larkes.hackathonpriceapp.android.viewmodels.main.MainViewModel
import com.larkes.hackathonpriceapp.android.viewmodels.main.models.MainAction
import com.larkes.hackathonpriceapp.android.viewmodels.main.models.MainEvent
import com.larkes.hackathonpriceapp.di.InjectUseCase
import com.larkes.hackathonpriceapp.domain.model.AuthData
import com.larkes.hackathonpriceapp.domain.model.PerformedPrice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController:NavController,
    viewModel: MainViewModel
) {
    val scope = rememberCoroutineScope()

    val viewState by viewModel.mainUIState.collectAsState()
    val viewAction by viewModel.mainAction.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(viewAction){

    }

    MainView(viewState){
        viewModel.onEvent(it)
    }

    if(viewAction is MainAction.OpenEditPriceSheet){
        EditPriceSheetView(
            viewState = viewState,
            sheetState = sheetState
        ){
            viewModel.onEvent(it)
        }
    }

        if(viewAction is MainAction.OpenCompletedSheet){
        CompletedSheet(
            sheetState = sheetState
        ){
            viewModel.onEvent(it)
        }
    }




}

