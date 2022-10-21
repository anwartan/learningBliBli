package com.example.learningblibli.domain.usecase

import com.example.learningblibli.data.repository.MealRepository
import com.example.learningblibli.domain.model.Meal
import kotlinx.coroutines.flow.Flow

class GetFavoriteMealUsecase(private val mealRepository: MealRepository) {
    operator fun invoke(): Flow<List<Meal>> = mealRepository.getFavoriteMeals()

}