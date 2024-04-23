package com.example.goespudd.data.mapper

import com.example.goespudd.data.model.Category
import com.example.goespudd.data.source.network.model.category.CategoryItemResponse

fun CategoryItemResponse?.toCategory() =
    Category(
        id = this?.id.orEmpty(),
        name = this?.name.orEmpty(),
        imgUrl = this?.imgUrl.orEmpty(),
        slug = this?.slug.orEmpty()
    )

fun Collection<CategoryItemResponse>?.toCategory() =
    this?.map { it.toCategory() } ?: listOf()