package uk.ac.tees.mad.routinereset.ui.EditRoutineScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.routinereset.data.local.RoutineTaskEntity
import uk.ac.tees.mad.routinereset.domain.model.RoutineType
import uk.ac.tees.mad.routinereset.ui.EditRoutineScreen.component.EditScreenTopBar
import uk.ac.tees.mad.routinereset.ui.EditRoutineScreen.component.RoutineSelector

@Composable
fun EditRoutineScreen(onBackClick : () -> Unit,
                      editViewModel : EditViewModel = viewModel()){

    val uiState by editViewModel.editScreenUiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        if (uiState.showDialog) {
            EditTaskDialog(
                initialTitle = "Journaling",
                onSaveClick = { newTitle ->
                    editViewModel.onSaveNewTask(
                        newTitle,
                        routineType = when(uiState.selectedRoutineType){
                            RoutineType.EVENING -> 2
                            RoutineType.MORNING -> 1
                        }
                    )
                },
                onCancelClick = {
                    editViewModel.onCancel()
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ){
            EditScreenTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding(),
                onBackClick = onBackClick
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            RoutineSelector(
                selectedRoutine = uiState.selectedRoutineType,
                onRoutineSelected = {
                    editViewModel.onRoutineSelect(it)
                }
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            val list by remember(uiState) {
                derivedStateOf {
                    when (uiState.selectedRoutineType) {
                        RoutineType.MORNING -> uiState.morningRoutine
                        RoutineType.EVENING -> uiState.eveningRoutine
                    }
                }
            }

            LazyColumn (
                modifier = Modifier.weight(1f),
                state = rememberLazyListState(),
                contentPadding = PaddingValues(
                    vertical = 4.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(list){ it->
                    TaskItem(
                        task = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        onEditClick = {},
                        onDeleteClick = {it->
                            editViewModel.onDeleteClick(
                                it.taskId,
                                it.routineId
                            )
                        }
                    )
                }
            }
        }

        Button(
            onClick= {
                editViewModel
                    .onAddNewTask()
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(
                    Alignment.BottomCenter
                )
                .navigationBarsPadding()
                .padding(horizontal = 16.dp , vertical = 8.dp),

        ){
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
            Text(
                text = "Add New Task"
            )
        }

    }
}


@Composable
fun TaskItem(
    onDeleteClick:(RoutineTaskEntity)-> Unit,
    onEditClick: () -> Unit,
    task : RoutineTaskEntity,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp,
                    vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                modifier = Modifier.clickable{
                    onDeleteClick(
                        task
                    )
                }
            )
            Spacer(
                modifier = Modifier.width(4.dp)
            )
            Text(
                text = task.title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            Spacer(
                modifier = Modifier.width(4.dp)
            )
//            Icon(
//                imageVector = Icons.Default.Edit,
//                contentDescription = null,
//                modifier = Modifier.clickable{
//                    onEditClick()
//                }
//            )
        }
    }
}




@Composable
fun EditTaskDialog(
    initialTitle: String = "",
    onSaveClick: (String) -> Unit,
    onCancelClick: () -> Unit
) {
    var taskTitle by remember { mutableStateOf(initialTitle) }

    Dialog(onDismissRequest = onCancelClick) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(28.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Title
//                Text(
//                    text = "Task Name",
//                    style = MaterialTheme.typography.titleLarge,
//                    fontWeight = FontWeight.SemiBold
//                )

                // Label
                Text(
                    text = "TASK TITLE",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // TextField
                OutlinedTextField(
                    value = taskTitle,
                    onValueChange = { taskTitle = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Save button
                Button(
                    onClick = { onSaveClick(taskTitle) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(26.dp)
                ) {
                    Text(text = "Save Changes")
                }

                // Cancel button
                TextButton(
                    onClick = onCancelClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EditRoutineScreenPreview(){
    EditRoutineScreen(
        onBackClick = {}
    )
}