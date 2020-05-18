package com.spring.recipe.app.services;

import java.util.Set;

import com.spring.recipe.app.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();
}