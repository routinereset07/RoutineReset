package uk.ac.tees.mad.routinereset.ui.homescreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
    appName : String,
    date : String
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Text(text = appName)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = date,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold)
        }

        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Person,
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
        appName = "RoutineReset",
        date = "12/2/2024"
    )
}