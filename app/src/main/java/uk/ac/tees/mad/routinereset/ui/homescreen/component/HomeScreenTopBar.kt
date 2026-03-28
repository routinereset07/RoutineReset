package uk.ac.tees.mad.routinereset.ui.homescreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreenTopBar(
    modifier : Modifier,
    onSettingClick:()-> Unit
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){

        Text(
            text = "Routine Reset",
            modifier = Modifier
                .padding(start = 16.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
        )


        IconButton(onClick = onSettingClick) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "profile"
            )
        }
    }
}



@Composable
@Preview(showBackground = true)
fun HomeScreenTopBarPreview(){
    HomeScreenTopBar(
        modifier = Modifier,
        {}
    )
}