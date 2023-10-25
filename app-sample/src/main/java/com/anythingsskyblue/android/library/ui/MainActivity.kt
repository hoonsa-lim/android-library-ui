package com.anythingsskyblue.android.library.ui

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.anythingsskyblue.android.library.ui.compose.PreventContinuousClick
import com.anythingsskyblue.android.library.ui.compose.clickableNotContinuous
import com.anythingsskyblue.android.library.ui.compose.get
import com.anythingsskyblue.android.library.ui.ui.theme.AndroidlibraryuiTheme
import com.anythingsskyblue.android.library.ui.util.ContextUtil
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidlibraryuiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Button(onClick = {
                            ContextUtil.openUri(this@MainActivity, Uri.parse("https://www.google.com"))
                        }) {
                            Text(text = "open google")
                        }

                        var count by remember{ mutableStateOf(0) }
                        Text(
                            modifier = Modifier.clickableNotContinuous { count += 1 },
                            text = "prevent continuous click ($count)"
                        )

                        var count1 by remember{ mutableStateOf(0) }
                        val preventContinuousClick = remember { PreventContinuousClick.get(500.milliseconds) }
                        Button(onClick = {
                            preventContinuousClick.preventOrClick { count1 += 1 }
                        }) {
                            Text(text = "prevent continuous click ($count1)")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidlibraryuiTheme {

    }
}