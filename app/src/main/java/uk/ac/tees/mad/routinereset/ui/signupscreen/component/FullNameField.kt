package uk.ac.tees.mad.routinereset.ui.signupscreen.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FullNameField(
    name : String,
    onNameChange:(String)->Unit,
    modifier: Modifier = Modifier
){
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        placeholder = {
            Text(
                text = "Mohammad Arman"
            )
        },
        shape = RoundedCornerShape(16.dp),
        modifier = modifier,
        singleLine = true
    )
}