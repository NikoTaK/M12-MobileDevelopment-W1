package com.cmota.unsplash.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmota.unsplash.R

@Composable
fun DetailsScreen(
  @DrawableRes image: Int,
  onAction: (Int) -> Unit = {}
) {
  Surface(
    modifier = Modifier.fillMaxSize(),
    color = Color.Black
  ) {
    Column {
      Image(
        painter = painterResource(image),
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onAction(image) },
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(R.string.description_bcn_sagrada_familia)
      )
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          Icons.Filled.LocationOn,
          contentDescription = null,
          tint = Color.White
        )
        Spacer(Modifier.width(8.dp))
        Text(
          text = stringResource(R.string.location_bcn),
          color = Color.White,
          fontWeight = FontWeight.Bold
        )
      }
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Image(
          painter = painterResource(id = R.drawable.author),
          contentDescription = null,
          modifier = Modifier
            .size(48.dp)
            .padding(end = 12.dp)
        )
        Text(
          text = stringResource(R.string.author_name),
          color = Color.White,
          modifier = Modifier.weight(1f),
          fontWeight = FontWeight.Medium
        )
        IconButton(onClick = { /* download */ }) {
          Icon(Icons.Default.Download, contentDescription = null, tint = Color.White)
        }
        IconButton(onClick = { /* like */ }) {
          Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = Color.White)
        }
        IconButton(onClick = { /* bookmark */ }) {
          Icon(Icons.Default.BookmarkBorder, contentDescription = null, tint = Color.White)
        }
      }

      AddPhotoInfoRow(
        title1 = R.string.info_camera,
        subtitle1 = R.string.info_camera_placeholder,
        title2 = R.string.info_aperture,
        subtitle2 = R.string.info_aperture_placeholder
      )
      AddPhotoInfoRow(
        title1 = R.string.info_focal_length,
        subtitle1 = R.string.info_focal_length_placeholder,
        title2 = R.string.info_shutter_speed,
        subtitle2 = R.string.info_shutter_speed_placeholder
      )
      AddPhotoInfoRow(
        title1 = R.string.info_iso,
        subtitle1 = R.string.info_iso_placeholder,
        title2 = R.string.info_dimensions,
        subtitle2 = R.string.info_dimensions_placeholder
      )

      HorizontalDivider(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 16.dp, horizontal = 16.dp),
        thickness = 1.dp,
        color = Color.LightGray
      )

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
      ) {
        StatsColumn(R.string.info_views, R.string.info_views_placeholder)
        StatsColumn(R.string.info_downloads, R.string.info_downloads_placeholder)
        StatsColumn(R.string.info_likes, R.string.info_likes_placeholder)
      }

      FlowRow(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        TagChip(textRes = R.string.tag_barcelona)
        TagChip(textRes = R.string.tag_spain)
      }
    }
  }
}

@Composable
private fun StatsColumn(@StringRes title: Int, @StringRes value: Int) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Text(stringResource(title), color = Color.White)
    Text(stringResource(value), color = Color.White)
  }
}

@Composable
private fun TagChip(@StringRes textRes: Int) {
  Text(
    text = stringResource(textRes),
    color = Color.White,
    modifier = Modifier
      .background(
        color = Color(0x33FFFFFF),
        shape = RoundedCornerShape(32.dp)
      )
      .padding(horizontal = 12.dp, vertical = 6.dp)
  )
}

@Composable
fun AddPhotoInfoRow(
  @StringRes title1: Int,
  @StringRes subtitle1: Int,
  @StringRes title2: Int,
  @StringRes subtitle2: Int
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Column(
      modifier = Modifier
        .weight(1f)
        .padding(end = 8.dp)
    ) {
      Text(
        text = stringResource(id = title1),
        fontWeight = FontWeight.Bold,
        color = Color.White
      )
      Text(
        text = stringResource(id = subtitle1),
        color = Color.White
      )
    }
    Column(
      modifier = Modifier
        .weight(1f)
        .padding(start = 8.dp)
    ) {
      Text(
        text = stringResource(id = title2),
        fontWeight = FontWeight.Bold,
        color = Color.White
      )
      Text(
        text = stringResource(id = subtitle2),
        color = Color.White
      )
    }
  }
}

@Preview
@Composable
fun PreviewDetailsScreen() {
  DetailsScreen(R.drawable.bcn_la_sagrada_familia)
}