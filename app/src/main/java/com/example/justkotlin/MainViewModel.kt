package com.example.justkotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.NumberFormat

class MainViewModel : ViewModel() {

    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> get() = _quantity

    private val _whippedCreamTopping = MutableLiveData<Boolean>()
    val whippedCreamTopping: LiveData<Boolean> get() = _whippedCreamTopping

    private val _chocolateTopping = MutableLiveData<Boolean>()
    val chocolateTopping: LiveData<Boolean> get() = _chocolateTopping

    private val _orderSummaryMessage = MutableLiveData<String>()
    val orderSummaryMessage: LiveData<String> get() = _orderSummaryMessage

    init {
        clearOrder()
    }

    fun increaseQuantity() {
        _quantity.value = _quantity.value?.plus(1)
    }

    fun decreaseQuantity() {
        if (_quantity.value!! > 0) {
            _quantity.value = _quantity.value?.minus(1)
        }
    }

    fun createOrder() {
        if(_quantity.value == 0){
            _orderSummaryMessage.value = " please, choose quantity!"
        } else {
            val price = calculatePrice()
            if (price != null) {
                createOrderMessage(price)
            }
        }
    }

    fun clearOrder() {
        _whippedCreamTopping.value = false
        _chocolateTopping.value = false
        _quantity.value = 0
        _orderSummaryMessage.value = ""
    }

    fun addWhippedCreamTopping() {
        _whippedCreamTopping.value = _whippedCreamTopping.value == false
    }

    fun addChocolateTopping() {
        _chocolateTopping.value = _chocolateTopping.value == false
    }

    private fun calculatePrice(): Double {
        var orderPrice = 3.7
        if (_whippedCreamTopping.value == true) {
            orderPrice += 1
        }
        if (_chocolateTopping.value == true) {
            orderPrice += 2
        }
        orderPrice *= _quantity.value!!
        return orderPrice
    }

    private fun createOrderMessage(totalSum: Double) {
        val orderPrice = NumberFormat.getCurrencyInstance().format(totalSum)
        _orderSummaryMessage.value =
            "\nAdd whipped cream? ${_whippedCreamTopping.value} \n" +
                    "Add chocolate? ${_chocolateTopping.value} \n" +
                    "Quantity: ${_quantity.value} \n" +
                    "Total: $orderPrice \n" +
                    "Thank you!"
    }
}