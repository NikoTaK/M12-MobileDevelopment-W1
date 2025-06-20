package com.cmota.unsplash.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
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
import coil3.compose.rememberAsyncImagePainter
import com.cmota.unsplash.data.images.UnsplashItem
import com.cmota.unsplash.data.images.User
import com.google.accompanist.flowlayout.FlowRow
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf

@Composable
fun DetailsScreen(
  item: UnsplashItem,
  onAction: () -> Unit = {},
  onDownloadClick: () -> Unit = {},
  onLikeClick: () -> Unit = {},
  onBookmarkClick: () -> Unit = {},
  onTagClick: (String) -> Unit = {}
) {
  var isDownloaded by remember { mutableStateOf(false) }
  var isLiked by remember { mutableStateOf(false) }
  var isBookmarked by remember { mutableStateOf(false) }
  Column {
    Box(modifier = Modifier.height(320.dp)) {
      val painter = rememberAsyncImagePainter(item.urls?.regular)
      Image(
        painter = painter,
        modifier = Modifier
          .fillMaxWidth()
          .height(320.dp)
          .clickable { onAction() },
        contentScale = ContentScale.Crop,
        contentDescription = item.description ?: "-"
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
          text = item.user?.location ?: "-",
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
      val authorPainter = rememberAsyncImagePainter(item.user?.profile_image?.medium)
      Image(
        painter = authorPainter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .size(48.dp)
          .clip(CircleShape)
      )
      Spacer(modifier = Modifier.width(12.dp))
      Text(
        text = item.user?.name ?: "-",
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
          Icon(Icons.Filled.Download, contentDescription = null, tint = Color.White)
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
          Icon(Icons.Filled.Favorite, contentDescription = null, tint = Color.White)
        } else {
          Icon(Icons.Filled.FavoriteBorder, contentDescription = null, tint = Color.White)
        }
      }
      // Bookmark button
      IconButton(onClick = {
        isBookmarked = !isBookmarked
        onBookmarkClick()
      }) {
        if (isBookmarked) {
          Icon(Icons.Filled.Bookmark, contentDescription = null, tint = Color.White)
        } else {
          Icon(Icons.Filled.BookmarkBorder, contentDescription = null, tint = Color.White)
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
        subtitle1 = item.exif?.model ?: "-",
        title2 = R.string.info_aperture,
        subtitle2 = item.exif?.aperture ?: "-"
      )
      AddPhotoInfoRow(
        title1 = R.string.info_focal_length,
        subtitle1 = item.exif?.focal_length ?: "-",
        title2 = R.string.info_shutter_speed,
        subtitle2 = item.exif?.exposure_time ?: "-"
      )
      AddPhotoInfoRow(
        title1 = R.string.info_iso,
        subtitle1 = item.exif?.iso?.toString() ?: "-",
        title2 = R.string.info_dimensions,
        subtitle2 = "${item.width ?: "-"} x ${item.height ?: "-"}"
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
        StatsColumn("Views", item.views?.toString() ?: "-")
        StatsColumn("Downloads", item.downloads?.toString() ?: "-")
        StatsColumn("Likes", item.likes?.toString() ?: "-")
      }
      Spacer(modifier = Modifier.height(16.dp))
      FlowRow(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        // Example tags, replace with real tags if available
        TagChip(text = item.user?.username ?: "-", onClick = { onTagClick(item.user?.username ?: "-") })
      }
      Spacer(modifier = Modifier.height(16.dp))
    }
  }
}

@Composable
private fun StatsColumn(title: String, value: String) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Text(title, color = Color.White)
    Text(value, color = Color.White)
  }
}

@Composable
private fun TagChip(text: String, onClick: () -> Unit = {}) {
  Text(
    text = text,
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
  subtitle1: String,
  @StringRes title2: Int,
  subtitle2: String
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
        text = subtitle1,
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
        text = subtitle2,
        color = Color.White
      )
    }
  }
}