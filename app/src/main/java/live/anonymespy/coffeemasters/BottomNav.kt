package live.anonymespy.coffeemasters

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import live.anonymespy.coffeemasters.ui.theme.Alternative1
import live.anonymespy.coffeemasters.ui.theme.OnPrimary
import live.anonymespy.coffeemasters.ui.theme.Primary

@Preview
@Composable
private fun NavBarItem_preview() {
    NavBarItem(page = Routes.MenuPage, modifier = Modifier.padding(8.dp))
}

@Composable
fun NavBar(
    selectedRoute: String = Routes.MenuPage.route,
    onChange: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .background(Primary)
            .padding(12.dp)
    ) {
        for (page in Routes.pages) {
            NavBarItem(
                page,
                selected = selectedRoute == page.route,
                modifier = Modifier.clickable() {
                    onChange(page.route)
                }
            )
        }
    }
}

data class NavItem(val route: String, val title: String, val icon: ImageVector)

object Routes {
    val OffersPage = NavItem("offers", "Offers", Icons.Outlined.Star)
    val MenuPage = NavItem("menu", "Menu", Icons.Outlined.Home)
    val OrderPage = NavItem("order", "Order", Icons.Outlined.ShoppingCart)
    val InfoPage = NavItem("info", "Info", Icons.Outlined.Info)

    val pages = listOf<NavItem>(MenuPage, OffersPage, OrderPage, InfoPage)
}

@Composable
fun NavBarItem(
    page: NavItem,
    selected: Boolean = false,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 12.dp)
    ) {
        Image(
            imageVector = page.icon,
            contentDescription = page.title,
            colorFilter = ColorFilter.tint(
                if (selected) Alternative1 else OnPrimary
            ),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .size(24.dp)
        )
        Text(
            page.title,
            fontSize = 12.sp,
            color = if (selected) Alternative1 else OnPrimary
        )
    }
}