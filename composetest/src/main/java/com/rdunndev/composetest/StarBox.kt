package com.rdunndev.composetest

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun StartBox() {
    Box(modifier = Modifier) {
        Image(painter = painterResource(R.drawable.star), contentDescription = "")
    }
}