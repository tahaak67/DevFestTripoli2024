package ly.com.tahaben.devfest

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "DevFest Tripoli 2024",
    ) {
        App()
    }
}