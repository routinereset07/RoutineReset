package uk.ac.tees.mad.routinereset.ui.homescreen

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.routinereset.R
import uk.ac.tees.mad.routinereset.ui.homescreen.component.HomeScreenTopBar
import uk.ac.tees.mad.routinereset.ui.homescreen.component.ProgressCard
import uk.ac.tees.mad.routinereset.ui.homescreen.component.RoutineCard

@Composable
fun HomeScreen(
    onEditRoutineClick: () -> Unit,
    onSettingClick: () -> Unit,
    homeViewModel : HomeViewModel = viewModel()
) {

    val uiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()

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
                onSettingClick = onSettingClick
            )

            ProgressCard(
                completed = uiState.completedTask,
                total = uiState.totalTask,
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
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "view all",
                modifier = Modifier
                    .clickable{
                        homeViewModel.onMorningExpand()
                    },
                textAlign = TextAlign.End,
                fontWeight = FontWeight.SemiBold
            )
        }
            RoutineCard(
                uiState.morningRoutine,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                    //.weight(0.8f),
                isExpanded = uiState.isMorningRoutineExpanded,
                onCheckBoxClick = homeViewModel::onToggleRoutine
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
                    fontWeight = FontWeight.SemiBold,
                  modifier = Modifier.weight(1f),

                )

                Text(
                    text = "view all",
                    modifier = Modifier
                        .clickable {
                           homeViewModel.onEveningExpand()
                        },
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.SemiBold

                )
            }
            RoutineCard(
                uiState.eveningRoutine,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                   // .weight(1f),
                isExpanded = uiState.isEveningRoutineExpanded,
                onCheckBoxClick = homeViewModel::onToggleRoutine
            )
        }

        // Floating "normal" button
        AddRoutineButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .systemBarsPadding()
                .padding(16.dp),
            onClick = onEditRoutineClick
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
                text = "Manage Routine",
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}



@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(
        onEditRoutineClick = {},
        onSettingClick = {}
    )
}


