package com.example.goespudd.data.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Menu(
    var id: String = UUID.randomUUID().toString(),
    var name: String,
    var imgUrl: String,
    var desc: String,
    var price: Double,
    var shopLoc: String,
    var mapsLoc: String
): Parcelable
