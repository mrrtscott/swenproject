package com.uwi.loanhub.models

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class LoanHubDatabase : RoomDatabase() {

    abstract fun UserDao(): UserDao


    companion object {

        @Volatile
        private var INSTANCE: LoanHubDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): LoanHubDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoanHubDatabase::class.java,
                    "LoanHub_Database"
                ).addCallback(UserDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }

        }

    }

    private class UserDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.UserDao())
                }

            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun populateDatabase(userDao: UserDao) {

            var current = LocalDateTime.now()
            var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            var formatted = current.format(formatter)

            userDao.deleteAllUsers()

            var user = User(0, "Romaro", "Scott", "scott.r.ja@gmail.com", "scottja", "12345678", "Male", formatted, 900000.00, "Kingston", "Kingston", "Scotiabank", "Long Term", 500000.00, "Engineer", formatted)
            userDao.addNewUser(user)


        }


    }


}