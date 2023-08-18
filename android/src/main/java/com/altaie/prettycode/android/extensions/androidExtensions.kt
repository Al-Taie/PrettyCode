package com.altaie.prettycode.android.extensions

import android.view.Window
import android.view.WindowManager
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat


fun Window.fitSystemWindowsWithAdjustResize() {
    setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )

    WindowCompat.setDecorFitsSystemWindows(this, true)

    ViewCompat.setOnApplyWindowInsetsListener(decorView) { view, insets ->
        val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom

        WindowInsetsCompat
            .Builder()
            .setInsets(
                WindowInsetsCompat.Type.systemBars(),
                Insets.of(0, 0, 0, bottom)
            )
            .build()
            .apply { ViewCompat.onApplyWindowInsets(view, this) }
    }
}
