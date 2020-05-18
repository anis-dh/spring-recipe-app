package com.spring.recipe.app.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.spring.recipe.app.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

	Optional<UnitOfMeasure> findByDescription(String description);
}