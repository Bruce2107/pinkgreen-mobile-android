package br.com.pinkgreen.mkt.ui.view.navigation

import androidx.navigation.NavController
import br.com.pinkgreen.mkt.R

import java.lang.ref.WeakReference

internal class MktNavigation(private val navController: WeakReference<NavController>) {
    fun navigateFromListToDetails() {
        navController.get()
            ?.navigate(R.id.action_list_to_product_details)
    }

    fun popBackStack() {
        navController.get()?.popBackStack()
    }
}