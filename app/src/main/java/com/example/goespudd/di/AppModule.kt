package com.example.goespudd.di

import android.content.SharedPreferences
import com.example.goespudd.data.datasource.authentication.AuthDataSource
import com.example.goespudd.data.datasource.authentication.FirebaseAuthDataSource
import com.example.goespudd.data.datasource.cart.CartDataSource
import com.example.goespudd.data.datasource.cart.CartDatabaseDataSource
import com.example.goespudd.data.datasource.category.CategoryApiDataSource
import com.example.goespudd.data.datasource.category.CategoryDataSource
import com.example.goespudd.data.datasource.menu.MenuApiDataSource
import com.example.goespudd.data.datasource.menu.MenuDataSource
import com.example.goespudd.data.datasource.user.UserDataSource
import com.example.goespudd.data.datasource.user.UserDataSourceImpl
import com.example.goespudd.data.repository.CartRepository
import com.example.goespudd.data.repository.CartRepositoryImpl
import com.example.goespudd.data.repository.CategoryRepository
import com.example.goespudd.data.repository.CategoryRepositoryImpl
import com.example.goespudd.data.repository.MenuRepository
import com.example.goespudd.data.repository.MenuRepositoryImpl
import com.example.goespudd.data.repository.UserRepository
import com.example.goespudd.data.repository.UserRepositoryImpl
import com.example.goespudd.data.source.firebase.FirebaseService
import com.example.goespudd.data.source.local.database.AppDatabase
import com.example.goespudd.data.source.local.database.dao.CartDao
import com.example.goespudd.data.source.local.pref.UserPreference
import com.example.goespudd.data.source.local.pref.UserPreferenceImpl
import com.example.goespudd.data.source.network.services.GoespuddApiService
import com.example.goespudd.presentation.cart.CartViewModel
import com.example.goespudd.presentation.checkout.CheckoutViewModel
import com.example.goespudd.presentation.detailmenu.DetailMenuViewModel
import com.example.goespudd.presentation.home.HomeViewModel
import com.example.goespudd.presentation.login.LoginViewModel
import com.example.goespudd.presentation.profile.ProfileViewModel
import com.example.goespudd.presentation.register.RegisterViewModel
import com.example.goespudd.presentation.splashscreen.SplashViewModel
import com.example.goespudd.utils.SharedPreferenceUtils
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object AppModule {
    private val networkModule =
        module {
            single<GoespuddApiService> {
                GoespuddApiService.invoke()
            }
        }

    private val localModule =
        module {
            single<AppDatabase> {
                AppDatabase.createInstance(androidContext())
            }
            single<CartDao> { get<AppDatabase>().cartDao() }
            single<SharedPreferences> {
                SharedPreferenceUtils.createPreference(androidContext(), UserPreferenceImpl.PREF_NAME)
            }
            single<UserPreference> {
                UserPreferenceImpl(get())
            }
        }

    private val firebaseModule =
        module {
            single<FirebaseService> { FirebaseService.getInstance() }
        }

    private val dataSourceModule =
        module {
            single<AuthDataSource> {
                FirebaseAuthDataSource(get())
            }
            single<CartDataSource> {
                CartDatabaseDataSource(get())
            }
            single<MenuDataSource> {
                MenuApiDataSource(get())
            }
            single<CategoryDataSource> {
                CategoryApiDataSource(get())
            }
            single<UserDataSource> {
                UserDataSourceImpl(get())
            }
        }

    private val repositoryModule =
        module {
            single<CartRepository> {
                CartRepositoryImpl(get())
            }
            single<MenuRepository> {
                MenuRepositoryImpl(get())
            }
            single<CategoryRepository> {
                CategoryRepositoryImpl(get())
            }
            single<UserRepository> {
                UserRepositoryImpl(get(), get())
            }
        }

    private val viewModelModule =
        module {

            viewModelOf(::LoginViewModel)
            viewModelOf(::RegisterViewModel)
            viewModelOf(::CartViewModel)
            viewModelOf(::ProfileViewModel)
            viewModelOf(::SplashViewModel)
            viewModelOf(::CheckoutViewModel)
            viewModelOf(::HomeViewModel)
            viewModel { params ->
                DetailMenuViewModel(
                    extras = params.get(),
                    cartRepository = get(),
                )
            }
        }

    val modules =
        listOf(
            networkModule,
            localModule,
            firebaseModule,
            dataSourceModule,
            repositoryModule,
            viewModelModule,
        )
}
