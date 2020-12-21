package ru.kazantsev.eventsandposters.ui.viewmodel

import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class TabsViewModel @Inject constructor() : NavigationViewModel() {

    val savedAdapterState = MutableLiveData<SparseArray<Parcelable>>()

}
