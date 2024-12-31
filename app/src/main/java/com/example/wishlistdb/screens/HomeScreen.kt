package com.example.wishlistdb.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistdb.R
import com.example.wishlistdb.Screen
import com.example.wishlistdb.components.AppBar
import com.example.wishlistdb.components.WishListItem
import com.example.wishlistdb.viewmodel.WishViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: WishViewModel,
    popBackStackEntry: () -> Unit
) {
    val context = LocalContext.current
    val wishes = viewModel.wishes.collectAsState(initial = listOf())
    Scaffold(
        topBar = {
            AppBar(title = stringResource(id = R.string.home_screen_title)) {
//                popBackStackEntry()

            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddScreen.route + "/0L")
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add wishlist item")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(wishes.value, key = { wish -> wish.id }) { wish ->
                val dismissState = rememberDismissState(
                    confirmValueChange = {
                        if (it === DismissValue.DismissedToStart) {
                            viewModel.deleteWish(wish)
                        }
                        true
                    },
                    positionalThreshold = { it * .75f }
                )

                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color by animateColorAsState(
                            targetValue =
                            if (
                                dismissState.dismissDirection == DismissDirection.EndToStart
                            )
                                Color.Red
                            else Color.Transparent,
                            label = ""
                        )
                        val alignment = Alignment.CenterEnd
                        Row(modifier = Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(8.dp, 8.dp, 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.White
                            )
                        }
                    },
                    directions = setOf(DismissDirection.EndToStart),
                    dismissContent = {
                        WishListItem(wish = wish) {
                            val id = wish.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        }
                    }
                )

            }

        }
    }
}
