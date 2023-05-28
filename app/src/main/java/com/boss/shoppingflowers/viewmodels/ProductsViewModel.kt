package com.boss.shoppingflowers.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.boss.shoppingflowers.di.FirestorePagingSource
import com.boss.shoppingflowers.utils.Constants.PAGE_SIZE
import com.google.firebase.firestore.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val queryProductsByName: Query) : ViewModel() {
    val flow = Pager(
        PagingConfig(
            pageSize = PAGE_SIZE
        )
    ){
        FirestorePagingSource(queryProductsByName)
    }.flow.cachedIn(viewModelScope)
}