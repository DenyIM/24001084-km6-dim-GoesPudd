package com.example.goespudd.data.datasource.menu

import com.example.goespudd.data.source.network.model.menu.MenuResponse
import com.example.goespudd.data.source.network.services.GoespuddApiService

class MenuApiDataSource(private val service: GoespuddApiService): MenuDataSource {
    override suspend fun getMenu(categorySlug: String?): MenuResponse {
        return service.getMenu(categorySlug)
    }
}