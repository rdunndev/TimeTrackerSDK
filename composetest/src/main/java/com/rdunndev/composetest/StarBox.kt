package com.rdunndev.composetest

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun StartBox(size: Dp) {
    Box(modifier = Modifier.height(size)
        .width(size)
    ) {
        Image(painter = painterResource(R.drawable.star), contentDescription = "")
    }
}