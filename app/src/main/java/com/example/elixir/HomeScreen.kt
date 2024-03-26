package com.example.elixir


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.automirrored.outlined.DirectionsRun
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.SportsGymnastics
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(name : String,modifier: Modifier = Modifier){
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Run",
            selectedIcon = Icons.AutoMirrored.Filled.DirectionsRun,
            unselectedIcon = Icons.AutoMirrored.Outlined.DirectionsRun,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Workout",
            selectedIcon = Icons.Filled.SportsGymnastics,
            unselectedIcon = Icons.Outlined.SportsGymnastics,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Statistics",
            selectedIcon = Icons.Filled.BarChart,
            unselectedIcon = Icons.Outlined.BarChart,
            hasNews = false
        )
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Home") },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            //navController.navigate(item.title)
                        },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if(item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }

                                    }
                                    else if(item.hasNews) {
                                        Badge ()
                                    }

                                }) {
                                Icon(
                                    imageVector = if(index == selectedItemIndex) {
                                        item.selectedIcon
                                    }
                                    else {
                                        item.unselectedIcon
                                    }, contentDescription = item.title)
                            }
                        })
                }

            }
        }
    ) {
        values -> var userName by remember { mutableStateOf("") }
        userName = name
        Column(modifier = Modifier.padding(values)) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(
                    text = "Your Weekly Snapshots",
                    modifier = modifier
                )
                Spacer(modifier = Modifier.width(16.dp))

            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Column {
                    Text(
                        text = "Time",
                        modifier = modifier,
                        style = MaterialTheme.typography.labelLarge
                    )
                    TimeDisplay(hours = 0, minutes = 0)

                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Distance",
                        modifier = modifier,
                        style = MaterialTheme.typography.labelLarge
                    )
                    DistanceDisplay(0.00f)
                }
                Spacer(modifier = Modifier.width(150.dp))
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "See More")

                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start,
            ) {
                item {
                    Text(
                        text = "WorkOut History",
                        modifier = modifier,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Column {
                            Text(
                                text = "Time",
                                modifier = modifier,
                                style = MaterialTheme.typography.labelLarge
                            )
                            TimeDisplay(hours = 0, minutes = 0)

                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "Distance",
                                modifier = modifier,
                                style = MaterialTheme.typography.labelLarge
                            )
                            DistanceDisplay(0.00f)
                        }
                    }
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.screenshot1),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Column {
                            Text(
                                text = "Time",
                                modifier = modifier,
                                style = MaterialTheme.typography.labelLarge
                            )
                            TimeDisplay(hours = 0, minutes = 0)

                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "Distance",
                                modifier = modifier,
                                style = MaterialTheme.typography.labelLarge
                            )
                            DistanceDisplay(0.00f)
                        }
                    }
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.screenshot2),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }

            }


        }

    }


}


@Composable
fun TimeDisplay(hours: Int, minutes: Int) {
    Text(text = "${formatTime(hours, minutes)}")
}

fun formatTime(hours: Int, minutes: Int): String {
    return "${hours}h ${minutes}m"
}

@Composable
fun DistanceDisplay(distanceKm: Float) {
    Text(text = "${formatDistance(distanceKm)}")
}

fun formatDistance(distance: Float): String {
    return "${"%.2f".format(distance)} km"
}

@Preview(showBackground = true)
@Composable
fun HomeDesign() {
            HomeScreen("Rohith")

}

@Preview
@Composable
fun PreviewTimeDisplay() {
    TimeDisplay(hours = 0, minutes = 0)
}

@Preview
@Composable
fun PreviewDistanceDisplay() {
    DistanceDisplay(0.00f)
}
