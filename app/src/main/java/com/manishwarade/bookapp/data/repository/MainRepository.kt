package com.manishwarade.bookapp.data.repository

import com.manishwarade.bookapp.data.AppDatabase
import com.manishwarade.bookapp.data.dao.BookDao
import com.manishwarade.bookapp.data.entity.Book

class MainRepository {

    private val bookDao: BookDao by lazy {
        AppDatabase.getInstance().bookDao()
    }

    suspend fun add(bookObj: Book) {
        bookDao.add(bookObj)
    }

    suspend fun findByNumber(number: String) = bookDao.findByNumber(number)
}