package com.cmota.unsplash.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
  onAction: (Int) -> Unit = {},
  onDownloadClick: () -> Unit = {},
  onLikeClick: () -> Unit = {},
  onBookmarkClick: () -> Unit = {},
  onTagClick: (String) -> Unit = {}
) {

  var isDownloaded by remember { mutableStateOf(false) }
  var isLiked by remember { mutableStateOf(false) }
  var isBookmarked by remember { mutableStateOf(false) }
  Surface(
    modifier = Modifier.fillMaxSize(),
    color = Color.Black
  ) {
    Column {
      Box(modifier = Modifier.height(320.dp)) {
        Image(
          painter = painterResource(image),
          modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .clickable { onAction(image) },
          contentScale = ContentScale.Crop,
          contentDescription = stringResource(R.string.description_bcn_sagrada_familia)
        )
        Row(
          modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(start = 16.dp, bottom = 16.dp)
            .background(Color(0x33000000), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            Icons.Filled.LocationOn,
            contentDescription = null,
            tint = Color.White
          )
          Spacer(Modifier.width(10.dp))
          Text(
            text = stringResource(R.string.location_bcn),
            color = Color.White,
            fontWeight = FontWeight.Bold
          )
        }
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
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
          text = stringResource(R.string.author_name),
          color = Color.White,
          modifier = Modifier.weight(1f),
          fontWeight = FontWeight.Medium
        )
        // Download button
        IconButton(onClick = {
          isDownloaded = !isDownloaded
          onDownloadClick()
        }) {
          if (isDownloaded) {
            Icon(Icons.Default.Download, contentDescription = null, tint = Color.White)
          } else {
            Icon(
              painter = painterResource(id = R.drawable.ic_download_outline),
              contentDescription = null,
              tint = Color.White
            )
          }
        }
        // Like button
        IconButton(onClick = {
          isLiked = !isLiked
          onLikeClick()
        }) {
          if (isLiked) {
            Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.White)
          } else {
            Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = Color.White)
          }
        }
        // Bookmark button
        IconButton(onClick = {
          isBookmarked = !isBookmarked
          onBookmarkClick()
        }) {
          if (isBookmarked) {
            Icon(Icons.Default.Bookmark, contentDescription = null, tint = Color.White)
          } else {
            Icon(Icons.Default.BookmarkBorder, contentDescription = null, tint = Color.White)
          }
        }
      }
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color.Black)
          .padding(horizontal = 0.dp, vertical = 0.dp)
      ) {
        Divider(color = Color.DarkGray, thickness = 1.dp)
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
        Divider(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
          thickness = 1.dp,
          color = Color.DarkGray
        )
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceEvenly
        ) {
          StatsColumn(R.string.info_views, R.string.info_views_placeholder)
          StatsColumn(R.string.info_downloads, R.string.info_downloads_placeholder)
          StatsColumn(R.string.info_likes, R.string.info_likes_placeholder)
        }
        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          TagChip(textRes = R.string.tag_barcelona, onClick = { onTagClick("barcelona") })
          TagChip(textRes = R.string.tag_spain, onClick = { onTagClick("spain") })
        }
        Spacer(modifier = Modifier.height(16.dp))
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
private fun TagChip(@StringRes textRes: Int, onClick: () -> Unit = {}) {
  Text(
    text = stringResource(textRes),
    color = Color.White,
    modifier = Modifier
      .background(
        color = Color(0x33FFFFFF),
        shape = RoundedCornerShape(32.dp)
      )
      .clickable { onClick() }
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