package com.example.contact

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "email")
    val email: String?,
    @ColumnInfo(name = "phone_no")
    val phoneNo: Int?
)
