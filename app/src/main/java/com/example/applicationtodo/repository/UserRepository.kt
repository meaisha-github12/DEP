package com.example.applicationtodo.repository

import androidx.lifecycle.LiveData
import com.example.applicationtodo.database.UserDao
import com.example.applicationtodo.model.User

class UserRepository(private  val userDao: UserDao) {
    val readAllData : LiveData<List<User>> = userDao.readAllData()
            suspend fun addUser (user: User)
            {
                userDao.addUser(user)
            }
}