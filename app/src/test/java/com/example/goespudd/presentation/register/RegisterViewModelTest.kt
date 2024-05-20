package com.example.goespudd.presentation.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.goespudd.data.repository.UserRepository
import com.example.goespudd.tools.MainCoroutineRule
import com.example.goespudd.tools.getOrAwaitValue
import com.example.goespudd.utils.ResultWrapper
import io.mockk.MockKAnnotations
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

class RegisterViewModelTest {
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    lateinit var repository: UserRepository

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(RegisterViewModel(repository))
    }

    @Test
    fun doRegister() {
        val email = "email@email.com"
        val password = "password"
        val fullName = "Full Name"

        every {
            repository.doRegister(email, fullName, password)
        } returns
            flow {
                emit(
                    ResultWrapper.Success(true),
                )
            }
        val result = viewModel.doRegister(email, fullName, password).getOrAwaitValue()
        assertEquals(true, result.payload)
        verify { repository.doRegister(email, fullName, password) }
    }
}
