package com.example.goespudd.data.repository

import com.example.goespudd.data.datasource.menu.MenuDataSource
import com.example.goespudd.data.mapper.toMenu
import com.example.goespudd.data.model.Cart
import com.example.goespudd.data.model.Menu
import com.example.goespudd.data.source.network.model.checkout.CheckoutItemPayload
import com.example.goespudd.data.source.network.model.checkout.CheckoutRequestPayload
import com.example.goespudd.utils.ResultWrapper
import com.example.goespudd.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    fun getMenu(categorySlug: String? = null): Flow<ResultWrapper<List<Menu>>>

    fun createOrder(products: List<Cart>): Flow<ResultWrapper<Boolean>>
}

class MenuRepositoryImpl(
    private val dataSource: MenuDataSource,
) : MenuRepository {
    override fun getMenu(categorySlug: String?): Flow<ResultWrapper<List<Menu>>> {
        return proceedFlow {
            dataSource.getMenu(categorySlug).data.toMenu()
        }
    }

    override fun createOrder(products: List<Cart>): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            dataSource.createOrder(
                CheckoutRequestPayload(
                    orders =
                        products.map {
                            CheckoutItemPayload(
                                notes = it.itemNotes,
                                name = it.menuName,
                                quantity = it.itemQuantity,
                            )
                        },
                ),
            ).status ?: false
        }
    }
}
