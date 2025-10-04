package live.anonymespy.coffeemasters.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import live.anonymespy.coffeemasters.DataManager
import live.anonymespy.coffeemasters.Product
import live.anonymespy.coffeemasters.R
import live.anonymespy.coffeemasters.ui.theme.Alternative1
import live.anonymespy.coffeemasters.ui.theme.CardBackground
import live.anonymespy.coffeemasters.ui.theme.Primary
import live.anonymespy.coffeemasters.ui.theme.SurfaceBackground
import live.anonymespy.coffeemasters.ui.theme.Ternary

@Composable
fun MenuPage(modifier: Modifier = Modifier, dataManager: DataManager) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceBackground)
    ) {
        if (dataManager.menu.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Loading menu...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Primary
                )
            }
            return
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Our Menu",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            dataManager.menu.forEach { category ->
                item {
                    Text(
                        text = category.name,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Ternary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                    )
                }

                items(category.products) { product ->
                    ProductItem(
                        product = product,
                        onAdd = { dataManager.cartAdd(it) }
                    )
                }
            }
        }
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)

@Composable
fun ProductItem(product: Product, onAdd: (Product) -> Unit) {
    val context = LocalContext.current
    val imageResId = context.resources.getIdentifier(
        product.image,
        "drawable",
        context.packageName
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            // Product Image
            Box(
                modifier = Modifier
                    .width(140.dp)
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(
                        id = if (imageResId != 0) imageResId else R.drawable.black_coffee
                    ),
                    contentDescription = "Image for ${product.name}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
                )
            }

            // Product Details
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = Primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$${product.price.format(2)}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Ternary,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Button(
                    onClick = { onAdd(product) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Alternative1,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Add to Cart",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
