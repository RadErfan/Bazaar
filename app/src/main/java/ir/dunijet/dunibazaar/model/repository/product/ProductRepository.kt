package ir.dunijet.dunibazaar.model.repository.product

import ir.dunijet.dunibazaar.model.data.Ads
import ir.dunijet.dunibazaar.model.data.Product

interface ProductRepository {

    suspend fun getAllProducts(isInternetConnected :Boolean): List<Product>
    suspend fun getAllAds(isInternetConnected :Boolean): List<Ads>
    suspend fun getAllProductsByCategory(category :String) :List<Product>
    suspend fun getProductById(productId :String) :Product

}