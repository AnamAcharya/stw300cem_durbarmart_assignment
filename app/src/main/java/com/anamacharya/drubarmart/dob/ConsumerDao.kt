//package com.reshika.foodmart.dob
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import com.reshika.foodmart.entity.Consumer
//
//@Dao
//interface ConsumerDao {
//    @Insert
//    suspend fun registerConsumer(consumer: Consumer)
//
//    @Query("select * from Consumer where username=(:username) and password=(:password)")
//    suspend fun checkUser(username: String, password: String): Consumer
//}