package com.example.elixir


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Login() {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(true) }
    Column( modifier = Modifier
        .fillMaxSize()
        .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Sign in",
            style = MaterialTheme.typography.headlineLarge,
            modifier= Modifier
                .padding(top = 80.dp)
                .align(Alignment.Start)
                .offset(x = -7.dp)
        )
        Text(text = "E-mail:", style = MaterialTheme.typography.titleMedium,
            modifier= Modifier
                .padding(top = 30.dp)
                .align(Alignment.Start))
        OutlinedTextField(
            value = userEmail,
            onValueChange = { userEmail = it },
            label = { Text("Email") },
            modifier= Modifier.padding(top=10.dp)
        )
        Text(text = "Password:",style = MaterialTheme.typography.titleMedium,
            modifier= Modifier
                .padding(top = 15.dp)
                .align(Alignment.Start))
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
            modifier= Modifier.padding(top=10.dp)
        )
        ElevatedButton(onClick = {}, modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()){ Text(text = "Sign in") }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ClickableText(text = AnnotatedString("Forget Password"),
                style = TextStyle(color = Color.Gray),
                modifier = Modifier.offset(x = 5.dp)) {
            }
            ClickableText(text = AnnotatedString("Sign up"),
                style = TextStyle(color = Color.Blue),
                modifier = Modifier.offset(x = -10.dp)) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Login()
}
