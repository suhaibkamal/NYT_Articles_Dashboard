package com.sk.nytarticlesdashboard.flow.home

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
fun BottomNavHosts(navController: NavHostController,viewModel: HomeViewModel) {
    NavHost(
        navController = navController,
        startDestination = BottomNavScreen.DashboardScreen.route
    ) {
        composable(route = BottomNavScreen.DashboardScreen.route) {
            DashboardScreen(viewModel=viewModel)
        }
        composable(route = BottomNavScreen.MoreScreen.route) {
           MoreScreen(homeViewModel =viewModel)
        }

    }
}