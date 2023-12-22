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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Text(
        text = value.value,
        color = value.colorCode.color,
        style = TextStyle(fontSize = value.textSize.sp)
    )
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
        horizontalArrangement = Arrangement.Start,
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
        horizontalAlignment = Alignment.Start
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


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ShowSearchBar(value: UiDataObject, context: Context) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current


    // TextField Colors
    val textFieldColors = TextFieldDefaults.textFieldColors(
        disabledTextColor = contentColorFor(Color.Transparent),
        cursorColor = contentColorFor(Color.Black),
        errorCursorColor = contentColorFor(Color.Transparent),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Search") },
        colors = textFieldColors,
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(CircleShape),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            // Hide the keyboard after submitting the search
            keyboardController?.hide()
            //or hide keyboard
            focusManager.clearFocus()

        })
    )
}