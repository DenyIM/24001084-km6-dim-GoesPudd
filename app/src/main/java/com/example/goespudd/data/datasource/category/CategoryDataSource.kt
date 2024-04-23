package com.example.goespudd.data.datasource.category

import com.example.goespudd.data.source.network.model.category.CategoryResponse

interface CategoryDataSource {
    suspend fun getCategory(): CategoryResponse
}

