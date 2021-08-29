package com.manishwarade.bookapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manishwarade.bookapp.data.entity.Book

@Dao
abstract class BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun add(item: Book)

    @Query("Select * From Book")
    abstract fun findAll() : List<Book>

    @Query("Select * From Book Where mobileNumber = :number")
    abstract fun findByNumber(number: String) : Book?
}