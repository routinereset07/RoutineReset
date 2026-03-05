package uk.ac.tees.mad.routinereset.domain.model

data class Routine(
    val id: String,
    val name: String,
    val description: String,
    val tasks: List<Task>
)
