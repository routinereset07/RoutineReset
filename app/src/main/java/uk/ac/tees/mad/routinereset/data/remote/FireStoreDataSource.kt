package uk.ac.tees.mad.routinereset.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import uk.ac.tees.mad.routinereset.data.local.RoutineTaskEntity

class RoutineRemoteDataSource(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {
    private fun uid() = auth.currentUser!!.uid

    suspend fun addTask(task: RoutineTaskEntity) {
        firestore.collection("users")
            .document(uid())
            .collection(if(task.routineId == 1) "morning" else "evening")
            .add(task)
            .await()
    }

    suspend fun deleteTask(taskId: Int,routineId: Int) {
        firestore.collection("users")
            .document(uid())
            .collection(if(routineId == 1) "morning" else "evening")
            .document(taskId.toString())
            .delete()
            .await()
    }

    suspend fun updateTask(
        taskId: Int,
        routineId: Int,
        title: String,
        description: String
    ) {
        firestore.collection("users")
            .document(uid())
            .collection(if(routineId == 1) "morning" else "evening")
            .document(taskId.toString())
            .update(
                mapOf(
                    "title" to title,
                    "description" to description
                )
            )
            .await()
    }
}
