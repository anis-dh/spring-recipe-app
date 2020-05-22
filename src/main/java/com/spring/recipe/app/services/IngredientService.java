package com.spring.recipe.app.services;

import com.spring.recipe.app.commands.IngredientCommand;

public interface IngredientService {

  IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

  IngredientCommand saveIngredientCommand(IngredientCommand command);

  void deleteById(Long recipeId, Long idToDelete);
}
