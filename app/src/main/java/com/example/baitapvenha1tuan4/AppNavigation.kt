package com.example.baitapvenha1tuan4

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

// --- Định nghĩa các route cho navigation ---
sealed class Screen(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object QuanLy : Screen("quanly", "Quản lý", Icons.Default.Home)
    object DSSach : Screen("dssach", "DS Sách", Icons.Default.List)
    object SinhVien : Screen("sinhvien", "Sinh viên", Icons.Default.Person)
}

val navItems = listOf(Screen.QuanLy, Screen.DSSach, Screen.SinhVien)

// --- Cấu trúc App chính với BottomBar ---
@Composable
fun AppNavigation(navController: NavHostController, viewModel: LibraryViewModel) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                navItems.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // NavHost chứa các màn hình
        NavHost(
            navController = navController,
            startDestination = Screen.QuanLy.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.QuanLy.route) {
                QuanLyScreen(viewModel = viewModel, navController = navController)
            }
            composable(Screen.DSSach.route) {
                DSSachScreen(viewModel = viewModel, navController = navController)
            }
            composable(Screen.SinhVien.route) {
                SinhVienScreen(viewModel = viewModel)
            }
        }
    }
}


// --- Màn hình 1: Quản lý (Giống mockup) ---
@Composable
fun QuanLyScreen(viewModel: LibraryViewModel, navController: NavController) {
    val studentName by viewModel.studentNameInput
    val borrowedBooks by viewModel.borrowedBooks
    val currentStudent by viewModel.currentStudentName
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hệ thống Quản lý Thư viện", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Phần Sinh viên
        Text("Sinh viên", style = MaterialTheme.typography.titleMedium, modifier = Modifier.fillMaxWidth())
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = studentName,
                onValueChange = { viewModel.studentNameInput.value = it },
                label = { Text("Tên sinh viên") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    viewModel.setCurrentStudent()
                    keyboardController?.hide()
                })
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                viewModel.setCurrentStudent()
                keyboardController?.hide()
            }) {
                Text("Thay đổi")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Phần Danh sách sách
        Text("Danh sách sách mượn", style = MaterialTheme.typography.titleMedium, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        Card(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)) {
            if (borrowedBooks.isEmpty()) {
                // Hiển thị khi không có sách nào
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Bạn chưa mượn quyển sách nào.\nNhấn 'Thêm' để bắt đầu hành trình đọc sách!",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp),
                        color = Color.Gray
                    )
                }
            } else {
                // Hiển thị danh sách sách đã mượn
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    items(borrowedBooks) { book ->
                        BookItemRow(
                            book = book,
                            isChecked = true, // Luôn check vì đây là sách đã mượn
                            onCheckedChange = {
                                // Checkbox ở đây sẽ dùng để "Trả sách"
                                viewModel.returnBook(currentStudent, book)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nút "Thêm" (sẽ điều hướng qua màn hình DS Sách để chọn)
        Button(
            onClick = { navController.navigate(Screen.DSSach.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Thêm sách mượn")
        }
    }
}

// --- Màn hình 2: Danh sách Sách (Master list) ---
@Composable
fun DSSachScreen(viewModel: LibraryViewModel, navController: NavController) {
    val allBooks by remember { derivedStateOf { viewModel.allBooks } }
    var newBookTitle by remember { mutableStateOf("") }
    val currentStudent by viewModel.currentStudentName

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Quản lý Danh sách Sách", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Form thêm sách mới
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = newBookTitle,
                onValueChange = { newBookTitle = it },
                label = { Text("Tên sách mới") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                viewModel.addBook(Book(newBookTitle))
                newBookTitle = "" // Reset
            }) {
                Text("Thêm")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Nhấn vào sách để mượn cho: $currentStudent", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))

        // Danh sách tất cả sách
        LazyColumn {
            items(allBooks) { book ->
                Button(
                    onClick = {
                        // Logic mượn sách
                        viewModel.borrowBook(currentStudent, book)
                        // Quay lại màn hình quản lý sau khi mượn
                        navController.navigate(Screen.QuanLy.route) {
                            popUpTo(navController.graph.startDestinationId)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(book.title)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

// --- Màn hình 3: Danh sách Sinh viên (Master list) ---
@Composable
fun SinhVienScreen(viewModel: LibraryViewModel) {
    val students by remember { derivedStateOf { viewModel.students } }
    var newStudentName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Quản lý Danh sách Sinh viên", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Form thêm sinh viên mới
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = newStudentName,
                onValueChange = { newStudentName = it },
                label = { Text("Tên sinh viên mới") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                viewModel.addStudent(newStudentName)
                newStudentName = "" // Reset
            }) {
                Text("Thêm")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Danh sách tất cả sinh viên
        LazyColumn {
            items(students) { student ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Text(student.name, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}


// --- Composable Tái sử dụng: 1 dòng Sách với Checkbox ---
@Composable
fun BookItemRow(
    book: Book,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(shape = MaterialTheme.shapes.medium) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = book.title, style = MaterialTheme.typography.bodyLarge)
        }
    }
}