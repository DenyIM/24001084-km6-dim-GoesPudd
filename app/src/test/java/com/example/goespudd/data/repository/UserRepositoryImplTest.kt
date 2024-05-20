package com.example.goespudd.data.repository

import app.cash.turbine.test
import com.example.goespudd.data.datasource.authentication.AuthDataSource
import com.example.goespudd.data.datasource.user.UserDataSource
import com.example.goespudd.data.model.User
import com.example.goespudd.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UserRepositoryImplTest {
    @MockK
    lateinit var uds: UserDataSource

    @MockK
    lateinit var ads: AuthDataSource

    private lateinit var repository: UserRepository

    private val email = "email@email.com"
    private val password = "password"
    private val fullName = "Full Name"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = UserRepositoryImpl(uds, ads)
    }

    @Test
    fun isUsingGrid() {
        every {
            repository.isUsingGridMode()
        } returns true

        val actualResult = uds.isUsingGridMode()
        verify {
            repository.isUsingGridMode()
        }
        assertEquals(true, actualResult)
    }

    @Test
    fun setUsingGridMode() {
        every {
            repository.setUsingGridMode(any())
        } returns Unit

        uds.setUsingGridMode(true)
        verify {
            repository.setUsingGridMode(any())
        }
    }

    @Test
    fun doLoginLoading() {
        coEvery {
            ads.doLogin(email, password)
        } returns true

        runTest {
            repository.doLogin(email, password).map {
                delay(100)
                it
            }.test {
                delay(150)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Loading)
                coVerify { ads.doLogin(email, password) }
            }
        }
    }

    @Test
    fun doLoginSuccess() {
        coEvery {
            ads.doLogin(email, password)
        } returns true

        runTest {
            repository.doLogin(email, password).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify { ads.doLogin(email, password) }
            }
        }
    }

    @Test
    fun doLoginError() {
        coEvery {
            ads.doLogin(email, password)
        } throws IllegalStateException("Mock Error")

        runTest {
            repository.doLogin(email, password).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                coVerify { ads.doLogin(email, password) }
            }
        }
    }

    @Test
    fun doRegisterLoading() {
        coEvery {
            ads.doRegister(email, fullName, password)
        } returns true

        runTest {
            repository.doRegister(email, fullName, password).map {
                delay(100)
                it
            }.test {
                delay(150)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Loading)
                coVerify { ads.doRegister(email, fullName, password) }
            }
        }
    }

    @Test
    fun doRegisterSuccess() {
        coEvery {
            ads.doRegister(email, fullName, password)
        } returns true

        runTest {
            repository.doRegister(email, fullName, password).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify { ads.doRegister(email, fullName, password) }
            }
        }
    }

    @Test
    fun doRegisterError() {
        coEvery {
            ads.doRegister(email, fullName, password)
        } throws IllegalStateException("Mock Error")

        runTest {
            repository.doRegister(email, fullName, password).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                coVerify { ads.doRegister(email, fullName, password) }
            }
        }
    }

    @Test
    fun updatePasswordLoading() {
        coEvery {
            ads.updatePassword(password)
        } returns true

        runTest {
            repository.updatePassword(password).map {
                delay(100)
                it
            }.test {
                delay(150)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Loading)
                coVerify { ads.updatePassword(password) }
            }
        }
    }

    @Test
    fun updatePasswordSuccess() {
        coEvery {
            ads.updatePassword(password)
        } returns true

        runTest {
            repository.updatePassword(password).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify { ads.updatePassword(password) }
            }
        }
    }

    @Test
    fun updatePasswordError() {
        coEvery {
            ads.updatePassword(password)
        } throws IllegalStateException("Mock Error")

        runTest {
            repository.updatePassword(password).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                coVerify { ads.updatePassword(password) }
            }
        }
    }

    @Test
    fun updateEmailLoading() {
        coEvery {
            ads.updateEmail(email)
        } returns true

        runTest {
            repository.updateEmail(email).map {
                delay(100)
                it
            }.test {
                delay(150)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Loading)
                coVerify { ads.updateEmail(email) }
            }
        }
    }

    @Test
    fun updateEmailSuccess() {
        coEvery {
            ads.updateEmail(email)
        } returns true

        runTest {
            repository.updateEmail(email).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify { ads.updateEmail(email) }
            }
        }
    }

    @Test
    fun updateEmailError() {
        coEvery {
            ads.updateEmail(email)
        } throws IllegalStateException("Mock Error")

        runTest {
            repository.updateEmail(email).map {
                delay(100)
                it
            }.test {
                delay(250)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                coVerify { ads.updateEmail(email) }
            }
        }
    }

    @Test
    fun requestChangePasswordByEmail() {
        every {
            repository.requestChangePasswordByEmail()
        } returns true

        val actualResult = ads.requestChangePasswordByEmail()
        verify {
            repository.requestChangePasswordByEmail()
        }
        assertEquals(true, actualResult)
    }

    @Test
    fun doLogout() {
        every {
            repository.doLogout()
        } returns true

        val actualResult = ads.doLogout()
        verify {
            repository.doLogout()
        }
        assertEquals(true, actualResult)
    }

    @Test
    fun isLoggedIn() {
        every {
            repository.isLoggedIn()
        } returns true

        val actualResult = ads.isLoggedIn()
        verify {
            repository.isLoggedIn()
        }
        assertEquals(true, actualResult)
    }

    @Test
    fun getCurrentUser() {
        val mockUser = mockk<User>(relaxed = true)
        every {
            repository.getCurrentUser()
        } returns mockUser

        val actualResult = ads.getCurrentUser()
        verify {
            repository.getCurrentUser()
        }
        assertEquals(mockUser, actualResult)
    }
}
