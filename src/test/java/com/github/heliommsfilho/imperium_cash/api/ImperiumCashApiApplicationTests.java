package com.github.heliommsfilho.imperium_cash.api;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImperiumCashApiApplicationTests {

	@Test
	void contextLoads() {
		Assert.assertTrue(true);
	}

	@Test
	void main_ShouldStartup() {
		ImperiumCashApiApplication.main(new String[]{ "xyz" });
		Assert.assertTrue(true);
	}

	@Test
	void main_ShouldThrowIllegalArgumentException() {
		Assert.assertThrows(IllegalArgumentException.class, () -> ImperiumCashApiApplication.main(new String[]{"test"}));
	}
}
