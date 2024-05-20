package com.example.goespudd.presentation.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.goespudd.data.repository.UserRepository
import com.example.goespudd.tools.MainCoroutineRule
import com.example.goespudd.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class LoginViewModelTest {
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    lateinit var repository: UserRepository

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = LoginViewModel(repository)
    }

    @Test
    fun doLogin() {
        val email = "email@email.com"
        val password = "password"

        every {
            repository.doLogin(any(), any())
        } returns
            flow {
                emit(ResultWrapper.Success(true))
            }
        viewModel.doLogin(email, password)
        verify {
            repository.doLogin(any(), any())
        }
    }
}
