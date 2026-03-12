package uk.ac.tees.mad.routinereset.ui.homescreen

import uk.ac.tees.mad.routinereset.data.local.RoutineTaskEntity

data class HomeUiState (
    val morningRoutine: List<RoutineTaskEntity> = emptyList(),
    val eveningRoutine: List<RoutineTaskEntity> = emptyList(),
    val completedTask:Int = 0,
    val totalTask: Int=0,
    val isMorningRoutineExpanded : Boolean = false,
    val isEveningRoutineExpanded : Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
)