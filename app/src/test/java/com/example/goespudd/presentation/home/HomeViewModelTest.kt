package com.example.goespudd.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.goespudd.data.repository.CategoryRepository
import com.example.goespudd.data.repository.MenuRepository
import com.example.goespudd.data.repository.UserRepository
import com.example.goespudd.tools.MainCoroutineRule
import com.example.goespudd.tools.getOrAwaitValue
import com.example.goespudd.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
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

class HomeViewModelTest {
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    lateinit var userRepository: UserRepository

    @MockK
    lateinit var categoryRepository: CategoryRepository

    @MockK
    lateinit var menuRepository: MenuRepository

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel =
            spyk(
                HomeViewModel(
                    categoryRepository,
                    menuRepository,
                    userRepository,
                ),
            )
    }

    @Test
    fun getProduct() {
        every { menuRepository.getMenu() } returns
            flow {
                emit(
                    ResultWrapper.Success(
                        listOf(
                            mockk(), mockk(),
                        ),
                    ),
                )
            }
        val result = viewModel.getMenu().getOrAwaitValue()
        assertEquals(2, result.payload?.size)
        verify { menuRepository.getMenu() }
    }

    @Test
    fun getProductSlug() {
        every { menuRepository.getMenu(any()) } returns
            flow {
                emit(
                    ResultWrapper.Success(
                        listOf(
                            mockk(), mockk(),
                        ),
                    ),
                )
            }
        val result = viewModel.getMenu().getOrAwaitValue()
        assertEquals(2, result.payload?.size)
        verify { menuRepository.getMenu(any()) }
    }

    @Test
    fun getCategory() {
        every { categoryRepository.getCategory() } returns
            flow {
                emit(
                    ResultWrapper.Success(
                        listOf(
                            mockk(), mockk(),
                        ),
                    ),
                )
            }
        val result = viewModel.getCategory().getOrAwaitValue()
        assertEquals(2, result.payload?.size)
        verify { categoryRepository.getCategory() }
    }

    @Test
    fun isUsingGrid() {
        every {
            userRepository.isUsingGridMode()
        } returns true
        val result = viewModel.isUsingGridMode()
        assertEquals(true, result)
        verify { userRepository.isUsingGridMode() }
    }

    @Test
    fun setUsingGridMode() {
        every {
            userRepository.setUsingGridMode(any())
        } returns Unit
        viewModel.setUsingGridMode(false)
        verify {
            userRepository.setUsingGridMode(any())
        }
    }
}
