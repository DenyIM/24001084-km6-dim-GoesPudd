package com.example.goespudd.data.repository

import com.example.goespudd.data.datasource.category.CategoryDataSource
import com.example.goespudd.data.model.Category

interface CategoryRepository {
    fun getCategory(): List<Category>
}

class CategoryRepositoryImpl(private val dataSource: CategoryDataSource) : CategoryRepository {
    override fun getCategory(): List<Category> = dataSource.getCategory()
}