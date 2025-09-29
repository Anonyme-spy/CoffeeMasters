package live.anonymespy.coffeemasters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun Offers(modifier: Modifier = Modifier) {
    val style = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = "My title",
            style = typography.headlineLarge,
            modifier = Modifier
                .background(Color.Cyan)
                .padding(18.dp)
        )
        Spacer(modifier = Modifier.padding(8.dp).height(8.dp))
        Text(
            text = "Description",
            style = typography.headlineSmall,
            modifier = Modifier
                .background(Color.Cyan)
                .padding(18.dp)

        )
    }
}