package com.example.myapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.ui.theme.OurPeach40
import com.example.myapplication.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    // 👇 AQUÍ ESTABA EL ERROR: Ahora recibe un String (el rol)
    onLoginSuccess: (String) -> Unit,
    loginViewModel: LoginViewModel = viewModel()
) {
    val passwordFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val fondoOscuro = Color(0xFF1E1B18)
    val textoClaro = Color(0xFFF2ECFF)

    var ejecutarLogin = {
        // 👇 Llamamos a la función del ViewModel
        val rolDetectado = loginViewModel.obtenerRol()

        if (rolDetectado != null) {
            onLoginSuccess(rolDetectado) // 👈 Le pasamos el rol al MainActivity
        } else {
            println("Error: Credenciales incorrectas")
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(fondoOscuro)
            .padding(32.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.logooursour),
            contentDescription = "Logo de Our Sour",
            modifier = Modifier
                .size(180.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = "Bienvenido",
            fontSize = 25.sp,
            color = textoClaro,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        OutlinedTextField(
            value = loginViewModel.mail,
            onValueChange = { loginViewModel.cambiarMail(it) },
            label = { Text("Correo", color = OurPeach40) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OurPeach40,
                unfocusedBorderColor = OurPeach40.copy(alpha = 0.5f),
                focusedTextColor = textoClaro,
                unfocusedTextColor = textoClaro,
                cursorColor = OurPeach40
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() })
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = loginViewModel.pass,
            onValueChange = { loginViewModel.cambiarPass(it) },
            label = { Text("Contraseña", color = OurPeach40) },
            modifier = Modifier.fillMaxWidth().focusRequester(passwordFocusRequester),
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(30.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OurPeach40,
                unfocusedBorderColor = OurPeach40.copy(alpha = 0.5f),
                focusedTextColor = textoClaro,
                unfocusedTextColor = textoClaro,
                cursorColor = OurPeach40
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    ejecutarLogin()
                }
            )
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            modifier = Modifier.fillMaxWidth().height(52.dp),
            onClick = { ejecutarLogin() },
            shape = RoundedCornerShape(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = OurPeach40, contentColor = Color.White)
        ) {
            Text("Iniciar Sesión", fontSize = 16.sp)
        }
    }
}