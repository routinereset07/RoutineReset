package uk.ac.tees.mad.routinereset.ui.RoutineListScreen

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
import java.util.UUID

class RoutineListViewModel (
    private val routineRepository: RoutineRepository = AppModule.routineRepository
): ViewModel() {
    private val _editScreenUiState = MutableStateFlow(RoutineListUiState())
    val editScreenUiState = _editScreenUiState.asStateFlow()

    init {
        fetchAllTasks()
    }

    fun fetchAllTasks() {
        viewModelScope.launch {

            _editScreenUiState.update {
                it.copy(isLoading = true)
            }

            routineRepository
                .observeAllRoutineTasks()
                .collect { routines ->

                    val morningTask = routines.filter { it.routineId == 1 }
                    val eveningTask = routines.filter { it.routineId == 2 }

                    _editScreenUiState.update {
                        it.copy(
                            morningRoutine = morningTask,
                            eveningRoutine = eveningTask,
                            isLoading = false
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
    fun onDeleteClick(
        taskId: String,
        routineId: Int
    ) {
        viewModelScope.launch {
            _editScreenUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            routineRepository.deleteTask(
                taskId,
                routineId
            )
            _editScreenUiState.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }
}

