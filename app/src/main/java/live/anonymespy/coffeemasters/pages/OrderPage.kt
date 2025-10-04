package live.anonymespy.coffeemasters.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import live.anonymespy.coffeemasters.DataManager
import live.anonymespy.coffeemasters.ItemInCart
import live.anonymespy.coffeemasters.ui.theme.Alternative1
import live.anonymespy.coffeemasters.ui.theme.CardBackground
import live.anonymespy.coffeemasters.ui.theme.Primary
import live.anonymespy.coffeemasters.ui.theme.SurfaceBackground
import live.anonymespy.coffeemasters.ui.theme.Ternary

@Composable
fun OrderPage(modifier: Modifier = Modifier, dataManager: DataManager) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceBackground)
    ) {
        if (dataManager.cart.isEmpty()) {
            EmptyCartView()
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = "Your Order",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                items(dataManager.cart) { item ->
                    OrderItemCard(
                        item = item,
                        onRemove = { dataManager.cartRemove(item.product) }
                    )
                }
            }

            OrderSummaryCard(
                subtotal = dataManager.cart.sumOf { it.product.price * it.quantity },
                onCheckout = { /* Handle checkout */ }
            )
        }
    }
}

@Composable
private fun EmptyCartView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Your cart is empty",
            style = MaterialTheme.typography.headlineSmall,
            color = Primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Add some delicious coffee or tea to get started!",
            style = MaterialTheme.typography.bodyMedium,
            color = Primary.copy(alpha = 0.7f)
        )
    }
}

@Composable
private fun OrderItemCard(
    item: ItemInCart,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item.product.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Primary,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "$${item.product.price.format(2)} each",
                    style = MaterialTheme.typography.bodySmall,
                    color = Primary.copy(alpha = 0.6f)
                )
                Text(
                    text = "Quantity: ${item.quantity}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Ternary,
                    fontWeight = FontWeight.Medium
                )
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "$${(item.product.price * item.quantity).format(2)}",
                    style = MaterialTheme.typography.titleLarge,
                    color = Primary,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = onRemove,
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Ternary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove item"
                    )
                }
            }
        }
    }
}

@Composable
private fun OrderSummaryCard(
    subtotal: Double,
    onCheckout: () -> Unit
) {
    val tax = subtotal * 0.1
    val total = subtotal + tax

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Order Summary",
                style = MaterialTheme.typography.titleLarge,
                color = Primary,
                fontWeight = FontWeight.Bold
            )

            Divider(color = Primary.copy(alpha = 0.2f), thickness = 1.dp)

            SummaryRow(label = "Subtotal", amount = subtotal)
            SummaryRow(label = "Tax (10%)", amount = tax)

            Divider(color = Primary.copy(alpha = 0.2f), thickness = 1.dp)

            SummaryRow(
                label = "Total",
                amount = total,
                isBold = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onCheckout,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Alternative1,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Place Order",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun SummaryRow(
    label: String,
    amount: Double,
    isBold: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = Primary,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = "$${amount.format(2)}",
            style = MaterialTheme.typography.bodyLarge,
            color = Primary,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
    }
}
