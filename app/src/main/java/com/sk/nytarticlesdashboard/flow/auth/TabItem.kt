package com.sk.nytarticlesdashboard.flow.auth

import androidx.compose.runtime.Composable

data class TabItem(val title: String,
                   val screen: @Composable () -> Unit )
