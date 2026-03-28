package uk.ac.tees.mad.routinereset.ui.homescreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.routinereset.data.local.RoutineTaskEntity


@Composable
fun RoutineCard(
    routine: List<RoutineTaskEntity>,
    modifier: Modifier,
    isExpanded: Boolean,
    onCheckBoxClick: (String, Int, Boolean) -> Unit
) {

    val visibleTask = if (isExpanded) {
       routine
    } else {
       routine.filter {
          ! it.isCompleted
       }.take(3)
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        visibleTask.forEach { task ->
            TaskCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                task = task,
                onCheckBoxClick = onCheckBoxClick
            )
        }
    }
}



@Composable
fun TaskCard(
    modifier: Modifier,
    task: RoutineTaskEntity,
    onCheckBoxClick: (String, Int, Boolean) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = task.title,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                modifier = Modifier.weight(1f)
            )

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(
                        if (task.isCompleted)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                    .clickable {
                        onCheckBoxClick(
                            task.taskId,
                            task.routineId,
                            !task.isCompleted
                        )
                    }
            )
        }
    }
}





