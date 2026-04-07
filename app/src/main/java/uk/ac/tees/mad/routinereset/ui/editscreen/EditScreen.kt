package uk.ac.tees.mad.routinereset.ui.editscreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.routinereset.ui.editscreen.component.EditTopBar

@Composable
fun EditScreen(
    id: String?,
    routineId: Int,
    onBackClick: () -> Unit,
    editViewModel: EditScreenViewModel = viewModel()){

    val uiState by editViewModel.editScreenUiState.collectAsStateWithLifecycle()

    val isEditMode = id != null

    LaunchedEffect(routineId , id) {
        if (id != null) {
            Log.d("EditScreen", "Fetching task with ID: $id")
            editViewModel.fetchTaskById(id)
        }
    }
    LaunchedEffect(uiState.navigateBack) {
        if (uiState.navigateBack) {
            onBackClick()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){

        if(uiState.isLoading){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
             CircularProgressIndicator()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            EditTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding(),
                onBackClick = onBackClick,
                routineId = routineId,
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ){
                Text(
                    text = "Title",
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                EditTextField(
                    value = uiState.title,
                    onValueChange = editViewModel::onTitleChange,
                    placeholder = "Title",
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Description",
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                EditTextField(
                    value = uiState.description,
                    onValueChange = editViewModel::onDescriptionChange,
                    placeholder = "description",
                    minLines = 5,
                    singleLine = false
                )
            }
            Button(
                enabled = !uiState.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .navigationBarsPadding(),
                onClick = {
                    editViewModel.onSaveChanges(
                        isEditMode = isEditMode,
                        taskId = id ?: "",
                        routineId = routineId ,
                    )
                }
            ){
                Text(
                    text = if (isEditMode) "Save Changes" else "Add Task",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}




@Composable
fun EditTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    minLines: Int = 1
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFF8A94A6)
            )
        },
        singleLine = singleLine,
        minLines = minLines,
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF3B82F6),
            unfocusedBorderColor = Color(0xFFE5E7EB),
            focusedContainerColor = Color(0xFFF5F7FB),
            unfocusedContainerColor = Color(0xFFF5F7FB),
            cursorColor = Color(0xFF3B82F6)
        ),
        modifier = modifier
            .fillMaxWidth()
    )
}


@Preview(showBackground = true)
@Composable
fun EditScreenPreview(){

    EditScreen(
        id = "",
        routineId = 1,
        onBackClick = {}
    )
}