package com.zenika.zenilunch.mapper

import com.zenika.zenilunch.RestaurantUIModel
import com.zenika.zenilunch.network.RestaurantDto

fun RestaurantDto.convertRestaurantObject() = RestaurantUIModel(
    this.name,
    this.type,
    this.price,
    this.vegetarian,
    this.vegan,
    this.latitude,
    this.longitude
)
