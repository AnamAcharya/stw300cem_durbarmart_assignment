package com.anamacharya.drubarmart

import com.anamacharya.drubarmart.api.ServiceBuilder
import com.anamacharya.drubarmart.repository.CustomerRepository
import kotlinx.coroutines.runBlocking
import com.anamacharya.drubarmart.entity.*
import com.anamacharya.drubarmart.repository.CartRepository
import com.anamacharya.drubarmart.repository.ContactRepository
import com.anamacharya.drubarmart.repository.OrderRepository
import org.junit.Assert
import org.junit.Test

class UnitTestingFoodmart {
    private lateinit var customerRepository: CustomerRepository
    private lateinit var contactRepository: ContactRepository
    private lateinit var cartRepository: CartRepository
    private lateinit var orderRepository: OrderRepository

    @Test
    fun checkLogin()= runBlocking {
        customerRepository= CustomerRepository()
        val response=customerRepository.checkUser("anam07","anam07")
        val expectedResult= true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }



    @Test
    fun checkContact()= runBlocking {
        val contact=Contact( fullname = "Anam Acharya",
             email = "acharyaanam@gmail.com", phone="97410367890",message="anam here")
        contactRepository= ContactRepository()
        val response=contactRepository.contactUser(contact)
        val expectedResult= true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun checkProfile() = runBlocking {
        customerRepository= CustomerRepository()
        ServiceBuilder.token="Bearer " +customerRepository.checkUser("anam07","anam07").token
        val response=customerRepository.getCitizen()
        val expectedResult= true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    @Test
    fun checkCart() = runBlocking {
        cartRepository= CartRepository()
        customerRepository= CustomerRepository()
        ServiceBuilder.token="Bearer " +customerRepository.checkUser("anam07","anam07").token
        val response=cartRepository.getCart()
        val expectedResult= true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    @Test
    fun checkOrder() = runBlocking {
        customerRepository= CustomerRepository()
        orderRepository = OrderRepository()
        ServiceBuilder.token="Bearer " +customerRepository.checkUser("anam07","anam07").token
        val response=orderRepository.getOrder()
        val expectedResult= true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }
}


