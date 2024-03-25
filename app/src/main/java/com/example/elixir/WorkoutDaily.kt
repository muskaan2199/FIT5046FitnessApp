package com.example.elixir

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.automirrored.outlined.DirectionsRun
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.DirectionsRun
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.SportsGymnastics
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


//@Composable
//fun WorkoutDailyScreen() {
//    LazyVerticalGrid(
//        modifier = Modifier.fillMaxSize(),
//        contentPadding = PaddingValues(vertical = 16.dp),
//        columns = GridCells.Fixed(2)
//    ) {
//        itemsIndexed(MainActivity.items) {index, item ->
//            Spacer(modifier = Modifier.width(8.dp))
//            GridItem(item = item)
//            Spacer(modifier = Modifier.width(8.dp))
//        }
//
//    }
//}

@Composable
fun GridItem(
    item:Item,
    navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .height(250.dp)
            .width(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(170.dp)
                .clip(RoundedCornerShape(30.dp))
                .clickable(onClick = {navHostController.navigate("workout_list") }),
            painter = painterResource(id = item.image),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,

        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(text = item.title, fontWeight = FontWeight.SemiBold)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutDailyScreen(navHostController: NavHostController) {
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
                title = { Text(text = "My Workout") },
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
    ) { values ->
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize()
                .padding(values),

            contentPadding = PaddingValues(vertical = 16.dp),
            columns = GridCells.Fixed(2)
        ) {
            itemsIndexed(MainActivity.items) {index, item ->
                Spacer(modifier = Modifier.width(8.dp))
                GridItem(item = item, navHostController)
                Spacer(modifier = Modifier.width(8.dp))
            }

        }

    }

}
