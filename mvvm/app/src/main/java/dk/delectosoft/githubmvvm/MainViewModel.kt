package dk.delectosoft.githubmvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dk.delectosoft.githubmvvm.repositories.FirebaseQueryLiveData


class MainViewModel : ViewModel() {

    private var userMutable = MutableLiveData<FirebaseUser>()
    private var contributionsMutable = FirebaseQueryLiveData(FirebaseFirestore.getInstance().collection("contributors"))

    val user: LiveData<FirebaseUser>
        get() = userMutable

    val contributions = contributionsMutable

    init {
        userMutable.value = FirebaseAuth.getInstance().currentUser
    }
}