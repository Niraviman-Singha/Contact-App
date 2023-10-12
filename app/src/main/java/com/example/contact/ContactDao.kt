package com.example.contact

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactDao {
    @Query("SELECT * FROM contact_table")
    fun getAll():List<Contact>

    @Query("SELECT * FROM contact_table WHERE phone_no LIKE :phone LIMIT 1")
    suspend fun findByRoll(phone:Int):Contact

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: Contact)


    @Query("SELECT (SELECT COUNT(*) FROM contact_table) == 0")
    fun isEmpty(): Boolean

}