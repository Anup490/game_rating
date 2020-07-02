package com.anup.gamecore.core;

import com.anup.gamecore.dto.GameRequest;
import com.anup.gamecore.dto.GameResponse;
import com.anup.gamecore.persistance.GamePersistor;
import com.anup.gamecore.validation.GameValidator;
import com.anup.gamecore.validation.ValidationStatus;

public class GameCoreImpl implements GameCore {

    private GamePersistor persistor;
    private GameValidator validator;

    public GameCoreImpl(GamePersistor persistor, GameValidator validator){
        this.persistor = persistor;
        this.validator = validator;
    }

    public GameResponse insertGame(GameRequest gameRequest){
        ValidationStatus status = validator.validate(gameRequest);
        if(status != ValidationStatus.OK){
            return toResponse(status,null);
        }
        persistor.persistGame(gameRequest);
        return toResponse(status,null);
    }

    public GameResponse fetchGameByName(GameRequest gameRequest){
        if(validator.hasName(gameRequest)){
            Object entity = persistor.fetchGameByName(gameRequest.getName());
            return toResponse(ValidationStatus.OK, entity);
        }
        return toResponse(ValidationStatus.NO_NAME,null);
    }

    public GameResponse updateGameByName(GameRequest gameRequest){
        ValidationStatus status = validator.validate(gameRequest);
        if(status != ValidationStatus.OK){
            return toResponse(status,null);
        }
        persistor.updateGameByName(gameRequest);
        return toResponse(ValidationStatus.OK, null);
    }

    private GameResponse toResponse(ValidationStatus status, Object data){
        return new GameResponse(status.getCode(),status.getMessage(),data);
    }

}
