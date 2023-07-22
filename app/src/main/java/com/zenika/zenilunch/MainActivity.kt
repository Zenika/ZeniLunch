package com.zenika.zenilunch

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.zenika.zenilunch.agency.AgencySelectionScreen
import com.zenika.zenilunch.detail.DetailScreen
import com.zenika.zenilunch.list.ListScreen
import com.zenika.zenilunch.suggestion.SuggestionDialog
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
        navController: NavHostController = rememberAnimatedNavController()
    ) {
        AnimatedNavHost(
            modifier = modifier,
            navController = navController,
            startDestination = "agency-selection"
        ) {
            composable("agency-selection") {
                AgencySelectionScreen(
                    modifier = Modifier.fillMaxSize(),
                    onExit = {
                        navController.navigate("list") {
                            popUpTo("agency-selection") {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable(
                route = "list"
            ) {
                ListScreen(
                    modifier = Modifier.fillMaxSize(),
                    goToDetailScreen = { restaurant ->
                        val name = restaurant.name
                        navController.navigate("detail/$name")
                    },
                    openSuggestionDialog = { navController.navigate("suggestions") }
                )
            }
            composable(
                route = "detail/{name}",
                arguments = listOf(navArgument("name") {
                    type = NavType.StringType
                })
            ) {
                DetailScreen(
                    modifier = Modifier.fillMaxSize(),
                    popBack = { navController.popBackStack() }
                )
            }
            dialog("suggestions") {
                SuggestionDialog(
                    onDismissRequest = { navController.popBackStack() }
                )
            }
        }
    }
}
