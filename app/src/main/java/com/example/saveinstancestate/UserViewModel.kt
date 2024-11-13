package com.example.saveinstancestate

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class  UserViewModel(): ViewModel() {

    var users = MutableLiveData<List<Users>>()
}
