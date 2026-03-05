package uk.ac.tees.mad.routinereset.ui.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.routinereset.R
import uk.ac.tees.mad.routinereset.domain.model.Routine
import uk.ac.tees.mad.routinereset.domain.model.Task
import uk.ac.tees.mad.routinereset.ui.homescreen.component.HomeScreenTopBar
import uk.ac.tees.mad.routinereset.ui.homescreen.component.ProgressCard
import uk.ac.tees.mad.routinereset.ui.homescreen.component.RoutineCard
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp)
        ) {
            HomeScreenTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding(),
                appName = "RoutineReset",
                date = "4/2/2026"
            )

            ProgressCard(
                completed = 4,
                total = 6,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            )

            Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(
                    R.drawable.outline_sunny_24
                ),
                contentDescription = null
            )
            Spacer(
                modifier = Modifier
                    .width(8.dp)
            )
            Text(
                text = "Morning Routine",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "6 Task",
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.End

            )
        }
            RoutineCard(
                routine = Routine(
                    id = "1",
                    name = "Morning Routine",
                    description = "Routine for the morning",
                    tasks = listOf(
                        Task(id = "1", title = "Wake up", isCompleted = true),
                        Task(id = "2", title = "Eat breakfast", isCompleted = true),
                        Task(id = "3", title = "Go to work", isCompleted = false),
                        Task(id = "4", title = "Code", isCompleted = false)
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(
                modifier = Modifier
                    .height(32.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(
                        R.drawable.outline_partly_cloudy_night_24
                    ),
                    contentDescription = null
                )
                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                )
                Text(
                    text = "Evening Routine",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "6 Task",
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.End

                )
            }
            RoutineCard(
                routine = Routine(
                    id = "1",
                    name = "Evening Routine",
                    description = "Routine for the morning",
                    tasks = listOf(
                        Task(id = "1", title = "Wake up", isCompleted = true),
                        Task(id = "2", title = "Eat breakfast", isCompleted = true),
                        Task(id = "3", title = "Go to work", isCompleted = false),
                        Task(id = "4", title = "Code", isCompleted = false)
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }

        // Floating "normal" button
        AddRoutineButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .systemBarsPadding()
                .padding(16.dp),
            onClick = {
                //
            }
        )
    }
}



@Composable
fun AddRoutineButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            //.fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
            )

            Text(
                text = "Add Routine",
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}



@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}
