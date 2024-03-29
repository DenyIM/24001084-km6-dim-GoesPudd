package com.example.goespudd.presentation.home

import androidx.lifecycle.ViewModel
import com.example.goespudd.data.datasource.menu.MenuDataSource
import com.example.goespudd.data.repository.CategoryRepository
import com.example.goespudd.data.repository.MenuRepository

class HomeViewModel(
    private val categoryRepository: CategoryRepository,
    private val menuRepository: MenuRepository
): ViewModel() {
    fun getMenu() = menuRepository.getMenu()
    fun getCategory() = categoryRepository.getCategory()
}