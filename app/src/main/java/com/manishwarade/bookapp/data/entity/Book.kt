package com.manishwarade.bookapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(

    var name: String = "",

    @PrimaryKey
    var mobileNumber: String = "",

    var bookName: String = ""
)
