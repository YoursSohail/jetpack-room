package com.yourssohail.jetpackroom

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yourssohail.jetpackroom.data.CheckIn
import com.yourssohail.jetpackroom.data.CheckInDao
import com.yourssohail.jetpackroom.data.CheckInDatabase
import com.yourssohail.jetpackroom.repository.CheckInRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CheckInModule {

    @Singleton
    @Provides
    fun getRepository(dao: CheckInDao): CheckInRepository {
        return CheckInRepository(dao)
    }

    @Singleton
    @Provides
    fun getDao(database: CheckInDatabase): CheckInDao {
        return database.checkInDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CheckInDatabase {
        return Room.databaseBuilder(
            context.applicationContext, CheckInDatabase::class.java, "checkin_database"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }
}