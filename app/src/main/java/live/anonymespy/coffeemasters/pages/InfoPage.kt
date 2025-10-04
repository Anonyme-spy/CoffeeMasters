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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import live.anonymespy.coffeemasters.DataManager
import live.anonymespy.coffeemasters.ui.theme.Alternative1
import live.anonymespy.coffeemasters.ui.theme.CardBackground
import live.anonymespy.coffeemasters.ui.theme.Primary
import live.anonymespy.coffeemasters.ui.theme.SurfaceBackground
import live.anonymespy.coffeemasters.ui.theme.Ternary

@Composable
fun InfoPage(modifier: Modifier = Modifier, dataManager: DataManager) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "About Us",
            style = MaterialTheme.typography.headlineLarge,
            color = Primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        InfoCard(
            icon = Icons.Default.Home,
            title = "Our Coffee",
            description = "We source the finest coffee beans from sustainable farms around the world. Each cup is carefully crafted to bring you an exceptional experience with rich flavors and perfect balance."
        )

        InfoCard(
            icon = Icons.Default.Favorite,
            title = "Premium Tea Selection",
            description = "Discover our curated collection of premium teas, from classic black and green teas to exotic herbal blends. Every leaf is hand-selected for quality and taste."
        )

        InfoCard(
            icon = Icons.Default.Star,
            title = "Artisan Crafted",
            description = "Our baristas are trained in the art of coffee and tea preparation. We combine traditional methods with modern techniques to create the perfect beverage every time."
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Alternative1)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Visit Us",
                    style = MaterialTheme.typography.titleLarge,
                    color = CardBackground,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Open Daily: 7:00 AM - 8:00 PM\n\nFind your perfect brew at Coffee Masters",
                    style = MaterialTheme.typography.bodyMedium,
                    color = CardBackground
                )
            }
        }
    }
}

@Composable
private fun InfoCard(
    icon: ImageVector,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Ternary,
                modifier = Modifier.size(32.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Primary,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Primary.copy(alpha = 0.8f)
                )
            }
        }
    }
}
