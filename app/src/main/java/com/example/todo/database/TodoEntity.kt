package com.example.todo.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var title: String,
    var description: String,
    var checked: Boolean
) : Parcelable