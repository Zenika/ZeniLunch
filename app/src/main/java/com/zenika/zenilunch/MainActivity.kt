package com.zenika.zenilunch

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.zenika.zenilunch.ui.theme.ZeniLunchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZeniLunchTheme {
                MyAppNavHost()
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
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
                ListScreen(onRestaurantClick = { restaurant ->
                    val name = restaurant.name
                    navController.navigate("detail/$name")
                },
                    onSuggestionClick = { restaurant ->
                        val name = restaurant.name
                        navController.navigate("suggestion/$name")
                    }
                )
            }
            composable("detail/{name}", arguments = listOf(navArgument("name") {
                type = NavType.StringType
            })) {
                DetailScreen()
            }
            dialog("suggestion/{name}", arguments = listOf(navArgument("name") {
                type = NavType.StringType
            })) {
                SuggestionDialog()
            }
        }
    }
}