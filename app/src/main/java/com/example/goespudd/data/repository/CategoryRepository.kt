package com.example.goespudd.data.repository

import com.example.goespudd.data.datasource.category.CategoryDataSource
import com.example.goespudd.data.mapper.toCategory
import com.example.goespudd.data.model.Category
import com.example.goespudd.utils.ResultWrapper
import com.example.goespudd.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategory(): Flow<ResultWrapper<List<Category>>>
}

class CategoryRepositoryImpl(
    private val dataSource: CategoryDataSource,
) : CategoryRepository {
    override fun getCategory(): Flow<ResultWrapper<List<Category>>> {
        return proceedFlow {
            dataSource.getCategory().data.toCategory()
        }
    }
}
