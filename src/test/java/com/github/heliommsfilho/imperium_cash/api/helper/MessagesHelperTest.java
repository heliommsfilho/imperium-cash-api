package com.github.heliommsfilho.imperium_cash.api.helper;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.is;

@Tag("Helpers")
@DisplayName("Message Helper should")
@SpringBootTest
class MessagesHelperTest {

    @Test
    @DisplayName("return the correct E000001 test message")
    void getE000001Property() {
        MatcherAssert.assertThat(MessagesHelper.getMessage(MessageConstants.ERROR_ENTITY_ALREADY_REGISTERED, "User", "test@email.com"),
                                 is("Entity 'User' already registered with identifier 'test@email.com'."));
    }

    @Test
    @DisplayName("return the default 'not defined' message")
    void getE999999Property() {
        MatcherAssert.assertThat(MessagesHelper.getMessage(MessageConstants.TEST_NON_EXISTENT_MESSAGE_KEY), is("No message defined for key 'E999999'"));
    }
}
