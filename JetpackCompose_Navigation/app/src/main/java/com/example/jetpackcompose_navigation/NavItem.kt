package com.example.jetpackcompose_navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class NavItem(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    navRoute: String
) {

    object Home: NavItem(R.string.home, R.drawable.home, NAV_HOME)
}