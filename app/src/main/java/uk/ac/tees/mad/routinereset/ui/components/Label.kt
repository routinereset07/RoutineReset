package uk.ac.tees.mad.routinereset.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Label(text: String,
          modifier : Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 14.sp,
        modifier = modifier
    )
    Spacer(modifier = Modifier
        .height(4.dp))
}




