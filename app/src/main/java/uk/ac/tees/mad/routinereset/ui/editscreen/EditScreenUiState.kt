package uk.ac.tees.mad.routinereset.ui.editscreen

import uk.ac.tees.mad.routinereset.domain.model.RoutineType

data class EditScreenUiState (
    val title : String = "",
    val description : String = "",
    val selectedRoutineType : RoutineType = RoutineType.MORNING,
    val isLoading : Boolean = false,
    val navigateBack : Boolean = false,
    val error : String = ""
)