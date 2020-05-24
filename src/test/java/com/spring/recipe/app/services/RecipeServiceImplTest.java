package com.spring.recipe.app.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.spring.recipe.app.commands.RecipeCommand;
import com.spring.recipe.app.converters.RecipeCommandToRecipe;
import com.spring.recipe.app.converters.RecipeToRecipeCommand;
import com.spring.recipe.app.domain.Recipe;
import com.spring.recipe.app.exceptions.NotFoundException;
import com.spring.recipe.app.repositories.RecipeRepository;

public class RecipeServiceImplTest {

  RecipeServiceImpl recipeService;

  @Mock
  RecipeRepository recipeRepository;

  @Mock
  RecipeToRecipeCommand recipeToRecipeCommand;

  @Mock
  RecipeCommandToRecipe recipeCommandToRecipe;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    recipeService =
        new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
  }

  @Test
  public void getRecipeByIdTest() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    Recipe recipeReturned = recipeService.findById(1L);

    assertNotNull("Null recipe returned", recipeReturned);
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, never()).findAll();
  }

  @Test
  public void getRecipesTest() throws Exception {

    Recipe recipe = new Recipe();
    HashSet receipesData = new HashSet();
    receipesData.add(recipe);

    when(recipeService.getRecipes()).thenReturn(receipesData);

    Set<Recipe> recipes = recipeService.getRecipes();

    assertEquals(recipes.size(), 1);
    verify(recipeRepository, times(1)).findAll();
    verify(recipeRepository, never()).findById(anyLong());
  }

  @Test
  public void getRecipeCommandByIdTest() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    RecipeCommand recipeCommand = new RecipeCommand();
    recipeCommand.setId(1L);

    when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

    RecipeCommand commandById = recipeService.findCommandById(1L);

    assertNotNull("Null recipe returned", commandById);
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, never()).findAll();
  }

  @Test
  public void testDeleteById() throws Exception {

    // given
    Long idToDelete = Long.valueOf(2L);

    // when
    recipeService.deleteById(idToDelete);

    // no 'when', since method has void return type

    // then
    verify(recipeRepository, times(1)).deleteById(anyLong());
  }

  @Test(expected = NotFoundException.class)
  public void getRecipeByIdTestNotFound() throws Exception {

    Optional<Recipe> recipeOptional = Optional.empty();

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    Recipe recipeReturned = recipeService.findById(1L);

    // should go boom
  }

  @Test(expected = NumberFormatException.class)
  public void getRecipeById_throws_NumberFormatException_when_id_is_not_a_number()
      throws Exception {
    recipeService.findById(Long.valueOf("asd"));
  }

}
