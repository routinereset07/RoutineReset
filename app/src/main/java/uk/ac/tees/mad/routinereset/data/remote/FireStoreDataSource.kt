package uk.ac.tees.mad.routinereset.data.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import uk.ac.tees.mad.routinereset.data.local.RoutineTaskEntity
import uk.ac.tees.mad.routinereset.preference.AppPreference

class RoutineRemoteDataSource(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {
    private fun uid(): String =
        auth.currentUser?.uid
            ?: throw IllegalStateException("User not authenticated")

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
    suspend fun deleteAllTasks() {
        val userDoc = firestore.collection("users").document(uid())

        // Morning
        val morningDocs = userDoc
            .collection("morning")
            .get()
            .await()

        for (doc in morningDocs.documents) {
            doc.reference.delete().await()
        }

        // Evening
        val eveningDocs = userDoc
            .collection("evening")
            .get()
            .await()

        for (doc in eveningDocs.documents) {
            doc.reference.delete().await()
        }
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


    suspend fun resetAllTask() {
        val userDoc = firestore
            .collection("users")
            .document(uid())

        val batch = firestore.batch()

        val morningSnapshot = userDoc
            .collection("morning")
            .get()
            .await()

        morningSnapshot.documents.forEach { doc ->
            batch.update(doc.reference, "isCompleted", false)
        }

        val eveningSnapshot = userDoc
            .collection("evening")
            .get()
            .await()

        eveningSnapshot.documents.forEach { doc ->
            batch.update(doc.reference, "isCompleted", false)
        }
        batch.commit().await()
    }
}
