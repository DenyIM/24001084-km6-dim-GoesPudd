package com.example.goespudd.data.repository

import com.example.goespudd.data.datasource.menu.MenuDataSource
import com.example.goespudd.data.model.Menu

interface MenuRepository {
    fun getMenu(): List<Menu>
}

class MenuRepositoryImpl(private val dataSource: MenuDataSource) : MenuRepository {
    override fun getMenu(): List<Menu> = dataSource.getMenu()
}