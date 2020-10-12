package com.github.heliommsfilho.imperium_cash.api;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Tag("Configuration")
@DisplayName("Imperium Cash Application should")
@SpringBootTest
class ImperiumCashApiApplicationTests {

	@Test
	@DisplayName("load context correctly")
	void contextLoads() {
		Assert.assertTrue(true);
	}

	@Test
	@DisplayName("start application correctly")
	void startApplication() {
		ImperiumCashApiApplication.main(new String[]{ "xyz" });
		Assert.assertTrue(true);
	}

	@Test
	@DisplayName("throw 'IllegalArgumentException' exception when starting application")
	void throwExceptionWhenStartingApplication() {
		Assert.assertThrows(IllegalArgumentException.class, () -> ImperiumCashApiApplication.main(new String[]{"test"}));
	}
}
