package com.example.goespudd.presentation.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goespudd.data.repository.CartRepository
import com.example.goespudd.data.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckoutViewModel(
    private val cartRepository: CartRepository,
    private val menuRepository: MenuRepository,
) : ViewModel() {
    val checkoutData = cartRepository.getCheckoutData().asLiveData(Dispatchers.IO)

    fun checkoutCart() =
        menuRepository.createOrder(
            checkoutData.value?.payload?.first.orEmpty(),
        ).asLiveData(Dispatchers.IO)

    fun removeCart() {
        viewModelScope.launch {
            cartRepository.deleteAllCart()
        }
    }
}
