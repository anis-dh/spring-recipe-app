package com.spring.recipe.app.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.spring.recipe.app.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Optional<Category> findByDescription(String description);

}