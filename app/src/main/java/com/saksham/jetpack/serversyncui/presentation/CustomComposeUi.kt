package com.saksham.jetpack.serversyncui.presentation

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.saksham.jetpack.serversyncui.domain.model.UiDataObject
import com.saksham.jetpack.serversyncui.utils.color


@Composable
fun ShowScaffold(
    value: UiDataObject, applicationContext: Context,
) {
    Scaffold(topBar = {
        SetView(value.topBar, applicationContext)
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SetView(list = value.children, context = applicationContext)
        }
    }
}


@Composable
fun ShowCardView(value: UiDataObject, context: Context) {
    Card(
        Modifier
            .padding(horizontal = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = value.colorCode.color
        )
    ) {
        SetView(list = value.children, context = context)
    }
}


@Composable
fun ShowText(
    value: UiDataObject
) {
    Text(text = value.value, color = value.colorCode.color)
}


@Composable
fun VerticalList(value: UiDataObject, context: Context) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            SetView(list = value.children, context = context)
        }

    }
}


@Composable
fun HorizontalList(value: UiDataObject, context: Context) {
    LazyRow(modifier = Modifier.fillMaxSize()) {
        item {
            SetView(list = value.children, context = context)
        }
    }
}

@Composable
fun ShowRowView(value: UiDataObject, context: Context) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SetView(list = value.children, context = context)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowAppBar(value: UiDataObject, context: Context) {
    TopAppBar(
        title = {
            SetView(list = value.children, context = context)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        ),
    )
}


@Composable
fun ShowColumnView(value: UiDataObject, context: Context) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SetView(list = value.children, context = context)
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ShowImage(value: UiDataObject, context: Context) {
    Image(
        painter = rememberImagePainter(data = value.value,
            builder = { transformations(CircleCropTransformation()) }),
        contentDescription = null,
        modifier = Modifier
            .size(80.dp)
    )
}