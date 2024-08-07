package com.example.applicationtodo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.applicationtodo.model.User

@Dao
interface UserDao {
//Contains the methods used for accessing database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun addUser(user: User)
   @Query("SELECT * FROM user_table ORDER BY id ASC")
   fun readAllData(): LiveData<List<User>>
}