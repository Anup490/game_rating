package com.anup.gamecore.validation;

import com.anup.gamecore.dto.GameRequest;
import com.anup.gamecore.utils.StringUtils;

public class GameValidatorImpl implements GameValidator {

    public ValidationStatus validate(GameRequest gameRequest){
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

    public boolean hasName(GameRequest gameRequest){
        return StringUtils.isNotBlank(gameRequest.getName());
    }

    private boolean hasNoContent(byte[] data){
        return (data == null) || (data.length == 0);
    }

}
