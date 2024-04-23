package com.example.goespudd.data.datasource.menu

import com.example.goespudd.data.source.network.model.menu.MenuResponse

interface MenuDataSource {
    suspend fun getMenu(categorySlug: String? = null): MenuResponse
}