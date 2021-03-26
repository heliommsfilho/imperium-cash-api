package com.github.heliommsfilho.imperium_cash.api.helper;

public enum MessageConstants {
    
    ERROR_ENTITY_ALREADY_REGISTERED("E000001"),
    ERROR_ENTITY_NOT_REGISTERED("E000002"),
    TEST_NON_EXISTENT_MESSAGE_KEY("E999999");

    private final String messageKey;
    
    public String getMessageKey() {
        return messageKey;
    }
    
    MessageConstants(String messageKey) {
        this.messageKey = messageKey;
    }
}
