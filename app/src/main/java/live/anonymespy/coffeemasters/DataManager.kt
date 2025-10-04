package live.anonymespy.coffeemasters

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel

class DataManager(app: Application) : AndroidViewModel(app) {
    var menu = mutableStateListOf<Category>()
    var cart = mutableStateListOf<ItemInCart>()

    init {
        fetchMenu()
    }

    fun cartAdd(product: Product) {
        val item = cart.find { it.product.id == product.id }
        if (item != null) {
            item.quantity += 1
        } else {
            cart.add(ItemInCart(product, 1))
        }
    }

    fun cartRemove(product: Product) {
        val item = cart.find { it.product.id == product.id }
        if (item != null) {
            item.quantity -= 1
            if (item.quantity <= 0) {
                cart.remove(item)
            }
        }
    }

    fun cartClear() {
        cart.clear()
    }

    fun fetchMenu() {
        val menuData = API.fetchMenu(getApplication())

        menu.clear()
        val categories = menuData.categories.map { category ->
            Category(
                name = category.name,
                products = category.products.map { product ->
                    Product(
                        id = product.id,
                        name = product.name,
                        price = product.price,
                        image = product.imageName
                    )
                }.toMutableList()
            )
        }
        menu.addAll(categories)
    }

}
