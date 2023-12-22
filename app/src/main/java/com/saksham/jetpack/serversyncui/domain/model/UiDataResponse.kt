package com.saksham.jetpack.serversyncui.domain.model

import com.google.firebase.database.PropertyName

data class UiDataResponse(
    val data: ArrayList<UiDataObject> = ArrayList()
)

data class UiDataObject(
    val children: ArrayList<UiDataObject> = ArrayList(),
    @get:PropertyName("top_bar") @set:PropertyName("top_bar") var topBar: ArrayList<UiDataObject> = ArrayList(),
    var type: UiType = UiType.SPACE,
    var value: String = "",
    @get:PropertyName("color_code") @set:PropertyName("color_code")
    var colorCode: String = "#000000"
)

