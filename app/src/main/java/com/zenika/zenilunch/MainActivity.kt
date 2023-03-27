package com.zenika.zenilunch

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

var modifier = Modifier
    .fillMaxWidth()
    .padding(10.dp)

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppNavHost()
        }
    }

    @Composable
    fun MyAppNavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberAnimatedNavController(),
        startDestination: String = "list"
    ) {
        AnimatedNavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable("list") {
                ListScreen { restaurant ->
                    val name = restaurant.name
                    navController.navigate("detail/$name")
                }
            }
            composable("detail/{name}", arguments = listOf(navArgument("name") {
                type = NavType.StringType
            })) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name")
                DetailScreen()
            }
        }
    }
}