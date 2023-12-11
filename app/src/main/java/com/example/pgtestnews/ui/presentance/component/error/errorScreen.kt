package com.example.pgtestnews.ui.presentance.component.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorScreen(
    error : String?,
    onRetry :() -> Unit
){
    Column(modifier = Modifier
    , verticalArrangement = Arrangement.Center
    , horizontalAlignment = Alignment.CenterHorizontally) {

        Row (modifier = Modifier
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){

            Icon(imageVector = Icons.Default.Warning,
                contentDescription =null )

            Spacer(modifier = Modifier.padding(horizontal = 4.dp))

            Text(text = error ?:"",
                style = MaterialTheme.typography.labelMedium)

        }

        Button(onClick = {onRetry()}) {
            Text(text = "Retry")
        }

    }

}