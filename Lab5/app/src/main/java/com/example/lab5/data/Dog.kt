package com.example.lab5.data

import androidx.annotation.DrawableRes
import com.example.lab5.R

data class Dog(
    val name: String,
    val age: Int,
    @param:DrawableRes val imageRes: Int
)

object DogRepository {
    val dogs = listOf(
        Dog("Koda", 2, R.drawable.ic_dog_avatar),
        Dog("Lola", 16, R.drawable.ic_dog_avatar),
        Dog("Frankie", 2, R.drawable.ic_dog_avatar),
        Dog("Nox", 8, R.drawable.ic_dog_avatar),
        Dog("Faye", 8, R.drawable.ic_dog_avatar),
        Dog("Bella", 14, R.drawable.ic_dog_avatar),
        Dog("Moana", 2, R.drawable.ic_dog_avatar),
        Dog("Tzeitel", 7, R.drawable.ic_dog_avatar),
        Dog("Leroy", 4, R.drawable.ic_dog_avatar)
    )
}

