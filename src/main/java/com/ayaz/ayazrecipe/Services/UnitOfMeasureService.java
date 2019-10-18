package com.ayaz.ayazrecipe.Services;

import com.ayaz.ayazrecipe.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
