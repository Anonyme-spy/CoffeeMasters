package live.anonymespy.coffeemasters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import live.anonymespy.coffeemasters.ui.theme.CoffeeMastersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SafeContent()
        }
    }
}

@Composable
fun SafeView(myContent: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .padding(WindowInsets.systemBars.asPaddingValues())
            .fillMaxSize()
    ) {
        myContent()
    }
}

@Composable
fun SafeContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val dataManager = remember {
        DataManager(context.applicationContext as android.app.Application)
    }

    CoffeeMastersTheme {
        SafeView {
            App(dataManager = dataManager)
        }
    }
}
