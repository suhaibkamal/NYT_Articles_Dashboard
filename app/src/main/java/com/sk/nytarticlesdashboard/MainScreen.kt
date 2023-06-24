package com.sk.nytarticlesdashboard


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.sk.nytarticlesdashboard.flow.home.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier,viewModel: HomeViewModel) {
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier,
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Box(modifier = Modifier.padding(paddingValues = it)) {
            BottomNavHosts(navController = navController,viewModel)

        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavScreen.DashboardScreen,
        BottomNavScreen.MoreScreen,

    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem (
        label = {
            Text(text = screen.title, color = MaterialTheme.colorScheme.onPrimary)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        modifier = Modifier.background(color = MaterialTheme.colorScheme.primary),
        unselectedContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = ContentAlpha.medium),
        selectedContentColor = MaterialTheme.colorScheme.onPrimary,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}