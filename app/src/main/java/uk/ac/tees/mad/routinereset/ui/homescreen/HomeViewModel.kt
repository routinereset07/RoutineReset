package uk.ac.tees.mad.routinereset.ui.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.routinereset.di.AppModule
import uk.ac.tees.mad.routinereset.domain.repository.RoutineRepository

class HomeViewModel(private val routineRepository: RoutineRepository = AppModule.routineRepository) : ViewModel(){
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    init {
        fetchAllRoutines()
    }


    fun fetchAllRoutines(){
        viewModelScope.launch {
         routineRepository
             .observeAllRoutineTasks()
             .collect { routines->
                 _homeUiState.update { it ->
                     val morningTask = routines.filter { it.routineId == 1 }
                     val eveningTask = routines.filter { it.routineId == 2 }
                     val completedTask = routines.filter { it.isCompleted }
                     it.copy(
                         morningRoutine = morningTask,
                         eveningRoutine = eveningTask,
                         completedTask =completedTask.size,
                         totalTask = routines.size
                     )
                   }
                 }
             }
    }



    fun onToggleRoutine(taskId:Int,
                        routineId : Int,
                        isCompleted: Boolean){
        viewModelScope.launch {
            routineRepository.updateTaskCompletion(
                taskId,
                routineId,
                isCompleted
            )
        }
    }

    fun onMorningExpand(){
        _homeUiState.update {it->
            it.copy(
                isMorningRoutineExpanded = !it.isMorningRoutineExpanded
            )
        }
    }

    fun onEveningExpand(){
        _homeUiState.update{
            it.copy(
                isEveningRoutineExpanded = !it.isEveningRoutineExpanded
            )
        }
    }
}