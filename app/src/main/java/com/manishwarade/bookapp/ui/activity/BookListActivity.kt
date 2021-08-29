package com.manishwarade.bookapp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.manishwarade.bookapp.R
import com.manishwarade.bookapp.ui.adapter.BookListAdapter
import com.manishwarade.bookapp.ui.viewmodel.BookListViewModel
import kotlinx.android.synthetic.main.activity_book_list.*

class BookListActivity : AppCompatActivity() {

    private val bookListViewModel by viewModels<BookListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        initUi()
    }

    private fun initUi() {

        bookListViewModel.adapter = BookListAdapter(this)

        recycler.apply {
            this.setHasFixedSize(true)
            this.adapter = bookListViewModel.adapter
        }

        bookListViewModel.findAll()

        bookListViewModel.adapter.onEdit = fun (mobileNumber: String) {

            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("MOBILENUMBER", mobileNumber)

            startActivity(intent)
        }
    }
}