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
            .document(task.taskId)
            .set(task)
            .await()
    }

    suspend fun deleteTask(taskId: String,routineId: Int) {
        firestore.collection("users")
            .document(uid())
            .collection(if(routineId == 1) "morning" else "evening")
            .document(taskId)
            .delete()
            .await()
    }

    suspend fun updateTask(
        taskId: String,
        routineId: Int,
        title: String,
        description: String
    ) {
        firestore.collection("users")
            .document(uid())
            .collection(if(routineId == 1) "morning" else "evening")
            .document(taskId)
            .update(
                mapOf(
                    "title" to title,
                    "description" to description
                )
            )
            .await()
    }

    /// for the first time user log in and fetch then after log in out and comes
    suspend fun fetchAllTasks(): List<RoutineTaskEntity> {
        return fetchMorningTask() + fetchEveningTask()
    }

    private suspend fun fetchMorningTask(): List<RoutineTaskEntity>{
        return firestore
            .collection("users")
            .document(uid())
            .collection("morning")
            .get()
            .await()
            .toObjects(RoutineTaskEntity::class.java)
    }
    private suspend fun fetchEveningTask():List<RoutineTaskEntity>{
        return firestore.collection("users")
            .document(uid())
            .collection("evening")
            .get()
            .await()
            .toObjects(RoutineTaskEntity::class.java)
    }

}
