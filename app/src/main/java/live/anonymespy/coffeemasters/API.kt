package live.anonymespy.coffeemasters

import android.content.Context
import java.util.Locale
import kotlin.random.Random

object API {

    fun fetchMenu(context: Context): MenuData {
        val drawableClass = R.drawable::class.java
        val fields = drawableClass.fields

        val products = mutableListOf<ProductData>()
        var id = 1

        fields.filter {
            // Exclude system drawables and keep only your product images
            !it.name.startsWith("ic_") &&
                    !it.name.startsWith("logo") &&
                    it.name != "background_pattern"
        }
            .forEach { field ->
                val resourceName = field.name
                    .replace("_", " ")
                    .split(" ")
                    .joinToString(" ") { it.capitalize(Locale.ROOT) }

                products.add(
                    ProductData(
                        id = id++,
                        name = resourceName,
                        price = Random.nextDouble(2.0, 6.0),
                        imageName = field.name
                    )
                )
            }

        return MenuData(
            categories = listOf(
                CategoryData("Coffee", products)
            )
        )
    }

}

data class ProductData(
    val id: Int,
    val name: String,
    val price: Double,
    val imageName: String
)

data class CategoryData(
    val name: String,
    val products: List<ProductData>
)

data class MenuData(
    val categories: List<CategoryData>
)
