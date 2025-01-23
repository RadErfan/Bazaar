package ir.dunijet.dunibazaar.di

import ProfileViewModel
import android.content.Context
import androidx.room.Room
import ir.dunijet.dunibazaar.model.db.AppDatabase
import ir.dunijet.dunibazaar.model.net.createApiService
import ir.dunijet.dunibazaar.model.repository.cart.CartRepository
import ir.dunijet.dunibazaar.model.repository.cart.CartRepositoryImpl
import ir.dunijet.dunibazaar.model.repository.comment.CommentRepository
import ir.dunijet.dunibazaar.model.repository.comment.CommentRepositoryImpl
import ir.dunijet.dunibazaar.model.repository.product.ProductRepository
import ir.dunijet.dunibazaar.model.repository.product.ProductRepositoryImpl
import ir.dunijet.dunibazaar.model.repository.user.UserRepository
import ir.dunijet.dunibazaar.model.repository.user.UserRepositoryImpl
import ir.dunijet.dunibazaar.ui.features.cart.CategoryViewModel
import ir.dunijet.dunibazaar.ui.features.category.CartViewModel
import ir.dunijet.dunibazaar.ui.features.main.MainViewModel
import ir.dunijet.dunibazaar.ui.features.product.ProductViewModel
import ir.dunijet.dunibazaar.ui.features.signIn.SignInViewModel
import ir.dunijet.dunibazaar.ui.features.signUp.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {

    single { androidContext().getSharedPreferences("data", Context.MODE_PRIVATE) }
    single { createApiService() }

    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_dataBase.db").build() }

    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<ProductRepository> { ProductRepositoryImpl(get(), get<AppDatabase>().productDao()) }
    single<CommentRepository> { CommentRepositoryImpl(get()) }
    single<CartRepository> { CartRepositoryImpl(get() , get()) }

    viewModel { CategoryViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { ProductViewModel(get(), get(), get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { (isNetConnected: Boolean) -> MainViewModel(get(), get() , isNetConnected) }
    viewModel { CartViewModel(get() , get()) }

}