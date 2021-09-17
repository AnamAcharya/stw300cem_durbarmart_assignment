package com.anamacharya.drubarmart.dob

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anamacharya.drubarmart.entity.Product

@Dao
interface ProductDAO {

    @Insert
    suspend fun registerProduct(product: Product)

    @Query("select * from Product")
    suspend fun getAllProduct():List<Product>

}