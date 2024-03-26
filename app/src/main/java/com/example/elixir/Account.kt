package com.example.elixir

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Account() {
    var targetBodyFat by remember { mutableStateOf("15") }
    var deadline by remember { mutableStateOf("10/4/2024") }
    var isEdit by remember { mutableStateOf(false)}
    var newTargetBodyFat by remember { mutableStateOf("") }
    var newDeadline by remember { mutableStateOf("") }
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
    val calendar = Calendar.getInstance()
    var selectedDate by remember {mutableStateOf(calendar.timeInMillis)}
    var showDatePicker by remember {mutableStateOf(false)}
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = Instant.now().toEpochMilli())
    var currentBodyFat by remember { mutableStateOf("20") }
    var newCurrentBodyFat by remember { mutableStateOf("") }
    var exercisePurpose by remember { mutableStateOf("Keep health") }
    var isExercisePurposeBoxExpanded by remember { mutableStateOf(false) }
    var selectedPurpose by remember { mutableStateOf("") }
    val exercisePurposeOption = listOf("Keep health", "Lose fat", "Enhance endurance", "Gain muscle")


    Row(){
        ClickableText(
            text = AnnotatedString("Go back"),
            onClick = {
            },
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.padding(10.dp))
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 35.dp, top = 35.dp)
    ) {
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = "xxxxxxxx@xxxx.com", modifier = Modifier.padding(top = 100.dp),style = TextStyle(fontSize = 25.sp))

            Row(){
                Column(
                    modifier = Modifier
                ) {
                    Text(text = "Current fat rate：$currentBodyFat%", modifier = Modifier.padding(top = 20.dp),style = TextStyle(fontSize = 18.sp))
                    Text(text = "Target fat rate：$targetBodyFat%", modifier = Modifier.padding(top = 20.dp),style = TextStyle(fontSize = 18.sp))
                    Text(text = "Deadline：$deadline", modifier = Modifier.padding(top = 20.dp),style = TextStyle(fontSize = 18.sp))
                    Text(text = "Purpose of exercise: $exercisePurpose", modifier = Modifier.padding(top = 20.dp),style = TextStyle(fontSize = 18.sp))
                }
                Column(){
                    TextButton(
                        onClick = {isEdit = true},
                        modifier = Modifier
                    ) {
                        Text(text = "Edit", modifier = Modifier.padding(top = 80.dp).offset(x=-13.dp))
                    }
                }
            }
        }

        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = "Total days of exercise：30", modifier = Modifier.padding(top = 20.dp),style = TextStyle(fontSize = 18.sp))
            Text(text = "Total running distance:100km", modifier = Modifier.padding(top = 20.dp),style = TextStyle(fontSize = 18.sp))
            Text(text = "Total running time:10h", modifier = Modifier.padding(top = 20.dp),style = TextStyle(fontSize = 18.sp))
        }

        Row(modifier = Modifier.padding(top = 20.dp)){
            Button(onClick = { }, modifier = Modifier.width(160.dp).offset(x=-5.dp).height(50.dp)) {
                Text(text = "Clear history data")
            }
            Button(onClick = { }, modifier = Modifier.width(160.dp).offset(x=5.dp).height(50.dp)) {
                Text(text = "log out")
            }
        }

        if (isEdit) {
            AlertDialog(
                onDismissRequest = { isEdit = false },
                title = { Text("Edit") },
                text = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        TextField(
                            value = newCurrentBodyFat,
                            onValueChange = {  newCurrentBodyFat= it },
                            label = { Text("Current fat rate") }
                        )
                        TextField(
                            value = newTargetBodyFat,
                            onValueChange = { newTargetBodyFat = it },
                            label = { Text("Target fat rate") }
                        )
                        if (showDatePicker) {
                            DatePickerDialog(
                                onDismissRequest = { showDatePicker = false },
                                confirmButton = {
                                    TextButton(onClick = {
                                        showDatePicker = false
                                        selectedDate = datePickerState.selectedDateMillis!!
                                        newDeadline = formatter.format(Date(selectedDate))
                                    }) {
                                        Text(text = "OK")
                                    }
                                },
                                dismissButton = {
                                    TextButton(onClick = {
                                        showDatePicker = false
                                    }) {
                                        Text(text = "Cancel")
                                    }
                                }
                            ) //end of dialog
                            { //still column scope
                                DatePicker(
                                    state = datePickerState
                                )
                            }
                        }
                        OutlinedTextField(
                            placeholder = { Text("dd/mm/yyyy") },
                            value = newDeadline,
                            onValueChange = { newDeadline = it },
                            label = { Text("Deadline") },
                            modifier = Modifier.padding(top = 10.dp),
                            trailingIcon = {
                                IconButton(
                                    onClick = { showDatePicker = true }
                                )
                                {
                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = "Toggle datepicker visibility"
                                    )
                                }
                            }
                        )
                        ExposedDropdownMenuBox(
                            expanded = isExercisePurposeBoxExpanded,
                            onExpandedChange = { isExercisePurposeBoxExpanded = it },
                            modifier = Modifier
                                .size(width = 280.dp, height = 75.dp)
                                .padding(top = 10.dp)
                        ) {
                            TextField(
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                                    .focusProperties {
                                        canFocus = false
                                    }
                                    .padding(bottom = 8.dp),
                                readOnly = true,
                                value = selectedPurpose,
                                onValueChange = {},
                                label = { Text("Purpose") },
                                //manages the arrow icon up and down
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExercisePurposeBoxExpanded)
                                },
                            )
                            ExposedDropdownMenu(
                                expanded = isExercisePurposeBoxExpanded,
                                onDismissRequest = { isExercisePurposeBoxExpanded = false }
                            )
                            {
                                exercisePurposeOption.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        text = { Text(selectionOption) },
                                        onClick = {
                                            selectedPurpose = selectionOption
                                            isExercisePurposeBoxExpanded = false
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                    )
                                }
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            targetBodyFat = newTargetBodyFat
                            deadline = newDeadline
                            currentBodyFat = newCurrentBodyFat
                            exercisePurpose = selectedPurpose
                            isEdit = false
                        }
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { isEdit = false }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountPreview() {
    Account()
}