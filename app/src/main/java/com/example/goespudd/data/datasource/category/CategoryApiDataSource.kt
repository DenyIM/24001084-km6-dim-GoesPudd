package com.example.goespudd.data.datasource.category

import com.example.goespudd.data.source.network.model.category.CategoryResponse
import com.example.goespudd.data.source.network.services.GoespuddApiService

class CategoryApiDataSource(private val service: GoespuddApiService): CategoryDataSource {
    override suspend fun getCategory(): CategoryResponse {
        return service.getCategory()
    }
}