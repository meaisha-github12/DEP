package com.example.applicationtodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.applicationtodo.model.User

@Database(entities = [User::class], version = 1 , exportSchema = false)
abstract class userDatabase: RoomDatabase() {
    // represent database in room library
    abstract fun userDao(): UserDao

    companion object
    {
        @Volatile
        private var INSTANCE: userDatabase? =null
        fun getDatabase(context: Context):userDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null)
            {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(context.applicationContext, userDatabase::class.java, "user_database")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}