package uk.ac.tees.mad.routinereset.ui.signupscreen.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmPasswordField(
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier
){
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = modifier,
        placeholder = { Text("Confirm Password") },
        shape = RoundedCornerShape(16.dp),
        singleLine = true
    )
}