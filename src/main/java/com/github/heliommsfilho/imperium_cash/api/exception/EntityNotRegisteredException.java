package com.github.heliommsfilho.imperium_cash.api.exception;

import com.github.heliommsfilho.imperium_cash.api.helper.MessageConstants;
import com.github.heliommsfilho.imperium_cash.api.helper.MessagesHelper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityNotRegisteredException extends ApplicationDomainException {

    public EntityNotRegisteredException(String entity, String identifier) {
        super(MessagesHelper.getMessage(MessageConstants.ERROR_ENTITY_NOT_REGISTERED, entity, identifier));
    }
}
