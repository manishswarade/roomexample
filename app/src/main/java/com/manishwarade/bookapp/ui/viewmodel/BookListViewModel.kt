package com.manishwarade.bookapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manishwarade.bookapp.data.repository.BookListRepository
import com.manishwarade.bookapp.ui.adapter.BookListAdapter
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookListViewModel : ViewModel() {

    private val bookListRepo: BookListRepository by lazy { BookListRepository() }

    lateinit var adapter: BookListAdapter

    fun findAll() {

        viewModelScope.launch(IO) {

            val list = bookListRepo.findAll()

            withContext(Main) {
                adapter.updateData(list)
            }
        }
    }
}