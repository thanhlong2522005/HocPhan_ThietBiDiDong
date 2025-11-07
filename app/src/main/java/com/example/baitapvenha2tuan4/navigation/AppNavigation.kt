package com.example.baitapvenha2tuan4.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.baitapvenha2tuan4.R
import com.example.baitapvenha2tuan4.ui.screen.detail.DetailScreen
import com.example.baitapvenha2tuan4.ui.screen.login.LoginScreen
import com.example.baitapvenha2tuan4.ui.screen.product.ProductScreen
import com.example.baitapvenha2tuan4.ui.screen.home.HomeScreen
import com.example.baitapvenha2tuan4.ui.screen.task.TaskListScreen
import com.example.baitapvenha2tuan4.ui.screen.task.TaskDetailScreen

object AppRoutes {
    const val LOGIN = "login_screen"
    const val HOME = "home_screen"
    const val DETAIL = "detail_screen"
    const val PRODUCT = "product_screen"

    const val TASK_LIST = "task_list"
    const val TASK_DETAIL = "task_detail/{id}"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoutes.LOGIN) {
        composable(AppRoutes.LOGIN) { LoginScreen(navController) }
        composable(AppRoutes.HOME) { HomeScreen(navController) }
        composable(AppRoutes.DETAIL) { DetailScreen(navController) }
        composable(AppRoutes.PRODUCT) { ProductScreen(navController) }

        composable(AppRoutes.TASK_LIST) { TaskListScreen(navController) }

        composable(
            route = AppRoutes.TASK_DETAIL,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            TaskDetailScreen(navController = navController, taskId = id)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navController: NavController, canNavigateBack: Boolean) {
    TopAppBar(
        title = { },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

@Composable
fun LogoImage() {
    Image(
        painter = painterResource(id = R.drawable.uth_logo),
        contentDescription = "UTH Logo",
        modifier = Modifier
            .height(100.dp)
            .padding(bottom = 24.dp)
    )
}
