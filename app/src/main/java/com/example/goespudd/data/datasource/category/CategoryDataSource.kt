package com.example.goespudd.data.datasource.category

import com.example.goespudd.data.model.Category

interface CategoryDataSource {
    fun getCategory(): List<Category>
}
