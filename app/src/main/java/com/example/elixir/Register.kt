package com.example.assignmentone

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale

@RequiresApi(0)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Data entry  screen
fun Register() {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(true) }
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
    val calendar = Calendar.getInstance()
    var selectedDate by remember {mutableStateOf(calendar.timeInMillis)}
    var showDatePicker by remember {mutableStateOf(false)}
    var userDateOfBirth by remember { mutableStateOf("")}
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = Instant.now().toEpochMilli())
    val genderOption = listOf("Male", "Female", "Other")
    var isGenderBoxExpanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf(genderOption[0]) }


    Column(modifier = Modifier.fillMaxSize()) {
        Row(){
            ClickableText(
                text = AnnotatedString("Go back"),
                onClick = {
                },
                style = TextStyle(color = Color.Gray),
                modifier = Modifier.padding(10.dp))
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 50.dp, end = 50.dp, top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Sign up",
                style = MaterialTheme.typography.headlineLarge,
                modifier= Modifier.align(Alignment.Start)
            )
            Text(
                text = "E-mail: ",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top=20.dp).offset(x= -112.dp)
            )
            OutlinedTextField(
                value = userEmail,
                onValueChange = { userEmail = it },
                label = { Text("Email") },
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = "Password:", style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(top = 15.dp).offset(x= -100.dp)
            )
            OutlinedTextField(
                value = userPassword,
                onValueChange = { userPassword = it },
                label = { Text("Password") },
                visualTransformation = if (passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    IconButton(
                        onClick = { passwordVisibility = !passwordVisibility }
                    ) {
                        Icon(
                            imageVector = if (passwordVisibility) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Toggle password visibility"
                        )
                    }
                },
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = "Date of birth:", style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(top = 15.dp).offset(x=-90.dp)
            )
            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            showDatePicker = false
                            selectedDate = datePickerState.selectedDateMillis!!
                            userDateOfBirth = formatter.format(Date(selectedDate))
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
                value = userDateOfBirth,
                onValueChange = { userDateOfBirth = it },
                label = { Text("DOB") },
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
            Text(
                text = "Gender:", style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(top = 15.dp).offset(x=-110.dp)
            )
            ExposedDropdownMenuBox(
                expanded = isGenderBoxExpanded,
                onExpandedChange = { isGenderBoxExpanded = it },
                modifier = Modifier.size(width = 280.dp, height = 75.dp)
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
                    value = selectedGender,
                    onValueChange = {},
                    label = { Text("Gender") },
                    //manages the arrow icon up and down
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isGenderBoxExpanded)
                    },
                )
                ExposedDropdownMenu(
                    expanded = isGenderBoxExpanded,
                    onDismissRequest = { isGenderBoxExpanded = false }
                )
                {
                    genderOption.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedGender = selectionOption
                                isGenderBoxExpanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }
            ElevatedButton(
                onClick = {}, modifier = Modifier
                    .padding(top = 25.dp)
                    .fillMaxWidth()
            ) { Text(text = "Sign up") }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    Register()
}