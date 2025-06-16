package com.cmota.unsplash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cmota.unsplash.R

@Composable
fun MainScreen(
  onAction: (Int) -> Unit
) {
  val images = listOf(
    R.drawable.bcn_la_sagrada_familia,
    R.drawable.bcn_casa_battlo,
    R.drawable.bcn_palau_montjuic,
    R.drawable.bcn_parc_guell,
    R.drawable.bcn_parc_guell_2
  )

  val search = remember { mutableStateOf("") }

  LazyColumn(
    contentPadding = PaddingValues(16.dp),
  ) {
    item {
      OutlinedTextField(
        value = search.value,
        onValueChange = { search.value = it },
        label = { Text("Search") },
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 16.dp)
      )
    }
    items(images) { image ->
        Image(
          modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = { onAction(image) }),
          painter = painterResource(image),
          contentDescription = "Image",
          contentScale = ContentScale.Crop
        )
    }
  }
}