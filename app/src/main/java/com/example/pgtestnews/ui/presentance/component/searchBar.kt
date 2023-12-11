package com.example.pgtestnews.ui.presentance.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SearchBarNews(
    value : String,
    onValueChange :(String)->Unit,
    onCloseIcon :() -> Unit,
    onActionSearch : (String) ->Unit,
    modifier: Modifier=Modifier
){

    TextField(
        value = value,
        onValueChange ={
            onValueChange(it)
        },
        placeholder = {
                      Text(text = "Search here")
        }
        ,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search,
                contentDescription ="Search")
        },
        trailingIcon = {
            IconButton(onClick = {
                if (value.isNotEmpty()) onValueChange("")
                else {
                    onCloseIcon()
                }
            }) {
               Icon(imageVector = Icons.Default.Close,
                   contentDescription =null )
            }
        },
        keyboardActions = KeyboardActions(
            onSearch = {onActionSearch(value)}
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        modifier = modifier.fillMaxWidth()
    )
}