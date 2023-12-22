package com.saksham.jetpack.serversyncui.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saksham.jetpack.serversyncui.R
import com.saksham.jetpack.serversyncui.domain.model.UiDataObject
import com.saksham.jetpack.serversyncui.domain.model.UiType
import com.saksham.jetpack.serversyncui.ui.theme.ServerSyncUITheme
import com.saksham.jetpack.serversyncui.utils.AnimatedShimmer
import com.saksham.jetpack.serversyncui.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ServerSyncUiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ServerSyncUITheme {
                GetServerSyncUiData(applicationContext = applicationContext, viewModel = viewModel)
            }
        }
    }
}


@Composable
private fun GetServerSyncUiData(
    applicationContext: Context,
    viewModel: ServerSyncUiViewModel
) {
    val dataResult by viewModel.getServerSyncUiData.collectAsState(initial = NetworkResult.Loading())
    when (dataResult) {
        is NetworkResult.Error -> ShowError()
        is NetworkResult.Loading -> ShowLoading()
        is NetworkResult.Success -> dataResult.data?.data.let { value ->
            if (value != null) {
                SetView(list = value, context = applicationContext)
            } else {
                ShowError()
            }
        }
    }
}

@Composable
fun SetView(
    list: ArrayList<UiDataObject>, context: Context
) {
    list.forEach { value ->
        CheckUIType(value = value, context = context)
    }
}


@Preview(showBackground = true)
@Composable
private fun ShowError() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.internal_server_error)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowLoading() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        repeat(10) {
            AnimatedShimmer()
        }
    }
}


@Composable
private fun CheckUIType(
    value: UiDataObject,
    context: Context,
) {
    when (value.type) {
        UiType.SCAFFOLD -> ShowScaffold(value, context)
        UiType.APP_BAR -> ShowAppBar(value, context)
        UiType.CARD_VIEW -> ShowCardView(value = value, context = context)
        UiType.TEXT -> ShowText(value)
        UiType.IMAGE -> ShowImage(value, context)
        UiType.VERTICAL_LIST -> VerticalList(value, context)
        UiType.HORIZONTAL_LIST -> HorizontalList(value, context)
        UiType.ROW -> ShowRowView(value, context)
        UiType.COLUMN -> ShowColumnView(value, context)
        UiType.SPACE -> Spacer(modifier = Modifier.height(20.dp))
        UiType.SEARCH_BAR -> ShowSearchBar(value, context)
    }
}