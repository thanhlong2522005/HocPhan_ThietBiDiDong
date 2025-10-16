package com.example.baitaptuan3


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.padding

// Giữ nguyên object Routes
object Routes {
    const val WELCOME = "welcome"
    const val COMPONENT_LIST = "component_list"
    const val TEXT_DETAIL = "text_detail"
    const val IMAGE_DETAIL = "image_detail"
    const val TEXT_FIELD_DETAIL = "text_field_detail"
    const val ROW_DETAIL = "row_detail"
    const val PASSWORD_FIELD_DETAIL = "password_field_detail"
    const val COLUMN_DETAIL = "column_detail"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.WELCOME) {
        composable(Routes.WELCOME) {
            WelcomeScreen(navController = navController)
        }
        composable(Routes.COMPONENT_LIST) {
            ComponentListScreen(navController = navController)
        }
        // Sửa lại các route chi tiết để nhận tham số "title"
        composable("${Routes.TEXT_DETAIL}/{title}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "Text Detail"
            TextDetailScreen(title = title, onBack = { navController.popBackStack() })
        }
        composable("${Routes.IMAGE_DETAIL}/{title}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "Image Detail"
            ImageDetailScreen(title = title, onBack = { navController.popBackStack() })
        }
        composable("${Routes.TEXT_FIELD_DETAIL}/{title}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "TextField Detail"
            TextFieldDetailScreen(title = title, onBack = { navController.popBackStack() })
        }
        composable("${Routes.ROW_DETAIL}/{title}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "Row Layout"
            RowLayoutScreen(title = title, onBack = { navController.popBackStack() })
        }
        composable("${Routes.PASSWORD_FIELD_DETAIL}/{title}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "Password Field"
            PasswordFieldDetailScreen(title = title, onBack = { navController.popBackStack() })
        }

        composable("${Routes.COLUMN_DETAIL}/{title}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "Column Layout"
            ColumnLayoutScreen(title = title, onBack = { navController.popBackStack() })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenScaffold(title: String, onBack: () -> Unit, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        androidx.compose.foundation.layout.Box(
            modifier = androidx.compose.ui.Modifier.padding(paddingValues)
        ) {
            content()
        }
    }
}