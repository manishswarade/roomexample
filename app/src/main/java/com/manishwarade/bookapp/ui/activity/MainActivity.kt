package com.manishwarade.bookapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.manishwarade.bookapp.R
import com.manishwarade.bookapp.dialog.BookDialog
import com.manishwarade.bookapp.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.setTitle("Add Book")

        initUi()

        handleEvent()
    }

    private fun initUi() {

        val number = intent.extras?.let {
            it.getString("MOBILENUMBER")
        } ?: ""

        mainViewModel.setMobileNumber(number)

        if(mainViewModel.isMobileNumberNotEmpty()) {
            edtMobileNumber.isEnabled = false
            btnSubmit.setText("Update")
            mainViewModel.findByNumber()
        }

        mainViewModel.bookLiveData.observe(this, Observer {

            edtName.setText(it.name)

            edtMobileNumber.setText(it.mobileNumber)

            cmbBook.setText(it.bookName)
        })
    }
    private fun handleEvent() {

        btnSubmit.setOnClickListener {

            val message = validate()

            if(message != "") {
                BookDialog.show(this, "Error", message)
                return@setOnClickListener
            }
            else {

                mainViewModel.add(edtName.text.toString(), edtMobileNumber.text.toString(), cmbBook.text.toString())

                Toast.makeText(this, "Book added sucessfully!!", Toast.LENGTH_LONG).show()
            }
        }

        cmbBook.setOnTouchListener { view, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_UP) {

                val books = resources.getStringArray(R.array.books)

                BookDialog.showSelectionDialog(this, findMessage(), books, cmbBook.text.toString()) {
                    cmbBook.setText(books[it])
                }
            }

            return@setOnTouchListener true
        }
    }

    private fun findMessage() =

        if(mainViewModel.isMobileNumberNotEmpty())
            "Book updated sucessfully!!"
        else
            "Book added sucessfully!!"

    private fun validate(): String {

        return when ("") {

            edtName.text.toString().trim() ->
                "Please enter name"

            edtMobileNumber.text.toString().trim() ->
                "Please enter mobile number"

            cmbBook.text.toString().trim() ->
                "Please select book"

            else ->
                ""
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_see_all, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_see_list -> {
                showBookListActivity()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showBookListActivity() {

        val intent = Intent(this, BookListActivity::class.java)

        startActivity(intent)
    }
}