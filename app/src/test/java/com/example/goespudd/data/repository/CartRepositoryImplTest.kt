package com.example.goespudd.data.repository

import app.cash.turbine.test
import com.example.goespudd.data.datasource.cart.CartDataSource
import com.example.goespudd.data.model.Cart
import com.example.goespudd.data.model.Menu
import com.example.goespudd.data.model.PriceItem
import com.example.goespudd.data.source.local.database.entity.CartEntity
import com.example.goespudd.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CartRepositoryImplTest {
    @MockK
    lateinit var ds: CartDataSource

    private lateinit var repository: CartRepository

    private val entity1 =
        CartEntity(
            id = 1,
            menuId = "1",
            menuName = "1",
            menuImgUrl = "1",
            menuPrice = 1.0,
            itemQuantity = 1,
            itemNotes = "1",
        )
    private val entity2 =
        CartEntity(
            id = 2,
            menuId = "2",
            menuName = "2",
            menuImgUrl = "2",
            menuPrice = 2.0,
            itemQuantity = 2,
            itemNotes = "2",
        )
    private val mockList = listOf(entity1, entity2)
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
        repository = CartRepositoryImpl(ds)
    }

    @Test
    fun getUserCartDataLoading() {
        val mockFlow =
            flow {
                emit(mockList)
            }
        every {
            ds.getAllCarts()
        } returns mockFlow

        runTest {
            repository.getUserCartData().map {
                delay(100)
                it
            }.test {
                delay(150)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Loading)
                assertEquals(null, actualData.payload?.first?.size)
                assertEquals(null, actualData.payload?.second)
                verify { ds.getAllCarts() }
            }
        }
    }

    @Test
    fun getUserCartDataSuccess() {
        val mockFlow =
            flow {
                emit(mockList)
            }
        every {
            ds.getAllCarts()
        } returns mockFlow

        runTest {
            repository.getUserCartData().map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                assertEquals(mockList.size, actualData.payload?.first?.size)
                assertEquals(5.0, actualData.payload?.second)
                verify { ds.getAllCarts() }
            }
        }
    }

    @Test
    fun getUserCartDataError() {
        every {
            ds.getAllCarts()
        } returns
            flow {
                throw IllegalStateException("Mock Error")
            }

        runTest {
            repository.getUserCartData().map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                verify { ds.getAllCarts() }
            }
        }
    }

    @Test
    fun getUserCartDataEmpty() {
        val mockList = listOf<CartEntity>()
        val mockFlow =
            flow {
                emit(mockList)
            }
        every {
            ds.getAllCarts()
        } returns mockFlow

        runTest {
            repository.getUserCartData().map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Empty)
                assertEquals(0, actualData.payload?.first?.size)
                assertEquals(0.0, actualData.payload?.second)
                verify { ds.getAllCarts() }
            }
        }
    }

    @Test
    fun getCheckoutDataLoading() {
        val mockFlow =
            flow {
                emit(mockList)
            }
        every {
            ds.getAllCarts()
        } returns mockFlow

        runTest {
            repository.getCheckoutData().map {
                delay(100)
                it
            }.test {
                delay(150)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Loading)
                assertEquals(null, actualData.payload?.first?.size)
                assertEquals(null, actualData.payload?.second?.size)
                assertEquals(null, actualData.payload?.third)
                verify { ds.getAllCarts() }
            }
        }
    }

    @Test
    fun getCheckoutDataSuccess() {
        val mockFlow =
            flow {
                emit(mockList)
            }
        every {
            ds.getAllCarts()
        } returns mockFlow

        runTest {
            repository.getCheckoutData().map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                assertEquals(mockList.size, actualData.payload?.first?.size)
                assertEquals(mockMenuPrice.size, actualData.payload?.second?.size)
                assertEquals(5.0, actualData.payload?.third)
                verify { ds.getAllCarts() }
            }
        }
    }

    @Test
    fun getCheckoutDataError() {
        every {
            ds.getAllCarts()
        } returns
            flow {
                throw IllegalStateException("Mock Error")
            }

        runTest {
            repository.getCheckoutData().map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                verify { ds.getAllCarts() }
            }
        }
    }

    @Test
    fun getCheckoutDataEmpty() {
        val mockList = listOf<CartEntity>()
        val mockFlow =
            flow {
                emit(mockList)
            }
        every {
            ds.getAllCarts()
        } returns mockFlow

        runTest {
            repository.getCheckoutData().map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Empty)
                assertEquals(0, actualData.payload?.first?.size)
                assertEquals(0, actualData.payload?.second?.size)
                assertEquals(0.0, actualData.payload?.third)
                verify { ds.getAllCarts() }
            }
        }
    }

    @Test
    fun createCartLoading() {
        val mockMenu = mockk<Menu>(relaxed = true)
        every {
            mockMenu.id
        } returns "1"

        runTest {
            coEvery {
                ds.insertCart(any())
            } returns 1
            repository.createCart(mockMenu, 1, "notes1").map {
                delay(100)
                it
            }.test {
                delay(150)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Loading)
                assertEquals(null, actualData.payload)
                coVerify(atLeast = 1) { ds.insertCart(any()) }
            }
        }
    }

    @Test
    fun createCartSuccess() {
        val mockMenu = mockk<Menu>(relaxed = true)
        every {
            mockMenu.id
        } returns "1"

        runTest {
            coEvery {
                ds.insertCart(any())
            } returns 1
            repository.createCart(mockMenu, 1, "notes1").map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                assertEquals(true, actualData.payload)
                coVerify(atLeast = 1) { ds.insertCart(any()) }
            }
        }
    }

    @Test
    fun createCartError() {
        val mockMenu = mockk<Menu>(relaxed = true)
        every {
            mockMenu.id
        } returns "1"

        runTest {
            coEvery {
                ds.insertCart(any())
            } throws IllegalStateException("Mock Error")
            repository.createCart(mockMenu, 1, "notes1").map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                coVerify(atLeast = 1) { ds.insertCart(any()) }
            }
        }
    }

    @Test
    fun createCartErrorNoId() {
        val mockMenu = mockk<Menu>(relaxed = true)
        every {
            mockMenu.id
        } returns null

        runTest {
            coEvery {
                ds.insertCart(any())
            } returns 1
            repository.createCart(mockMenu, 1, "notes1").map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
            }
        }
    }

    @Test
    fun createCartNotesNullLoading() {
        val mockMenu = mockk<Menu>(relaxed = true)
        every {
            mockMenu.id
        } returns "1"

        runTest {
            coEvery {
                ds.insertCart(any())
            } returns 1
            repository.createCart(mockMenu, 1).map {
                delay(100)
                it
            }.test {
                delay(150)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Loading)
                assertEquals(null, actualData.payload)
                coVerify(atLeast = 1) { ds.insertCart(any()) }
            }
        }
    }

    @Test
    fun createCartNotesNullSuccess() {
        val mockMenu = mockk<Menu>(relaxed = true)
        every {
            mockMenu.id
        } returns "1"

        runTest {
            coEvery {
                ds.insertCart(any())
            } returns 1
            repository.createCart(mockMenu, 1).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                assertEquals(true, actualData.payload)
                coVerify(atLeast = 1) { ds.insertCart(any()) }
            }
        }
    }

    @Test
    fun createCartNotesNullError() {
        val mockMenu = mockk<Menu>(relaxed = true)
        every {
            mockMenu.id
        } returns "1"

        runTest {
            coEvery {
                ds.insertCart(any())
            } throws IllegalStateException("Mock Error")
            repository.createCart(mockMenu, 1).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                coVerify(atLeast = 1) { ds.insertCart(any()) }
            }
        }
    }

    @Test
    fun createCartNotesNullErrorNoId() {
        val mockMenu = mockk<Menu>(relaxed = true)
        every {
            mockMenu.id
        } returns null

        runTest {
            coEvery {
                ds.insertCart(any())
            } returns 1
            repository.createCart(mockMenu, 1).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
            }
        }
    }

    @Test
    fun decreaseCartDeleteCartLoading() {
        coEvery {
            ds.deleteCart(any())
        } returns 1

        runTest {
            repository.decreaseCart(mockCart1).map {
                delay(100)
                it
            }.test {
                delay(150)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Loading)
                coVerify(atLeast = 1) { ds.deleteCart(any()) }
            }
        }
    }

    @Test
    fun decreaseCartDeleteCartSuccess() {
        coEvery {
            ds.deleteCart(any())
        } returns 1

        runTest {
            repository.decreaseCart(mockCart1).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify(atLeast = 1) { ds.deleteCart(any()) }
            }
        }
    }

    @Test
    fun decreaseCartDeleteCartError() {
        coEvery {
            ds.deleteCart(any())
        } throws IllegalStateException("Mock Error")

        runTest {
            repository.decreaseCart(mockCart1).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                coVerify(atLeast = 1) { ds.deleteCart(any()) }
            }
        }
    }

    @Test
    fun decreaseCartUpdateCartLoading() {
        coEvery {
            ds.updateCart(any())
        } returns 1

        runTest {
            repository.decreaseCart(mockCart).map {
                delay(100)
                it
            }.test {
                delay(150)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Loading)
                coVerify(atLeast = 1) { ds.updateCart(any()) }
            }
        }
    }

    @Test
    fun decreaseCartUpdateCartSuccess() {
        coEvery {
            ds.updateCart(any())
        } returns 1

        runTest {
            repository.decreaseCart(mockCart).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify(atLeast = 1) { ds.updateCart(any()) }
            }
        }
    }

    @Test
    fun decreaseCartUpdateCartError() {
        coEvery {
            ds.updateCart(any())
        } throws IllegalStateException("Mock Error")

        runTest {
            repository.decreaseCart(mockCart).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                coVerify(atLeast = 1) { ds.updateCart(any()) }
            }
        }
    }

    @Test
    fun increaseCartLoading() {
        coEvery {
            ds.updateCart(any())
        } returns 1

        runTest {
            repository.increaseCart(mockCart).map {
                delay(100)
                it
            }.test {
                delay(150)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Loading)
                coVerify(atLeast = 1) { ds.updateCart(any()) }
            }
        }
    }

    @Test
    fun increaseCartSuccess() {
        coEvery {
            ds.updateCart(any())
        } returns 1

        runTest {
            repository.increaseCart(mockCart).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify(atLeast = 1) { ds.updateCart(any()) }
            }
        }
    }

    @Test
    fun increaseCartError() {
        coEvery {
            ds.updateCart(any())
        } throws IllegalStateException("Mock Error")

        runTest {
            repository.increaseCart(mockCart).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                coVerify(atLeast = 1) { ds.updateCart(any()) }
            }
        }
    }

    @Test
    fun setCartNotesLoading() {
        coEvery {
            ds.updateCart(any())
        } returns 1

        runTest {
            repository.setCartNotes(mockCart).map {
                delay(100)
                it
            }.test {
                delay(150)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Loading)
                coVerify(atLeast = 1) { ds.updateCart(any()) }
            }
        }
    }

    @Test
    fun setCartNotesSuccess() {
        coEvery {
            ds.updateCart(any())
        } returns 1

        runTest {
            repository.setCartNotes(mockCart).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify(atLeast = 1) { ds.updateCart(any()) }
            }
        }
    }

    @Test
    fun setCartNotes() {
        coEvery {
            ds.updateCart(any())
        } throws IllegalStateException("Mock Error")

        runTest {
            repository.setCartNotes(mockCart).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                coVerify(atLeast = 1) { ds.updateCart(any()) }
            }
        }
    }

    @Test
    fun deleteCartLoading() {
        coEvery {
            ds.deleteCart(any())
        } returns 1

        runTest {
            repository.deleteCart(mockCart).map {
                delay(100)
                it
            }.test {
                delay(150)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Loading)
                coVerify(atLeast = 1) { ds.deleteCart(any()) }
            }
        }
    }

    @Test
    fun deleteCartSuccess() {
        coEvery {
            ds.deleteCart(any())
        } returns 1

        runTest {
            repository.deleteCart(mockCart).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify(atLeast = 1) { ds.deleteCart(any()) }
            }
        }
    }

    @Test
    fun deleteCartError() {
        coEvery {
            ds.deleteCart(any())
        } throws IllegalStateException("Mock Error")

        runTest {
            repository.deleteCart(mockCart).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                coVerify(atLeast = 1) { ds.deleteCart(any()) }
            }
        }
    }

    @Test
    fun deleteAllCart() {
        runTest {
            coEvery {
                repository.deleteAllCart()
            } returns Unit

            val result = ds.deleteAll()
            coVerify {
                repository.deleteAllCart()
                assertEquals(Unit, result)
            }
        }
    }
}
