package uk.ac.tees.mad.routinereset.ui.editscreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EditTopBar(
    modifier : Modifier,
    onBackClick : () -> Unit,
    routineId:Int,
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(modifier = Modifier.padding(horizontal = 16.dp)){
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "back",
                modifier = Modifier
                    .clickable{
                    onBackClick()
                }
            )
        }
        
        Text(
            text = if(routineId == 1) "Morning Routine" else "Evening Routine",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun EditTopBarPreview(){
    EditTopBar(
        modifier = Modifier,
        onBackClick = {},
        routineId = 1,
    )
}