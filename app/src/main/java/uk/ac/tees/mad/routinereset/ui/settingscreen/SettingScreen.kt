package uk.ac.tees.mad.routinereset.ui.settingscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.routinereset.ui.settingscreen.component.ClearDataCard
import uk.ac.tees.mad.routinereset.ui.settingscreen.component.LogoutCard
import uk.ac.tees.mad.routinereset.ui.settingscreen.component.NotificationCard
import uk.ac.tees.mad.routinereset.ui.settingscreen.component.ReminderCard
import uk.ac.tees.mad.routinereset.ui.settingscreen.component.RoutineResetCard
import uk.ac.tees.mad.routinereset.ui.settingscreen.component.SettingTopBar
@Composable
fun SettingScreen(
    onBackClick:()-> Unit,
    onLogoutClick:()-> Unit,
    settingViewModel: SettingViewModel = viewModel()
){

    val uiState by settingViewModel.settingUiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        SettingTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            onBackClick = onBackClick
        )
        Spacer(modifier = Modifier.height(24.dp))
        NotificationCard(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            isNotificationEnabled = uiState.isNotificationOn,
            onToggle = settingViewModel::onNotificationClick
        )
        Spacer(modifier = Modifier.height(8.dp))
        RoutineResetCard(
            onResetClick = {
                settingViewModel.onResetClick{
                    //show some success message
                }
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ClearDataCard(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            onClearClick = {
                settingViewModel.onClearDataClick{
                    //show some success message
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ReminderCard(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            onReminderClick = {
                settingViewModel.onSetReminderClick {
                    //show some success message
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
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


@Composable
@Preview(showBackground = true)
fun SettingPreview() {
    SettingScreen(
        {},
        {}
    )
}