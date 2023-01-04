package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class TopupTest {
	SwitchClass switchClass = new SwitchClass();
	StartDate date = new StartDate();

	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	// IMPORTANT: Save the old System.out!
	PrintStream old = System.out;

	@Test
	public void printTopupMessageTestInvalidDateTest() {
		
		switchClass.command("START_SUBSCRIPTION 25-907-2021".split(" "));
		switchClass.command("ADD_SUBSCRIPTION MUSIC PREMIUM".split(" "));
		switchClass.command("ADD_SUBSCRIPTION VIDEO PREMIUM".split(" "));
		switchClass.command("ADD_SUBSCRIPTION PODCAST PERSONAL".split(" "));
		switchClass.command("ADD_TOPUP FOUR_DEVICE 2".split(" "));

		
		old = System.out;
		System.setOut(ps);
		switchClass.command("PRINT_RENEWAL_DETAILS".split(" "));
		System.out.flush();
		System.setOut(old);

		String expected = "ADD_TOPUP_FAILED INVALID_DATE";
		String actual = baos.toString().trim();
		
		String value = Arrays.stream(actual.split("\\n")).filter(e -> e.contains("ADD_TOPUP_FAILED"))
						.collect(Collectors.toList()).get(0).trim();
		
		
		assertEquals(expected, value);
	}

	@Test
	public void printTopupMessageTestDuplicateTopupTest() {
		
		switchClass.command("START_SUBSCRIPTION 25-07-2021".split(" "));
		switchClass.command("ADD_SUBSCRIPTION MUSIC PREMIUM".split(" "));
		switchClass.command("ADD_SUBSCRIPTION VIDEO PREMIUM".split(" "));
		switchClass.command("ADD_SUBSCRIPTION PODCAST PERSONAL".split(" "));
		switchClass.command("ADD_TOPUP FOUR_DEVICE 2".split(" "));
		switchClass.command("ADD_TOPUP FOUR_DEVICE 2".split(" "));

		old = System.out;
		System.setOut(ps);
		switchClass.command("PRINT_RENEWAL_DETAILS".split(" "));
		System.out.flush();
		System.setOut(old);

		String expected = "ADD_TOPUP_FAILED DUPLICATE_TOPUP";
		String actual = baos.toString().trim();
	
		
		String value = Arrays.stream(actual.split("\\n")).filter(e -> e.contains("ADD_TOPUP_FAILED"))
				.collect(Collectors.toList()).get(0).trim();

		assertEquals(expected, value);

	}

	@Test
	public void printTopupMessageSubscriptionNotFoundTest() {
		
		switchClass.command("START_SUBSCRIPTION 25-07-2021".split(" "));
//		switchClass.command("ADD_SUBSCRIPTION MUSIC PREMIUM".split(" "));
//		switchClass.command("ADD_SUBSCRIPTION VIDEO PREMIUM".split(" "));
//		switchClass.command("ADD_SUBSCRIPTION PODCAST PERSONAL".split(" "));
		switchClass.command("ADD_TOPUP FOUR_DEVICE 2".split(" "));
		

		
		old = System.out;
		System.setOut(ps);
		switchClass.command("PRINT_RENEWAL_DETAILS".split(" "));
		System.out.flush();
		System.setOut(old);

		String expected = "ADD_TOPUP_FAILED SUBSCRIPTION_NOT_FOUND";
		String actual = baos.toString().trim();
		
		String value = Arrays.stream(actual.split("\\n")).filter(e -> e.contains("ADD_TOPUP_FAILED"))
				.collect(Collectors.toList()).get(0).trim();
		
		
		assertEquals(expected, value);

	}

}
