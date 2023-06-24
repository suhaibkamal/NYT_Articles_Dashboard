package com.sk.nytarticlesdashboard.flow.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(  val route: String,
                               val title: String,
                               val icon: ImageVector
){
    object DashboardScreen: BottomNavScreen( route = "dashboard",
        title = "Dashboard",
        icon = Icons.Filled.Home)

    object MoreScreen: BottomNavScreen( route = "more",
        title = "More",
        icon = Icons.Filled.MoreVert)
}
