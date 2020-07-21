package com.github.heliommsfilho.imperium_cash.api.helper;

import static org.hamcrest.Matchers.is;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessagesHelperTest {

    @Test
    public void getE000001Property_shouldReturnTestMessage() {
        MatcherAssert.assertThat(MessagesHelper.getMessage(MessageConstants.TEST, "A"), is("Test 'A'"));
    }

    @Test
    public void getE999999Property_shouldReturnDefaultMessage() {
        MatcherAssert.assertThat(MessagesHelper.getMessage(MessageConstants.TEST_NON_EXISTENT_MESSAGE_KEY), is("No message defined for key 'E999999'"));
    }
}