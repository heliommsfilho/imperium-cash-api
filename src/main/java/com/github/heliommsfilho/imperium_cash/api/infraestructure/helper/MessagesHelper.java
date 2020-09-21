package com.github.heliommsfilho.imperium_cash.api.infraestructure.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MessagesHelper {
    
    private static MessageSource messageSource;

    @Autowired
    MessagesHelper(@Qualifier("messageSource") MessageSource ms) {
        messageSource = ms;
    }
    
    public static String getMessage(MessageConstants key, String... params) {
        String defaultMessage = String.format("No message defined for key '%s'", key.getMessageKey());
        params = Arrays.stream(params).map(s -> String.format("'%s'", s)).toArray(String[]::new);
        return messageSource.getMessage(key.getMessageKey(), params, defaultMessage, LocaleContextHolder.getLocale());
    }
}
