package com.cmota.unsplash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cmota.unsplash.ui.AboutScreen
import com.cmota.unsplash.ui.DetailsScreen
import com.cmota.unsplash.ui.MainScreen
import com.cmota.unsplash.ui.theme.UnsplashTheme

class MainActivity : ComponentActivity() {

  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      UnsplashTheme {
        Scaffold(
          modifier = Modifier.fillMaxSize(),
          topBar = {
            CenterAlignedTopAppBar(
              title = {
                Text(stringResource(R.string.app_name))
              }
            )
          },
          floatingActionButton = {
            FloatingActionButton(
              onClick = {
                Toast.makeText(
                  this@MainActivity,
                  "Share",
                  Toast.LENGTH_SHORT
                ).show()
              }
            ) {
              Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
              )
            }
          }
        ) { innerPadding ->
          Column(
            modifier = Modifier.padding(innerPadding),

          ) {
            MainScreen(
              onAction = { image ->
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("image", image)
                startActivity(intent)
              }
            )
          }
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  UnsplashTheme {
    AboutScreen()
  }
}