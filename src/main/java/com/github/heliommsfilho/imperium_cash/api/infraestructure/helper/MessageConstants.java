package com.github.heliommsfilho.imperium_cash.api.infraestructure.helper;

public enum MessageConstants {
    
    TEST("E000001"),
    TEST_NON_EXISTENT_MESSAGE_KEY("E999999");

    private final String messageKey;
    
    public String getMessageKey() {
        return messageKey;
    }
    
    MessageConstants(String messageKey) {
        this.messageKey = messageKey;
    }
}
