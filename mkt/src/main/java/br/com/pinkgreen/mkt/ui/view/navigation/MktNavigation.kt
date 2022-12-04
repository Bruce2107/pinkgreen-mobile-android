package br.com.pinkgreen.mkt.ui.view.navigation

import android.content.Context
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import br.com.pinkgreen.mkt.R

import java.lang.ref.WeakReference

internal class MktNavigation(private val navController: WeakReference<NavController>) {
    fun navigateFromListToDetails(id: Int) {
        navController.get()
            ?.navigate(R.id.action_list_to_product_details, bundleOf("id" to id))
    }

    fun navigateToHome(){
        navController.get()?.navigate(R.id.action_any_to_home)
    }

    fun navigateToCheckout() {
        navController.get()?.navigate(R.id.action_any_to_checkout)
    }

    fun navigateToFavourites(context: Context) {
        Toast.makeText(context, "Favoritos", Toast.LENGTH_SHORT).show()
    }

    fun navigateToSettings(context: Context) {
        Toast.makeText(context, "Configurações", Toast.LENGTH_SHORT).show()
    }

    fun popBackStack() {
        navController.get()?.popBackStack()
    }
}