package com.example.goespudd.data.datasource.menu

import com.example.goespudd.data.source.network.model.checkout.CheckoutRequestPayload
import com.example.goespudd.data.source.network.model.checkout.CheckoutResponse
import com.example.goespudd.data.source.network.model.menu.MenuResponse
import com.example.goespudd.data.source.network.services.GoespuddApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MenuApiDataSourceTest {
    @MockK
    lateinit var service: GoespuddApiService

    private lateinit var dataSource: MenuDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = MenuApiDataSource(service)
    }

    @Test
    fun getMenuList() {
        runTest {
            val mockResponse = mockk<MenuResponse>(relaxed = true)
            coEvery {
                service.getMenu(any())
            } returns mockResponse

            val actualResult = dataSource.getMenu("makanan")
            coVerify {
                service.getMenu(any())
            }
            assertEquals(actualResult, mockResponse)
        }
    }

    @Test
    fun createOrder() {
        runTest {
            val mockRequest = mockk<CheckoutRequestPayload>()
            val mockResponse = mockk<CheckoutResponse>(relaxed = true)
            coEvery {
                service.createOrder(any())
            } returns mockResponse

            val actualResult = dataSource.createOrder(mockRequest)
            coVerify {
                service.createOrder(any())
            }
            assertEquals(actualResult, mockResponse)
        }
    }
}
