package com.anup.gamedomain.core;

import com.anup.gamedomain.api.GameRequest;
import com.anup.gamedomain.enums.ValidationStatus;
import com.anup.gamedomain.utils.StringUtils;

public class GameValidator {

    ValidationStatus validate(GameRequest gameRequest){
        if(!hasName(gameRequest)){
            return ValidationStatus.NO_NAME;
        }else if(StringUtils.isBlank(gameRequest.getDescription())){
            return ValidationStatus.NO_DESCRIPTION;
        }else if(gameRequest.getRating() == -1){
            return ValidationStatus.NO_RATING;
        }else if(hasNoContent(gameRequest.getPhoto())){
            return ValidationStatus.NO_PHOTO;
        }else{
            return ValidationStatus.OK;
        }
    }

    boolean hasName(GameRequest gameRequest){
        return StringUtils.isNotBlank(gameRequest.getName());
    }

    private boolean hasNoContent(byte[] data){
        return (data == null) || (data.length == 0);
    }

}
