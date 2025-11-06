package yusufs.turan.polltrial.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import yusufs.turan.polltrial.models.Poll

class PollListViewModel : ViewModel() {

    private val db = Firebase.firestore
    private val _polls = MutableStateFlow<List<Poll>>(emptyList())
    val polls: StateFlow<List<Poll>> = _polls

    init {
        fetchPolls()
    }

    private fun fetchPolls() {

        db.collection("polls")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val pollList = snapshot.documents.mapNotNull { document ->
                        document.toObject(Poll::class.java)?.copy(id = document.id)
                    }
                    _polls.value = pollList
                }
            }
    }
}