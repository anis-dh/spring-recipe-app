package com.spring.recipe.app.services;

import java.util.Set;
import com.spring.recipe.app.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

  Set<UnitOfMeasureCommand> listAllUoms();
}
