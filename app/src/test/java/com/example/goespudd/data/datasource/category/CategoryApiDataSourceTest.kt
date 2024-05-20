package com.example.goespudd.data.datasource.category

import com.example.goespudd.data.source.network.model.category.CategoryResponse
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

class CategoryApiDataSourceTest {
    @MockK
    lateinit var service: GoespuddApiService

    private lateinit var dataSource: CategoryDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = CategoryApiDataSource(service)
    }

    @Test
    fun getCategories() {
        runTest {
            val mockResponse = mockk<CategoryResponse>(relaxed = true)
            coEvery {
                service.getCategory()
            } returns mockResponse

            val actualResult = dataSource.getCategory()
            coVerify {
                service.getCategory()
            }
            assertEquals(actualResult, mockResponse)
        }
    }
}
