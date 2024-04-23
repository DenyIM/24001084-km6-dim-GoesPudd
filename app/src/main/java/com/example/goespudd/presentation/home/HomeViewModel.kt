package com.example.goespudd.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.goespudd.data.model.User
import com.example.goespudd.data.repository.CategoryRepository
import com.example.goespudd.data.repository.MenuRepository
import com.example.goespudd.data.repository.PrefRepository
import com.example.goespudd.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    private val categoryRepository: CategoryRepository,
    private val menuRepository: MenuRepository,
    private val prefRepository: PrefRepository,
    private val userRepository: UserRepository
): ViewModel() {
    fun getMenu(categorySlug: String? = null) =
        menuRepository.getMenu(categorySlug).asLiveData(Dispatchers.IO)
    fun getCategory() = categoryRepository.getCategory().asLiveData(Dispatchers.IO)

    private val _isUsingGridMode = MutableLiveData(false)

    val isUsingGridMode: LiveData<Boolean>
        get() = _isUsingGridMode

    init {
        _isUsingGridMode.value = isUsingGridMode()
    }

    fun changeListMode(){
        val currentValue = _isUsingGridMode.value?:false
        _isUsingGridMode.postValue(!currentValue)
        setUsingGridMode(!currentValue)
    }

    private fun isUsingGridMode() = prefRepository.isUsingGridMode()
    private fun setUsingGridMode(isUsingGridMode: Boolean) =
        prefRepository.setUsingGridMode(isUsingGridMode)

    fun getUser(): User? {
        return userRepository.getCurrentUser()
    }

    fun isLoggedIn(): Boolean {
        return userRepository.isLoggedIn()
    }
}