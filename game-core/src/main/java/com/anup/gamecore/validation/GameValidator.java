package com.anup.gamecore.validation;

import com.anup.gamecore.dto.GameRequest;

public interface GameValidator {
    ValidationStatus validate(GameRequest gameRequest);
    boolean hasName(GameRequest gameRequest);
}
