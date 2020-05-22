package com.spring.recipe.app.services;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;
import com.spring.recipe.app.commands.UnitOfMeasureCommand;
import com.spring.recipe.app.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.spring.recipe.app.repositories.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

  private final UnitOfMeasureRepository unitOfMeasureRepository;
  private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

  public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
      UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
    this.unitOfMeasureRepository = unitOfMeasureRepository;
    this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
  }

  @Override
  public Set<UnitOfMeasureCommand> listAllUoms() {

    return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
        .map(unitOfMeasureToUnitOfMeasureCommand::convert).collect(Collectors.toSet());
  }
}
