package com.example.goespudd.data.model

import java.util.UUID

data class Menu(
    var id: String = UUID.randomUUID().toString(),
    var name: String,
    var imgUrl: String,
    var price: Double,
    var desc: String,
    var shopLoc: String,
    var mapsLoc: String
)
