package com.manishwarade.bookapp.ui.viewmodel

import androidx.lifecycle.*
import com.manishwarade.bookapp.data.repository.MainRepository
import com.manishwarade.bookapp.data.entity.Book
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val mainRepo: MainRepository by lazy { MainRepository() }

    private var mobileNumber: String = ""

    var bookLiveData: MutableLiveData<Book> = MutableLiveData()

    fun setMobileNumber(number: String) {
        mobileNumber = number
    }

    fun isMobileNumberNotEmpty() = mobileNumber.isNotEmpty()

    fun add(name: String, mobileNumber: String, book: String) {

        viewModelScope.launch(IO) {
            mainRepo.add(buildBookObj(name, mobileNumber, book))
        }
    }

    fun findByNumber() {
        viewModelScope.launch(IO) {

            val item = mainRepo.findByNumber(mobileNumber)

            withContext(Main) {

                item?.let {
                    bookLiveData.value = item
                }
            }

        }
    }

    private fun buildBookObj(name: String, mobileNumber: String, book: String) = Book().apply {

            this.name = name

            this.mobileNumber = mobileNumber

            this.bookName = book
        }
}