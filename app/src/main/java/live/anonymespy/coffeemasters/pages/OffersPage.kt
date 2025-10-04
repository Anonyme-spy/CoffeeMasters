package live.anonymespy.coffeemasters.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import live.anonymespy.coffeemasters.R
import live.anonymespy.coffeemasters.ui.theme.Alternative1
import live.anonymespy.coffeemasters.ui.theme.Alternative2
import live.anonymespy.coffeemasters.ui.theme.CardBackground
import live.anonymespy.coffeemasters.ui.theme.Primary
import live.anonymespy.coffeemasters.ui.theme.Secondary
import live.anonymespy.coffeemasters.ui.theme.SurfaceBackground
import live.anonymespy.coffeemasters.ui.theme.Ternary

@Preview(showBackground = true)
@Composable
fun OffersPage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Special Offers",
            style = MaterialTheme.typography.headlineLarge,
            color = Primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OfferCard(
            title = "Early Bird Coffee",
            description = "Start your day right with 10% off all coffee drinks",
            validity = "Valid 6:00 AM - 9:00 AM",
            discount = "10% OFF",
            backgroundColor = Alternative1
        )

        OfferCard(
            title = "Welcome Gift",
            description = "New to Coffee Masters? Enjoy a special discount on your first order",
            validity = "First-time customers only",
            discount = "25% OFF",
            backgroundColor = Ternary
        )

        OfferCard(
            title = "Tea Time Special",
            description = "Discover our premium tea collection with an exclusive discount",
            validity = "All day, every day",
            discount = "15% OFF",
            backgroundColor = Alternative2
        )

        OfferCard(
            title = "Weekend Blend",
            description = "Make your weekend special with our signature blends",
            validity = "Saturday & Sunday",
            discount = "20% OFF",
            backgroundColor = Secondary
        )
    }
}

@Composable
fun OfferCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    validity: String,
    discount: String,
    backgroundColor: Color
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Box {
            Image(
                painter = painterResource(R.drawable.background_pattern),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                alpha = 0.1f
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineSmall,
                            color = Primary,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Primary.copy(alpha = 0.8f)
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = backgroundColor,
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = discount,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Divider(
                    color = Primary.copy(alpha = 0.2f),
                    thickness = 1.dp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⏱ ",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = validity,
                        style = MaterialTheme.typography.bodySmall,
                        color = Ternary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
