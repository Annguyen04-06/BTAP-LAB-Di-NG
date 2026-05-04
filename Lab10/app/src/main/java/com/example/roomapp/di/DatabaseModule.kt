package com.example.roomapp.di

import android.content.Context
import com.example.roomapp.data.ItemDatabase
import com.example.roomapp.data.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Singleton
    @Provides
    fun provideItemDatabase(
        @ApplicationContext context: Context
    ): ItemDatabase {
        return ItemDatabase.getDatabase(context)
    }
    
    @Singleton
    @Provides
    fun provideItemRepository(
        database: ItemDatabase
    ): ItemRepository {
        return ItemRepository(database.itemDao())
    }
}
