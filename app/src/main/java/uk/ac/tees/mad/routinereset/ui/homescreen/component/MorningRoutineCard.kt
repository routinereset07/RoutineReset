package uk.ac.tees.mad.routinereset.ui.homescreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.routinereset.domain.model.Routine
import uk.ac.tees.mad.routinereset.domain.model.Task

//@Composable
//fun MorningRoutineCard(
//    routine: Routine,
//    modifier: Modifier
//) {
//    var isExpanded by remember {
//        mutableStateOf(false)
//    }
//
//    val visibleTask = if (isExpanded) {
//        routine.tasks
//    } else {
//        routine.tasks.filterNot { it.isCompleted }.take(3)
//    }
//    Column(modifier = modifier){
//        visibleTask.forEach { task->
//            TaskCard(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(40.dp)
//                    .padding(vertical = 4.dp),
//                task = task
//            )
//        }
//    }
//}

@Composable
fun RoutineCard(
    routine: Routine,
    modifier: Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    val visibleTask = if (isExpanded) {
        routine.tasks
    } else {
        routine.tasks.filterNot { it.isCompleted }.take(3)
    }

    Column(modifier = modifier) {
        visibleTask.forEach { task ->
            TaskCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                task = task
            )
        }
    }
}



@Composable
fun TaskCard(
    modifier: Modifier,
    task: Task,
    onTaskClick: () -> Unit = {}
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
                    .clickable { onTaskClick() }
            )
        }
    }
}





//@Composable
//fun TaskCard(
//    modifier : Modifier,
//    task : Task,
//    onTaskClick: () -> Unit = {}
//){
//    Card(
//        modifier = modifier,
//        shape = RoundedCornerShape(32.dp),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 6.dp
//        ),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
//        )
//    ){
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            Text(
//                text = task.title,
//                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(start = 4.dp)
//            )
//
//            Box(
//                modifier = Modifier
//                    .size(24.dp)
//                    .clip(CircleShape)
//                    .background(if(task.isCompleted) MaterialTheme.colorScheme.primary
//                    else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
//                    .clickable{
//                        onTaskClick
//                    }
//            )
//        }
//    }
//}



