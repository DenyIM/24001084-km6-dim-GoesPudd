package com.example.goespudd.data.repository

import app.cash.turbine.test
import com.example.goespudd.data.datasource.menu.MenuDataSource
import com.example.goespudd.data.model.Cart
import com.example.goespudd.data.source.network.model.checkout.CheckoutResponse
import com.example.goespudd.data.source.network.model.menu.MenuItemResponse
import com.example.goespudd.data.source.network.model.menu.MenuResponse
import com.example.goespudd.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MenuRepositoryImplTest {
    @MockK
    lateinit var ds: MenuDataSource

    private lateinit var repository: MenuRepository

    private val menu1 =
        MenuItemResponse(
            name = "1",
            imgUrl = "1",
            restoAddress = "1",
            detail = "1",
            price = 1.0,
        )
    private val menu2 =
        MenuItemResponse(
            name = "2",
            imgUrl = "2",
            restoAddress = "2",
            detail = "2",
            price = 2.0,
        )
    private val mockListMenu = listOf(menu1, menu2)

    private val cart1 =
        Cart(
            id = 1,
            menuId = "1",
            menuName = "1",
            menuImgUrl = "1",
            menuPrice = 1.0,
            itemQuantity = 1,
            itemNotes = "1",
        )
    private val cart2 =
        Cart(
            id = 2,
            menuId = "2",
            menuName = "2",
            menuImgUrl = "2",
            menuPrice = 2.0,
            itemQuantity = 2,
            itemNotes = "2",
        )
    private val mockCart = listOf(cart1, cart2)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = MenuRepositoryImpl(ds)
    }

    @Test
    fun getMenuLoading() {
        val mockResponse = mockk<MenuResponse>()
        every {
            mockResponse.data
        } returns mockListMenu

        runTest {
            coEvery {
                ds.getMenu()
            } returns mockResponse
            repository.getMenu().map {
                delay(100)
                it
            }.test {
                delay(150)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Loading)
                coVerify {
                    ds.getMenu()
                }
            }
        }
    }

    @Test
    fun getMenuSuccess() {
        val mockResponse = mockk<MenuResponse>()
        every {
            mockResponse.data
        } returns mockListMenu

        runTest {
            coEvery {
                ds.getMenu()
            } returns mockResponse
            repository.getMenu().map {
                delay(100)
                it
            }.test {
                delay(250)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify {
                    ds.getMenu()
                }
            }
        }
    }

    @Test
    fun getMenuError() {
        runTest {
            coEvery {
                ds.getMenu()
            } throws IllegalStateException("Mock Error")
            repository.getMenu().map {
                delay(100)
                it
            }.test {
                delay(250)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Error)
                coVerify {
                    ds.getMenu()
                }
            }
        }
    }

    @Test
    fun getMenuEmpty() {
        val mockListMenu = listOf<MenuItemResponse>()
        val mockResponse = mockk<MenuResponse>()
        every {
            mockResponse.data
        } returns mockListMenu

        runTest {
            coEvery {
                ds.getMenu()
            } returns mockResponse
            repository.getMenu().map {
                delay(100)
                it
            }.test {
                delay(250)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Empty)
                coVerify {
                    ds.getMenu()
                }
            }
        }
    }

    @Test
    fun createOrderLoading() {
        val mockResponse = mockk<CheckoutResponse>(relaxed = true)

        runTest {
            coEvery {
                ds.createOrder(any())
            } returns mockResponse
            repository.createOrder(mockCart).map {
                delay(100)
                it
            }.test {
                delay(150)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Loading)
                coVerify {
                    ds.createOrder(any())
                }
            }
        }
    }

    @Test
    fun createOrderSuccess() {
        val mockResponse = mockk<CheckoutResponse>(relaxed = true)

        runTest {
            coEvery {
                ds.createOrder(any())
            } returns mockResponse
            repository.createOrder(mockCart).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify {
                    ds.createOrder(any())
                }
            }
        }
    }

    @Test
    fun createOrderError() {
        runTest {
            coEvery {
                ds.createOrder(any())
            } throws IllegalStateException("Mock Error")
            repository.createOrder(mockCart).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                coVerify {
                    ds.createOrder(any())
                }
            }
        }
    }
}
