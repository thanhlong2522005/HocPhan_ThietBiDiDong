package com.example.baitapvenha1tuan4

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LibraryViewModel : ViewModel() {

    val students = mutableStateListOf<Student>()

    val allBooks = mutableStateListOf<Book>()

    val studentBooksMap = mutableStateMapOf<String, MutableList<Book>>()

    val studentNameInput = mutableStateOf("Nguyen Van A")

    val currentStudentName = mutableStateOf("Nguyen Van A")

    val borrowedBooks = derivedStateOf {
        studentBooksMap[currentStudentName.value] ?: mutableListOf()
    }

    init {
        addStudent("Nguyen Van A")
        addStudent("Nguyen Thi B")
        addStudent("Nguyen Van C")

        val book1 = Book("Sách 01")
        val book2 = Book("Sách 02")
        addBook(book1)
        addBook(book2)

        borrowBook("Nguyen Van A", book1)
        borrowBook("Nguyen Van A", book2)
        borrowBook("Nguyen Thi B", book1)
    }


    fun addStudent(name: String) {
        if (name.isNotBlank() && students.none { it.name == name }) {
            students.add(Student(name))
            studentBooksMap[name] = mutableListOf()
        }
    }

    fun addBook(book: Book) {
        if (book.title.isNotBlank() && allBooks.none { it.title == book.title }) {
            allBooks.add(book)
        }
    }

    fun setCurrentStudent() {
        if (students.any { it.name == studentNameInput.value }) {
            currentStudentName.value = studentNameInput.value
        }
    }

    fun borrowBook(studentName: String, book: Book) {
        val list = studentBooksMap[studentName]
        if (list != null && !list.contains(book)) {
            list.add(book)
        }
    }

    fun returnBook(studentName: String, book: Book) {
        studentBooksMap[studentName]?.remove(book)
    }
}