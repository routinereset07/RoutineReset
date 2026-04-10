package uk.ac.tees.mad.routinereset.ui.RoutineListScreen

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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.routinereset.data.local.RoutineTaskEntity
import uk.ac.tees.mad.routinereset.domain.model.RoutineType
import uk.ac.tees.mad.routinereset.ui.RoutineListScreen.component.RoutineListScreenTopBar
import uk.ac.tees.mad.routinereset.ui.RoutineListScreen.component.RoutineSelector

@Composable
fun EditRoutineScreen(onBackClick : () -> Unit,
                      onEditClick:(String , Int)-> Unit,
                      onAddNewTask:(Int)-> Unit,
                      editViewModel : RoutineListViewModel = viewModel()){

    val uiState by editViewModel.editScreenUiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ){
            RoutineListScreenTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding(),
                onBackClick = onBackClick
            )
            Spacer(
                modifier = Modifier.height(16.dp)
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
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                items(list){ it->
                    TaskItem(
                        task = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        onEditClick = onEditClick,//
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
            enabled = !uiState.isLoading,
            onClick = {
                onAddNewTask(
                    when (uiState.selectedRoutineType) {
                        RoutineType.MORNING -> 1
                        RoutineType.EVENING -> 2
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(
                    Alignment.BottomCenter
                )
                .navigationBarsPadding()
                .padding(horizontal = 16.dp, vertical = 8.dp),

        ){
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
            Text(
                text = "Add New Task"
            )
        }

        if(uiState.isLoading){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
    }
}


@Composable
fun TaskItem(
    onDeleteClick:(RoutineTaskEntity)-> Unit,
    onEditClick: (String , Int) -> Unit,
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
                .padding(
                    horizontal = 8.dp,
                    vertical = 12.dp
                ),
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
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                modifier = Modifier.clickable{
                    onEditClick(
                        task.taskId,
                        task.routineId
                    )
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EditRoutineScreenPreview(){
    EditRoutineScreen(
        onBackClick = {},
        {
            taskId, routineId ->
        },
        {}
    )
}