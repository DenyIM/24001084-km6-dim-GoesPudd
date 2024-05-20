package com.example.goespudd.presentation.checkout

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.goespudd.data.model.Cart
import com.example.goespudd.data.model.PriceItem
import com.example.goespudd.data.repository.CartRepository
import com.example.goespudd.data.repository.MenuRepository
import com.example.goespudd.tools.MainCoroutineRule
import com.example.goespudd.tools.getOrAwaitValue
import com.example.goespudd.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CheckoutViewModelTest {
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    lateinit var cartRepository: CartRepository

    @MockK
    lateinit var menuRepository: MenuRepository

    private lateinit var viewModel: CheckoutViewModel

    private val mockCart =
        Cart(
            id = 1,
            menuId = "1",
            menuName = "1",
            menuImgUrl = "1",
            menuPrice = 1.0,
            itemQuantity = 3,
            itemNotes = "1",
        )
    private val mockCart1 =
        Cart(
            id = 1,
            menuId = "1",
            menuName = "1",
            menuImgUrl = "1",
            menuPrice = 1.0,
            itemQuantity = 1,
            itemNotes = "1",
        )
    private val mockListCart = listOf(mockCart, mockCart1)
    private val price1 =
        PriceItem(
            name = "1",
            total = 1.0,
        )
    private val price2 =
        PriceItem(
            name = "2",
            total = 2.0,
        )
    private val mockMenuPrice = listOf(price1, price2)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every {
            cartRepository.getCheckoutData()
        } returns
            flow {
                emit(
                    ResultWrapper.Success(
                        Triple(
                            mockListCart,
                            mockMenuPrice,
                            8000.0,
                        ),
                    ),
                )
            }
        viewModel =
            spyk(
                CheckoutViewModel(
                    cartRepository,
                    menuRepository,
                ),
            )
    }

    @Test
    fun checkoutCart() {
        every {
            menuRepository.createOrder(any())
        } returns
            flow {
                emit(
                    ResultWrapper.Success(true),
                )
            }
        val result = viewModel.checkoutCart().getOrAwaitValue()
        assertEquals(true, result.payload)
        verify {
            menuRepository.createOrder(any())
        }
    }

    @Test
    fun removeCart() {
        coEvery {
            cartRepository.deleteAllCart()
        } returns Unit
        val result = viewModel.removeCart()
        coVerify {
            cartRepository.deleteAllCart()
        }
        assertEquals(Unit, result)
    }
}
