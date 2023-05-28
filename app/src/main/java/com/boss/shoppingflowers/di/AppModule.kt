package com.boss.shoppingflowers.di

import com.boss.shoppingflowers.utils.Constants.NAME_PROPERTY
import com.boss.shoppingflowers.utils.Constants.PAGE_SIZE
import com.boss.shoppingflowers.utils.Constants.PRODUCTS_COLLECTION
import com.google.firebase.firestore.Query.Direction.ASCENDING
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideQueryProductsByName() = FirebaseFirestore.getInstance()
        .collection(PRODUCTS_COLLECTION)
        .orderBy(NAME_PROPERTY, ASCENDING)
        .limit(PAGE_SIZE.toLong())
}