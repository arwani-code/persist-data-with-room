package com.arwani.ahmad.persist_data_with_room

import android.icu.text.CaseMap.Title
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arwani.ahmad.persist_data_with_room.ui.navigation.InventoryNavHost

@Composable
fun InventoryApp(navController: NavHostController = rememberNavController()) {
    InventoryNavHost(navController = navController)
}

@Composable
fun InventoryTopAppBar(
    title: String,
    canNavigationBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {}
) {
    if (canNavigationBack) {
        TopAppBar(
            title = { Text(title) },
            modifier = modifier,
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                            id = R.string.back_button
                        )
                    )
                }
            }
        )
    } else {
        TopAppBar(title = { Text(text = title) }, modifier = modifier)
    }
}