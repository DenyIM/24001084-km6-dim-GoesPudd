package com.example.goespudd.presentation.detailmenu

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.goespudd.data.model.Menu
import com.example.goespudd.data.repository.CartRepository
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

class DetailMenuViewModelTest {
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    lateinit var repository: CartRepository

    @MockK
    lateinit var extras: Bundle

    private lateinit var viewModel: DetailMenuViewModel
    private val mockMenu = mockk<Menu>(relaxed = true)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { extras.getParcelable<Menu>(DetailMenuActivity.EXTRA_MENU) } returns mockMenu
        viewModel = spyk(DetailMenuViewModel(extras, repository))
    }

    @Test
    fun addToCart() {
        every {
            repository.createCart(mockMenu, 1)
        } returns
            flow {
                emit(
                    ResultWrapper.Success(true),
                )
            }
        val result = viewModel.addToCart().getOrAwaitValue()
        assertEquals(true, result.payload)
        verify { repository.createCart(mockMenu, 1) }
    }
}
