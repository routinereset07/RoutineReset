package uk.ac.tees.mad.routinereset.ui.RoutineListScreen

import uk.ac.tees.mad.routinereset.data.local.RoutineTaskEntity
import uk.ac.tees.mad.routinereset.domain.model.RoutineType

data class RoutineListUiState(
    val morningRoutine: List<RoutineTaskEntity> = emptyList(),
    val eveningRoutine : List<RoutineTaskEntity> = emptyList() ,
    val showDialog : Boolean = false,
    val isLoading : Boolean = false,
    val error:String? = null,
    val selectedRoutineType : RoutineType = RoutineType.MORNING
)