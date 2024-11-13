package com.example.saveinstancestate

import android.content.Context
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog

class MyDialjg {

    companion object{

        fun myDialog (context: Context,adapter: ArrayAdapter<Users>) =
            AdapterView.OnItemClickListener{ s, v, position, id->

            val builder = AlertDialog.Builder(context)

            builder.setTitle("Удаление").setMessage("Удалить пользователя?").setCancelable(true)
                .setNegativeButton("Нет") { dialog,which -> dialog.cancel()}
                .setPositiveButton("Да")  { dialog,which ->
                    val note = adapter.getItem(position)
                    adapter.remove(note) }.create()
            builder.show() }




        }
    }
