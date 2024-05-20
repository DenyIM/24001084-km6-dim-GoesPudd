package com.example.goespudd.data.repository

import app.cash.turbine.test
import com.example.goespudd.data.datasource.category.CategoryDataSource
import com.example.goespudd.data.source.network.model.category.CategoryItemResponse
import com.example.goespudd.data.source.network.model.category.CategoryResponse
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

class CategoryRepositoryImplTest {
    @MockK
    lateinit var ds: CategoryDataSource

    private lateinit var repository: CategoryRepository

    private val cart1 =
        CategoryItemResponse(
            name = "1",
            imgUrl = "1",
        )
    private val cart2 =
        CategoryItemResponse(
            name = "2",
            imgUrl = "2",
        )
    private val mockListCategory = listOf(cart1, cart2)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = CategoryRepositoryImpl(ds)
    }

    @Test
    fun getCategoriesLoading() {
        val mockResponse = mockk<CategoryResponse>()
        every {
            mockResponse.data
        } returns mockListCategory

        runTest {
            coEvery {
                ds.getCategory()
            } returns mockResponse
            repository.getCategory().map {
                delay(100)
                it
            }.test {
                delay(150)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Loading)
                coVerify {
                    ds.getCategory()
                }
            }
        }
    }

    @Test
    fun getCategoriesSuccess() {
        val mockResponse = mockk<CategoryResponse>()
        every {
            mockResponse.data
        } returns mockListCategory

        runTest {
            coEvery {
                ds.getCategory()
            } returns mockResponse
            repository.getCategory().map {
                delay(100)
                it
            }.test {
                delay(250)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify {
                    ds.getCategory()
                }
            }
        }
    }

    @Test
    fun getCategoriesError() {
        runTest {
            coEvery {
                ds.getCategory()
            } throws IllegalStateException("Mock Error")
            repository.getCategory().map {
                delay(100)
                it
            }.test {
                delay(250)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Error)
                coVerify {
                    ds.getCategory()
                }
            }
        }
    }

    @Test
    fun getCategoriesEmpty() {
        val mockListCategory = listOf<CategoryItemResponse>()
        val mockResponse = mockk<CategoryResponse>()
        every {
            mockResponse.data
        } returns mockListCategory

        runTest {
            coEvery {
                ds.getCategory()
            } returns mockResponse
            repository.getCategory().map {
                delay(100)
                it
            }.test {
                delay(250)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Empty)
                coVerify {
                    ds.getCategory()
                }
            }
        }
    }
}
