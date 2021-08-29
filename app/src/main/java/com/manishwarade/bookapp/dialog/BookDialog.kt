package com.manishwarade.bookapp.dialog

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

object BookDialog {

    fun show(context: Context, title: String, message: String) {

        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ok", null)
            .show()
    }

    fun showSelectionDialog(context: Context, title: String, items: Array<String>, selectedItem: String, onSelect: (Int) -> Unit) {

        var pos = items.indexOf(selectedItem)

        if(pos < 0) pos = 0

        val dialog = AlertDialog.Builder(context)

        dialog.setSingleChoiceItems(items, pos, object : DialogInterface.OnClickListener {

            override fun onClick(d: DialogInterface, n: Int) {

                pos = n
            }
        })

        dialog.setNegativeButton("Cancel", null)

        dialog.setPositiveButton("Ok", object : DialogInterface.OnClickListener {

            override fun onClick(d: DialogInterface, n: Int) {

                onSelect(pos)

                d.dismiss()
            }
        })

        dialog.setTitle(title)

        dialog.show()
    }

}