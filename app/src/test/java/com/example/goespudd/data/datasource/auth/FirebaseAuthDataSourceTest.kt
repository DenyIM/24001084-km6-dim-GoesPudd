package com.example.goespudd.data.datasource.auth

import com.example.goespudd.data.datasource.authentication.AuthDataSource
import com.example.goespudd.data.datasource.authentication.FirebaseAuthDataSource
import com.example.goespudd.data.model.toUser
import com.example.goespudd.data.source.firebase.FirebaseService
import com.google.firebase.auth.FirebaseUser
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FirebaseAuthDataSourceTest {
    @MockK
    lateinit var service: FirebaseService

    private lateinit var dataSource: AuthDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = FirebaseAuthDataSource(service)
    }

    @Test
    fun doLogin() {
        runTest {
            coEvery {
                service.doLogin(any(), any())
            } returns true

            val actualResult = dataSource.doLogin("denyiqbalmubarok773@gmail.com", "12345678")
            coVerify {
                service.doLogin(any(), any())
            }
            assertEquals(true, actualResult)
        }
    }

    @Test
    fun doRegister() {
        runTest {
            coEvery {
                service.doRegister(any(), any(), any())
            } returns true

            val actualResult = dataSource.doRegister("denyiqbalmubarok773@gmail.com", "Deny Iqbal Mubarok", "12345678")
            coVerify {
                service.doRegister(any(), any(), any())
            }
            assertEquals(true, actualResult)
        }
    }

    @Test
    fun isLoggedIn() {
        every {
            service.isLoggedIn()
        } returns true

        val actualResult = dataSource.isLoggedIn()
        verify {
            service.isLoggedIn()
        }
        assertEquals(true, actualResult)
    }

    @Test
    fun getCurrentUser() {
        val mockFirebaseUser = mockk<FirebaseUser>(relaxed = true)
        every {
            service.getCurrentUser()
        } returns mockFirebaseUser

        val result = mockFirebaseUser.toUser()
        val actualResult = dataSource.getCurrentUser()
        verify {
            service.getCurrentUser()
        }
        assertEquals(result, actualResult)
    }
}
