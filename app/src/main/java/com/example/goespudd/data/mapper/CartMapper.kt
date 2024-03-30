package com.example.goespudd.data.mapper

import com.example.goespudd.data.model.Cart
import com.example.goespudd.data.source.local.database.entity.CartEntity

fun Cart?.toCartEntity() = CartEntity(
    id = this?.id,
    menuId = this?.menuId.orEmpty(),
    menuName = this?.menuName.orEmpty(),
    menuPrice = this?.menuPrice ?: 0.0,
    itemQuantity = this?.itemQuantity ?: 0,
    menuImgUrl = this?.menuImgUrl.orEmpty(),
    itemNotes = this?.itemNotes
)

fun CartEntity?.toCart() = Cart(
    id = this?.id,
    menuId = this?.menuId.orEmpty(),
    menuName = this?.menuName.orEmpty(),
    menuPrice = this?.menuPrice ?: 0.0,
    itemQuantity = this?.itemQuantity ?: 0,
    menuImgUrl = this?.menuImgUrl.orEmpty(),
    itemNotes = this?.itemNotes

)

fun List<CartEntity?>.toCartList() = this.map { it.toCart() }