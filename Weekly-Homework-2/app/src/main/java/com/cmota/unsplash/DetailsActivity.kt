package com.cmota.unsplash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.cmota.unsplash.api.UnsplashProvider
import com.cmota.unsplash.data.cb.UnsplashResult
import com.cmota.unsplash.data.images.UnsplashItem
import com.cmota.unsplash.ui.DetailsScreen
import com.cmota.unsplash.ui.theme.UnsplashTheme

class DetailsActivity : ComponentActivity() {

  private val provider = UnsplashProvider()

  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    val item = intent.getParcelableExtra<UnsplashItem>("image")
    val id = item?.id

    val photoState = mutableStateOf<UnsplashItem?>(null)
    val errorState = mutableStateOf<String?>(null)
    val loadingState = mutableStateOf(true)

    if (id != null) {
      provider.fetchPhoto(id, object : UnsplashResult {
        override fun onDataFetchedSuccess(images: List<UnsplashItem>) {
          photoState.value = images.firstOrNull()
          loadingState.value = false
        }
        override fun onDataCollectionFetchedSuccess(collections: List<com.cmota.unsplash.data.collections.UnsplashCollectionItem>) {}
        override fun onDataFetchedFailed(message: String) {
          errorState.value = message
          loadingState.value = false
        }
      })
    } else {
      errorState.value = "No photo id provided."
      loadingState.value = false
    }

    setContent {
      UnsplashTheme {
        Scaffold(
          modifier = Modifier.fillMaxSize(),
          topBar = {
            TopAppBar(
              navigationIcon = {
                IconButton(onClick = { finish() }) {
                  Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back"
                  )
                }
              },
              title = {
                Text(stringResource(R.string.app_name))
              },
              colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.Black,
                navigationIconContentColor = Color.Black
              )
            )
          },
          content = { innerPadding ->
            Column(
              modifier = Modifier.padding(innerPadding),
            ) {
              when {
                photoState.value != null -> {
                  DetailsScreen(
                    item = photoState.value!!,
                    onAction = { /* TODO: handle image click */ }
                  )
                }
              }
            }
          }
        )
      }
    }
  }
}