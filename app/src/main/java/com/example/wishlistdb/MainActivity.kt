package com.example.wishlistdb

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wishlistdb.screens.HomeScreen
import com.example.wishlistdb.screens.WishDetailScreen
import com.example.wishlistdb.ui.theme.WishListDBTheme
import com.example.wishlistdb.viewmodel.WishViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WishListDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WishListApp()
                }
            }
        }
    }
}

@Composable
fun WishListApp()
{
    val navController = rememberNavController()
    val context = LocalContext.current
    val viewModel: WishViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route)
    {
        composable(route = Screen.HomeScreen.route)
        {
            HomeScreen(navController = navController, viewModel = viewModel)
            {
                Toast.makeText(
                    context,
                    "Back button clicked",
                    Toast.LENGTH_LONG).show()
            }
        }
        composable(
            route = Screen.AddScreen.route + "/{id}",
            arguments = listOf(navArgument("id"){
                type = NavType.LongType
                defaultValue = 0L
                nullable = false
            })
        )
        {
            val id = if(it.arguments != null) it.arguments!!.getLong("id") else 0L
            WishDetailScreen(id = id, viewModel = viewModel, navController = navController)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WishListDBTheme {
        WishListDbApp()
    }
}