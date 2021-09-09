// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlin.math.pow

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("点击生成") }
    DesktopMaterialTheme {
        MaterialTheme {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text)
                    Spacer(Modifier.size(20.dp))
                    Button(onClick = {
                        text = getUniqueMark()
                        ClipBoard.setSysClipboardText(text)
                    }) {
                        Text("点击生成")
                    }
                }
            }
        }
    }
}

fun main() = application {
    val state = rememberWindowState()
    state.position = WindowPosition.Aligned(Alignment.Center)
    state.size = state.size.copy(300.dp, 200.dp)
    Window(title = "小工具", state = state, onCloseRequest = ::exitApplication) {
        App()
    }
}

// GetUniqueMark 获得一个独特的字符串，
fun getUniqueMark(): String {
    var seed = System.currentTimeMillis() //13毫秒
    val chars = mutableListOf<String>()
    for (i in '0'..'9') {
        chars.add(i.toString())
    }
    for (i in 'a'..'z') {
        chars.add(i.toString())
    }
    for (i in 'A'..'Z') {
        chars.add(i.toString())
    }
    val indexes = mutableListOf<Long>()
    var findNotZero = false
    for (i in 50 downTo 0) {
        val pow = chars.size.toDouble().pow(i)
        val ret = (seed / pow).toLong()
        if (!findNotZero && ret != 0L) {
            findNotZero = true
        }
        if (findNotZero) {
            seed -= (pow * ret).toLong()
            indexes.add(ret)
        }
    }
    var output = ""
    indexes.forEachIndexed { _, index ->
        output += chars[(index % chars.size).toInt()]
    }
    return output
}

