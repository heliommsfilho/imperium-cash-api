package com.github.heliommsfilho.imperium_cash.api.infraestructure.helper;

import static org.hamcrest.Matchers.is;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MessagesHelperTest {

    @Test
    void getE000001Property_shouldReturnTestMessage() {
        MatcherAssert.assertThat(MessagesHelper.getMessage(MessageConstants.TEST, "A"), is("Test 'A'"));
    }

    @Test
    void getE999999Property_shouldReturnDefaultMessage() {
        MatcherAssert.assertThat(MessagesHelper.getMessage(MessageConstants.TEST_NON_EXISTENT_MESSAGE_KEY), is("No message defined for key 'E999999'"));
    }
}