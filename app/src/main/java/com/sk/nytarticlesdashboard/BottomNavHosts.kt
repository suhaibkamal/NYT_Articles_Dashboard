package com.sk.nytarticlesdashboard

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
/**
#     #     #   #
#           #  #
# #     #
 * Created by Suhaib Kamal  on 6/22/23
 * skamal@blessedtreeit.com
 * <p>
 * Project Name: LearnCompse
 * <p>
 * SEDRA
 */

@Composable
fun BottomNavHosts(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavScreen.DashboardScreen.route
    ) {
        composable(route = BottomNavScreen.DashboardScreen.route) {
            DashboardScreen()
        }
        composable(route = BottomNavScreen.MoreScreen.route) {
           MoreScreen()
        }

    }
}