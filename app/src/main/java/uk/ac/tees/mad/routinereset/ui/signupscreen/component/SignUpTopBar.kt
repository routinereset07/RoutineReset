package uk.ac.tees.mad.routinereset.ui.signupscreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SignUpTopBar(modifier: Modifier = Modifier,
                 onNavBackClick: () -> Unit){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Box( modifier = Modifier
            .weight(1f)){
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "back",
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable{
                        onNavBackClick()
                    }
            )
        }
        Text(
            text = "Create Account",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(2f)
        )
    }
}






@Preview(showBackground = true)
@Composable
fun SignUpBarPreview(){
    SignUpTopBar(
        onNavBackClick = {}
    )
}