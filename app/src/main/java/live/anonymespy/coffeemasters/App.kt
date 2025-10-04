package live.anonymespy.coffeemasters

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import live.anonymespy.coffeemasters.pages.InfoPage
import live.anonymespy.coffeemasters.pages.MenuPage
import live.anonymespy.coffeemasters.pages.OffersPage
import live.anonymespy.coffeemasters.pages.OrderPage


//@Preview
//@Composable
//fun App_Preview() {
//    CoffeeMastersTheme {
//        App(
//        )
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier, dataManager: DataManager) {
    val activeRoute = remember {
        mutableStateOf(Routes.MenuPage.route)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AppTitle()
                })
        },
        content = {
            Box(modifier.padding(it)) {
                when (activeRoute.value) {
                    Routes.MenuPage.route -> MenuPage(dataManager = dataManager)
                    Routes.OffersPage.route -> OffersPage()
                    Routes.OrderPage.route -> OrderPage(dataManager = dataManager)
                    Routes.InfoPage.route -> InfoPage(dataManager = dataManager)
                }
            }
        },
        bottomBar = {
            NavBar(
                selectedRoute = activeRoute.value,
                onChange = { majRoute ->
                    activeRoute.value = majRoute
                })
        }
    )

}

@Composable
fun AppTitle(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "coffee master logo"
            )
        }
    }
}