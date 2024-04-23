package com.example.goespudd.data.repository

import com.example.goespudd.data.datasource.menu.MenuDataSource
import com.example.goespudd.data.mapper.toMenu
import com.example.goespudd.data.model.Menu
import com.example.goespudd.utils.ResultWrapper
import com.example.goespudd.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    fun getMenu(categorySlug: String? = null): Flow<ResultWrapper<List<Menu>>>
}

class MenuRepositoryImpl(
    private val dataSource: MenuDataSource
) : MenuRepository {
    override fun getMenu(categorySlug: String?): Flow<ResultWrapper<List<Menu>>> {
        return proceedFlow {
            dataSource.getMenu(categorySlug).data.toMenu()
        }
    }

}