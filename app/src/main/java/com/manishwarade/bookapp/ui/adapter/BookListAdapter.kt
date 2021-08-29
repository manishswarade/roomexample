package com.manishwarade.bookapp.ui.adapter

import android.content.Context
import android.view.*
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.manishwarade.bookapp.R
import com.manishwarade.bookapp.data.entity.Book
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_book.view.*


open class BookListAdapter(private val context: Context) : RecyclerView.Adapter<BookListAdapter.BookListViewHolder>() {


    var settingItems = emptyList<Book>()

    var onEdit = { _: String -> }


    fun updateData(items: List<Book>) {
        this.settingItems = items
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_book, parent, false)

        return BookListViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: BookListViewHolder, i: Int) {
        viewHolder.bind(settingItems[i])
    }

    override fun getItemCount(): Int {
        return settingItems.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    inner class BookListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



        fun bind(item: Book?) {

            item?.let {

                itemView.txtName.text = findText(item.name)

                itemView.txtMobileNumber.text = findText(item.mobileNumber)

                itemView.txtBookName.text = findText(item.bookName)

                itemView.img_menu.setOnTouchListener { view, motionEvent ->

                    if (motionEvent.action == MotionEvent.ACTION_UP) {

                        val popup = PopupMenu(context, view)


                        popup.setOnMenuItemClickListener { it->

                            when(it.itemId) {

                                R.id.action_edit -> {
                                    onEdit(item.mobileNumber)
                                }
                            }

                            return@setOnMenuItemClickListener true
                        }

                        popup.inflate(R.menu.menu_book_list)
                        popup.show()
                    }

                    return@setOnTouchListener true
                }
            }

        }

        private fun findText(value: String?) = value ?: "N/A"
    }
}

