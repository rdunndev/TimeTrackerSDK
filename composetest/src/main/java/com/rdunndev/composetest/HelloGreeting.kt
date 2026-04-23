package com.rdunndev.composetest

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource

@Composable
fun HelloGreeting(name: String) {
    Text(text = "Alloha $name", color = colorResource(R.color.teal_200))
}
