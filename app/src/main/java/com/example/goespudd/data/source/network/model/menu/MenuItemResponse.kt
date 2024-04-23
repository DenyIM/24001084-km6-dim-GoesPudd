package com.example.goespudd.data.source.network.model.menu

import com.google.gson.annotations.SerializedName

data class MenuItemResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("img_url")
    val imgUrl: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("desc")
    val desc: String?,
    @SerializedName("shop_loc")
    var shopLoc: String?,
    @SerializedName("maps_loc")
    var mapsLoc: String?
)
