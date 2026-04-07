package uk.ac.tees.mad.routinereset.ui.editscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.routinereset.data.local.RoutineTaskEntity
import uk.ac.tees.mad.routinereset.di.AppModule
import uk.ac.tees.mad.routinereset.domain.repository.RoutineRepository
import java.util.UUID

class EditScreenViewModel(
    private val routineRepository: RoutineRepository = AppModule.routineRepository
) : ViewModel() {
    private val _editScreenUiState = MutableStateFlow(EditScreenUiState())
    val editScreenUiState = _editScreenUiState.asStateFlow()

    fun onSaveChanges(isEditMode: Boolean,
                 taskId: String,
                 routineId:Int, ) {
        viewModelScope.launch {
            _editScreenUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            val id = UUID.randomUUID().toString()
            if(isEditMode){
                routineRepository.updateTask(
                    taskId,
                    routineId,
                    _editScreenUiState.value.title,
                    _editScreenUiState.value.description
                )
                _editScreenUiState.update {
                    it.copy(
                        isLoading = false,
                        navigateBack = true
                    )
                }

            }else{
                routineRepository.addTask(
                    RoutineTaskEntity(
                        routineId,
                        id,
                        _editScreenUiState.value.title,
                        _editScreenUiState.value.description
                    )
                )
                _editScreenUiState.update {
                    it.copy(
                        isLoading = false,
                        title = "",
                        description = ""
                    )
                }
            }
        }
    }


    fun onTitleChange(newTitle : String){
        _editScreenUiState.update {
            it.copy(
                title = newTitle
            )
        }
    }

    fun onDescriptionChange(newDescription : String){
        _editScreenUiState.update {
            it.copy(
                description = newDescription
            )
        }
    }

    fun fetchTaskById(taskId : String){
        _editScreenUiState.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            val task = routineRepository.getTaskById(
                taskId = taskId
            )
            _editScreenUiState.update {
                it.copy(
                    title = task?.title ?: "",
                    description = task?.description ?: "",
                    isLoading = false
                )
            }
        }
    }
}