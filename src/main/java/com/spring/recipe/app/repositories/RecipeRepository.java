package com.spring.recipe.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring.recipe.app.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}