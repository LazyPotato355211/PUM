package com.example.ap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.ap.ui.theme.ApTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}


data class Task(val id: Int, val description: String, val maxPoints: Int)

data class AssignmentList(
    val id: String,
    val subject: String,
    val listNumber: Int,
    val grade: Double,
    val tasks: List<Task>
)

val sampleAssignmentLists = listOf(
    AssignmentList("PUM1_L1", "Programowanie Urządzeń Mobilnych 1", 1, 4.5, listOf(
        Task(1, "Implementacja FizzBuzz", 3), Task(2, "Sprawdzenie palindromu", 3), Task(3, "Trójkąt Pascala", 4)
    )),
    AssignmentList("PUM1_L2", "Programowanie Urządzeń Mobilnych 1", 2, 5.0, listOf(
        Task(1, "Funkcje rozszerzające", 4), Task(2, "Funkcje wyższego rzędu", 6)
    )),
    AssignmentList("SO_L1", "Systemy Operacyjne", 1, 3.5, listOf(
        Task(1, "Implementacja semafora", 5), Task(2, "Problem producenta-konsumenta", 5)
    )),
    AssignmentList("SO_L2", "Systemy Operacyjne", 2, 4.0, listOf(
        Task(1, "Algorytmy szeregowania CPU", 6), Task(2, "Zarządzanie pamięcią", 4)
    ))
)


sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Lists : BottomNavItem("lists", "Listy zadań", Icons.AutoMirrored.Filled.List)
    object Grades : BottomNavItem("grades", "Oceny", Icons.Filled.Star)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavItems = listOf(BottomNavItem.Lists, BottomNavItem.Grades)

    // dolny pasek - główne ekrany
    val showBottomBar = currentRoute in bottomNavItems.map { it.route }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Aplikacja Uczelniana") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                navigationIcon = {
                    if (!showBottomBar && currentRoute?.startsWith("detail") == true) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Wróć")
                        }
                    }
                }
            )
        },
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) },
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Lists.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // lz
            composable(BottomNavItem.Lists.route) {
                AssignmentListsScreen(
                    lists = sampleAssignmentLists,
                    onListClick = { listId ->
                        navController.navigate("detail/$listId")
                    }
                )
            }

            // oc
            composable(BottomNavItem.Grades.route) {
                GradesSummaryScreen(lists = sampleAssignmentLists)
            }

            // szcz list
            composable(
                route = "detail/{listId}",
                arguments = listOf(navArgument("listId") { type = NavType.StringType })
            ) { backStackEntry ->
                val listId = backStackEntry.arguments?.getString("listId")
                val selectedList = sampleAssignmentLists.find { it.id == listId }

                ListDetailScreen(assignmentList = selectedList)
            }
        }
    }
}


@Composable
fun AssignmentListsScreen(
    lists: List<AssignmentList>,
    onListClick: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(lists) { assignment ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onListClick(assignment.id) },
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "${assignment.subject} - Lista ${assignment.listNumber}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Liczba zadań: ${assignment.tasks.size}")
                        Text(
                            text = "Ocena: ${assignment.grade}",
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun GradesSummaryScreen(lists: List<AssignmentList>) {
    // listy po przedmiocie i obliczanie średniej
    val averageGrades = remember(lists) {
        lists.groupBy { it.subject }
            .mapValues { (_, subjectLists) ->
                val average = subjectLists.map { it.grade }.average()
                // 2 miejsca po przecinku
                String.format(Locale.US, "%.2f", average)
            }
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = "Średnia ocen z przedmiotów",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        items(averageGrades.toList()) { (subject, average) ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = subject,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = average,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}


@Composable
fun ListDetailScreen(assignmentList: AssignmentList?) {
    if (assignmentList == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Nie znaleziono listy zadań.")
        }
        return
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            color = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Lista ${assignmentList.listNumber}: ${assignmentList.subject}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Ocena końcowa: ${assignmentList.grade}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }


        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(assignmentList.tasks) { task ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Zadanie ${task.id}",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = task.description,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Text(
                            text = "${task.maxPoints} pkt",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
