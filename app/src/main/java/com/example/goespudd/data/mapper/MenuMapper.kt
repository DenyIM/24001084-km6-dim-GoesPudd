package com.example.goespudd.data.mapper

import com.example.goespudd.data.model.Menu
import com.example.goespudd.data.source.network.model.menu.MenuItemResponse

fun MenuItemResponse?.toMenu() =
    Menu(
        imgUrl = this?.imgUrl.orEmpty(),
        shopLoc = this?.restoAddress.orEmpty(),
        mapsLoc = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77",
        price = this?.price ?: 0.0,
        name = this?.name.orEmpty(),
        desc = this?.detail.orEmpty(),
    )

fun Collection<MenuItemResponse>?.toMenu() =
    this?.map {
        it.toMenu()
    } ?: listOf()
