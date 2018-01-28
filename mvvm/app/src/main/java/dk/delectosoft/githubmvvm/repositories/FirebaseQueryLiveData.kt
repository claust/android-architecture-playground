package dk.delectosoft.githubmvvm.repositories

import android.arch.lifecycle.LiveData
import com.google.firebase.firestore.*
import timber.log.Timber


class FirebaseQueryLiveData(private val query: Query) : LiveData<QuerySnapshot>() {

    private val listener = MyValueEventListener()
    private var registration: ListenerRegistration? = null

    override fun onActive() {
        Timber.d("onActive")
        registration = query.addSnapshotListener(listener)
    }

    override fun onInactive() {
        Timber.d("onInactive")
        registration?.remove()
    }

    private inner class MyValueEventListener : EventListener<QuerySnapshot> {
        override fun onEvent(snapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) {
            if (exception != null) {
                Timber.e(exception, "onEvent")
                return
            }
            if (snapshot != null) {
                value = snapshot
            }
        }
    }
}