package com.manishwarade.bookapp.data.repository

import com.manishwarade.bookapp.data.AppDatabase
import com.manishwarade.bookapp.data.dao.BookDao
import com.manishwarade.bookapp.data.entity.Book

class BookListRepository {

    private val bookDao: BookDao by lazy {
        AppDatabase.getInstance().bookDao()
    }

    suspend fun findAll(): List<Book> {
        return bookDao.findAll()
    }
}