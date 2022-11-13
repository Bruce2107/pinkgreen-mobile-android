package br.com.pinkgreen.mkt.ui.components.error

import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

internal data class ErrorLauncherParams(
    val fragmentActivity: WeakReference<Fragment>,
    val onDismissClickListener: () -> (Unit),
    val onPrimaryButtonClickListener: () -> (Unit),
)
