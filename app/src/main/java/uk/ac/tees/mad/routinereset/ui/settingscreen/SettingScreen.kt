package uk.ac.tees.mad.routinereset.ui.settingscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.routinereset.ui.settingscreen.component.ClearDataCard
import uk.ac.tees.mad.routinereset.ui.settingscreen.component.LogoutCard
import uk.ac.tees.mad.routinereset.ui.settingscreen.component.NotificationCard
import uk.ac.tees.mad.routinereset.ui.settingscreen.component.RoutineResetCard
import uk.ac.tees.mad.routinereset.ui.settingscreen.component.SettingTopBar

@Composable
fun SettingScreen(
    onBackClick:()-> Unit,
    onLogoutClick:()-> Unit,
    settingViewModel: SettingViewModel = viewModel()
) {

    val uiState by settingViewModel.settingUiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            SettingTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding(),
                onBackClick = onBackClick
            )
            Spacer(modifier = Modifier.height(24.dp))


            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(
                    16.dp
                )
            ) {

                NotificationCard(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    isNotificationEnabled = uiState.isNotificationOn,
                    onToggle = settingViewModel::onNotificationClick
                )

                RoutineResetCard(
                    onResetClick = {
                        settingViewModel.onResetClick {
                            //show some success message
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )

                ClearDataCard(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    onClearClick = {
                        settingViewModel.onClearDataClick {
                            //show some success message
                        }
                    }
                )
                LogoutCard(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    onLogoutClick = {
                        settingViewModel.onLogoutClick {
                            //show some success message
                            onLogoutClick()
                        }
                    }
                )
            }
        }


        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Routine Reset",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "App version 1.0.0",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun SettingPreview() {
    SettingScreen(
        {},
        {}
    )
}