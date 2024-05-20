package com.example.goespudd.data.mapper

import com.example.goespudd.data.model.Category
import com.example.goespudd.data.source.network.model.category.CategoryItemResponse

fun CategoryItemResponse?.toCategory() =
    Category(
        name = this?.name.orEmpty(),
        imgUrl = this?.imgUrl.orEmpty(),
    )

fun Collection<CategoryItemResponse>?.toCategory() =
    this?.map {
        it.toCategory()
    } ?: listOf()
