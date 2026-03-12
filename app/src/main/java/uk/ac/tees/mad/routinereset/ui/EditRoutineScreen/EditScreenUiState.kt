package uk.ac.tees.mad.routinereset.ui.EditRoutineScreen

import uk.ac.tees.mad.routinereset.data.local.RoutineTaskEntity
import uk.ac.tees.mad.routinereset.domain.model.RoutineType

data class EditScreenUiState(
    val morningRoutine: List<RoutineTaskEntity> = emptyList(),
    val eveningRoutine : List<RoutineTaskEntity> = emptyList() ,
    val showDialog : Boolean = false,
    val isLoading : Boolean = false,
    val error:String? = null,
    val selectedRoutineType : RoutineType = RoutineType.MORNING
)