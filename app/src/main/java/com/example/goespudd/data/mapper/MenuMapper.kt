package com.example.goespudd.data.mapper

import com.example.goespudd.data.model.Menu
import com.example.goespudd.data.source.network.model.menu.MenuItemResponse

fun MenuItemResponse?.toMenu() =
    Menu(
        id = this?.id.orEmpty(),
        name = this?.name.orEmpty(),
        imgUrl = this?.imgUrl.orEmpty(),
        desc = this?.desc.orEmpty(),
        price = this?.price ?: 0.0,
        shopLoc = this?.shopLoc.orEmpty(),
        mapsLoc = this?.mapsLoc.orEmpty(),

    )

fun Collection<MenuItemResponse>?.toMenu() =
    this?.map { it.toMenu() } ?: listOf()