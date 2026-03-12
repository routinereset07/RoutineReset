package uk.ac.tees.mad.routinereset.ui.EditRoutineScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.routinereset.data.local.RoutineTaskEntity
import uk.ac.tees.mad.routinereset.di.AppModule
import uk.ac.tees.mad.routinereset.domain.model.RoutineType
import uk.ac.tees.mad.routinereset.domain.repository.RoutineRepository

class EditViewModel (
    private val routineRepository: RoutineRepository = AppModule.routineRepository
): ViewModel() {
    private val _editScreenUiState = MutableStateFlow(EditScreenUiState())
    val editScreenUiState = _editScreenUiState.asStateFlow()


    init {
        fetchAllTasks()
    }


    fun fetchAllTasks() {
        viewModelScope.launch {
            routineRepository
                .observeAllRoutineTasks()
                .collect { routines ->
                    _editScreenUiState.update { it ->
                        val morningTask = routines.filter { it.routineId == 1 }
                        val eveningTask = routines.filter { it.routineId == 2 }
                        it.copy(
                            morningRoutine = morningTask,
                            eveningRoutine = eveningTask,
                        )
                    }
                }
        }
    }

    fun onRoutineSelect(routine: RoutineType) {
        _editScreenUiState.update {
            it.copy(
                selectedRoutineType = routine
            )
        }
    }

    //later---->>>>
    fun onEditClick() {

    }

    fun onDeleteClick(
        taskId: Int,
        routineId: Int
    ) {
        viewModelScope.launch {
            routineRepository.deleteTask(
                taskId,
                routineId
            )
        }
    }

    fun onAddNewTask() {
        _editScreenUiState.update {
            it.copy(
                showDialog = true
            )
        }
    }

    fun onCancel() {
        _editScreenUiState.update {
            it.copy(
                showDialog = false
            )
        }
    }

    fun onSaveNewTask(
        newTask: String,
        routineType: Int,
    ) {
        viewModelScope.launch {
            routineRepository.addTask(
                RoutineTaskEntity(
                    routineId = routineType,
                    title = newTask
                )
            )
            _editScreenUiState.update {
                it.copy(
                    showDialog = false
                )
            }
        }
    }
}

