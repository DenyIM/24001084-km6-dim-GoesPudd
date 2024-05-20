package com.example.goespudd.data.datasource.user

import com.example.goespudd.data.source.local.pref.UserPreference
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserDataSourceImplTest {
    @MockK
    lateinit var pref: UserPreference

    private lateinit var dataSource: UserDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = UserDataSourceImpl(pref)
    }

    @Test
    fun isUsingGridMode() {
        every {
            pref.isUsingGridMode()
        } returns true

        val actualResult = dataSource.isUsingGridMode()
        verify {
            pref.isUsingGridMode()
        }
        assertEquals(true, actualResult)
    }

    @Test
    fun setUsingGridMode() {
        every {
            pref.setUsingGridMode(any())
        } returns Unit

        dataSource.setUsingGridMode(true)
        verify {
            pref.setUsingGridMode(any())
        }
    }
}
