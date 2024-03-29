package com.example.goespudd.data.datasource.menu

import com.example.goespudd.data.model.Menu

interface MenuDataSource {
    fun getMenu(): List<Menu>
}