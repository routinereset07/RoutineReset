package uk.ac.tees.mad.routinereset.ui.RoutineListScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.routinereset.domain.model.RoutineType


@Composable
fun RoutineSelector(
    selectedRoutine: RoutineType,
    onRoutineSelected: (RoutineType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(50)
            )
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        RoutineTab(
            modifier =Modifier.weight(1f) ,
            text = "Morning Routine",
            selected = selectedRoutine == RoutineType.MORNING,
            onClick = { onRoutineSelected(RoutineType.MORNING) }
        )

        RoutineTab(
            modifier =Modifier.weight(1f) ,
            text = "Evening Routine",
            selected = selectedRoutine == RoutineType.EVENING,
            onClick = { onRoutineSelected(RoutineType.EVENING) }
        )
    }
}


@Composable
fun RoutineTab(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(
                if (selected)
                    MaterialTheme.colorScheme.primary
                else
                    Color.Transparent
            )
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (selected)
                MaterialTheme.colorScheme.onPrimary
            else
                MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.SemiBold
        )
    }
}



@Composable
@Preview(showBackground = true)
fun RoutineSelectorPreview(){
    RoutineSelector(
        selectedRoutine = RoutineType.MORNING,
        onRoutineSelected = {}
    )
}