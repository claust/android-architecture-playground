package dk.delectosoft.githubmvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainViewModel : ViewModel() {


    private var userMutable = MutableLiveData<FirebaseUser>()

    val user: LiveData<FirebaseUser>
            get() = userMutable


    init {
        userMutable.value = FirebaseAuth.getInstance().currentUser
    }
}