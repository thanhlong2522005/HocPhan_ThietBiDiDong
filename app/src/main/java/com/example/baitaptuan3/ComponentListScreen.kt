package com.example.baitaptuan3

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitaptuan3.ui.theme.ComponentItemBackground
import com.example.baitaptuan3.ui.theme.SelfStudyBackground


data class ComponentItem(val name: String, val description: String, val route: String)
data class ComponentCategory(val name: String, val items: List<ComponentItem>)

val componentList = listOf(
    ComponentCategory(
        name = "Display",
        items = listOf(
            ComponentItem("Text", "Displays text", Routes.TEXT_DETAIL),
            ComponentItem("Image", "Displays an image", Routes.IMAGE_DETAIL)
        )
    ),
    ComponentCategory(
        name = "Input",
        items = listOf(
            ComponentItem("TextField", "Input field for text", Routes.TEXT_FIELD_DETAIL),
            ComponentItem("PasswordField", "Input field for passwords", Routes.PASSWORD_FIELD_DETAIL)
        )
    ),
    ComponentCategory(
        name = "Layout",
        items = listOf(
            ComponentItem("Column", "Arranges elements vertically", Routes.COLUMN_DETAIL),
            ComponentItem("Row", "Arranges elements horizontally", Routes.ROW_DETAIL)
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("UI Components List") })
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            componentList.forEach { category ->
                item {
                    Text(
                        text = category.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 8.dp)
                    )
                }
                items(category.items) { component ->
                    ComponentListItem(component = component, isSelfStudy = false) {
                        navController.navigate("${component.route}/${component.name}")
                    }
                }
            }
            item {
                ComponentListItem(
                    component = ComponentItem("Tự tìm hiểu", "Tìm ra tất cả các thành phần UI Cơ bản", ""),
                    isSelfStudy = true
                ) {
                }
            }
        }
    }
}

@Composable
fun ComponentListItem(component: ComponentItem, isSelfStudy: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelfStudy) SelfStudyBackground else ComponentItemBackground

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(backgroundColor)
            .clickable(onClick = onClick, enabled = !isSelfStudy)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(text = component.name, fontWeight = FontWeight.SemiBold, color = Color.Black)
        Text(text = component.description, style = MaterialTheme.typography.bodySmall, color = Color.DarkGray)
    }
}