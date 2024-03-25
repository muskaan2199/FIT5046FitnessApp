package com.example.elixir

import android.icu.text.CaseMap.Title
import androidx.annotation.DrawableRes

data class Item(
    val title: String,
    @DrawableRes val image: Int
)
