package com.example.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.contact.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactDatabase: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        contactDatabase = ContactDatabase.getDatabase(this)
        binding.saveBtn.setOnClickListener {
            saveData()
        }

    }


    @OptIn(DelicateCoroutinesApi::class)
    private fun saveData() {
        val firstName = binding.nameET.text.toString()
        val lastName = binding.emailET.text.toString()
        val rollNo = binding.phoneNoET.text.toString()

        if(firstName.isNotEmpty() && lastName.isNotEmpty() && rollNo.isNotEmpty()){
            val contact = Contact(null, firstName, lastName, rollNo.toInt())
            GlobalScope.launch (Dispatchers.IO ){
                contactDatabase.ContactDao().insert(contact)
            }
            binding.nameET.text?.clear()
            binding.emailET.text?.clear()
            binding.phoneNoET.text?.clear()

            Toast.makeText(this@MainActivity,"Contact saved", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this@MainActivity,"Please enter all the data", Toast.LENGTH_SHORT).show()
        }

    }
}