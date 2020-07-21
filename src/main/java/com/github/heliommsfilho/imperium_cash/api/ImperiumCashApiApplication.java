package com.github.heliommsfilho.imperium_cash.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class ImperiumCashApiApplication {

	public static void main(String[] args) {
		validateStartupArguments(args);
		SpringApplication.run(ImperiumCashApiApplication.class, args);
	}

	private static void validateStartupArguments(String[] args) {
		Arrays.stream(args).filter(a -> !getValidArguments().contains(a))
								.findAny()
									.ifPresent(s -> { throw new IllegalArgumentException(); });
	}

	private static List<String> getValidArguments() {
		return Collections.emptyList();
	}
}
