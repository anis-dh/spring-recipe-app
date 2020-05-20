package com.spring.recipe.app.services;

import java.util.Set;
import com.spring.recipe.app.commands.RecipeCommand;
import com.spring.recipe.app.domain.Recipe;

public interface RecipeService {

  Set<Recipe> getRecipes();

  Recipe findById(Long l);

  RecipeCommand findCommandById(Long l);

  RecipeCommand saveRecipeCommand(RecipeCommand command);
}
