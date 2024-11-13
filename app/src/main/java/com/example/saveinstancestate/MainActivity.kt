package com.example.saveinstancestate

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var name: EditText
    private lateinit var age: EditText
    private lateinit var button: Button
    private lateinit var listTv: ListView

    private lateinit var textTv: TextView

    private lateinit var uvm: UserViewModel

    private var list: MutableList<Users> = mutableListOf()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

                 val builder = AlertDialog.Builder(this)
                 builder.setTitle("Выход из программы").setMessage("Действительно выйти?").setCancelable(true)
                        .setNegativeButton("Нет"){dialog,which -> dialog.cancel()}
                        .setPositiveButton("Да") {dialog,whitch -> finish() }.create()
                 builder.show()
        return super.onOptionsItemSelected(item)
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        uvm = ViewModelProvider(this).get(UserViewModel::class.java)
          name = findViewById(R.id.name)
          age = findViewById(R.id.age)
          button = findViewById(R.id.button)
          listTv = findViewById(R.id.listView)
          toolbar = findViewById(R.id.toolbarMain)



          setSupportActionBar(toolbar)
          toolbar.setTitleTextColor(getColor(R.color.blue))
           title = ("     Каталог пользователей")

        val adapter = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,list)
        listTv.adapter = adapter

        uvm.users.observe(this,{ list.clear();it.forEach { list += it }; adapter.notifyDataSetChanged()})

                 button.setOnClickListener {
                  if( name.text.isEmpty() or age.text.isEmpty()) { return@setOnClickListener }

                     uvm.users.postValue(list + listOf(Users(name.text.toString(),age.text.toString())))

                    name.text.clear();age.text.clear()

                       }

                listTv.onItemClickListener = MyDialjg.myDialog(this,adapter)
    }

}

 class Users (val name: String,val age: String){

    override fun toString() = "Имя: ${name}            Возраст: ${age}" }
