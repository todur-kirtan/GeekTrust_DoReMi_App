package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class RenewalReminderTest {
	

	SwitchClass switchClass = new SwitchClass();
	StartDate date = new StartDate();

	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	// IMPORTANT: Save the old System.out!
	PrintStream old = System.out;
	
	
	@Test
	public void printRenewalTest() {
		
		switchClass.command("START_SUBSCRIPTION 25-07-2021".split(" "));
		switchClass.command("ADD_SUBSCRIPTION MUSIC PREMIUM".split(" "));
//		switchClass.command("ADD_SUBSCRIPTION VIDEO PREMIUM".split(" "));
//		switchClass.command("ADD_SUBSCRIPTION PODCAST PERSONAL".split(" "));
//		switchClass.command("ADD_TOPUP FOUR_DEVICE 2".split(" "));

		
		old = System.out;
		System.setOut(ps);
		switchClass.command("PRINT_RENEWAL_DETAILS".split(" "));
		System.out.flush();
		System.setOut(old);
		String expectedOutput = "RENEWAL_REMINDER MUSIC 15-10-2021";
		String actualOutput = baos.toString().trim();
		
		String value = Arrays.stream(actualOutput.split("\\n")).filter(e -> e.contains("RENEWAL_REMINDER"))
				.collect(Collectors.toList()).get(0).trim();
		
		assertEquals(expectedOutput, value);
		
	}
	
	@Test
	public void printRenewalReminderMessageInvalidDateTest() {
		switchClass.command("START_SUBSCRIPTION 25-907-2021".split(" "));
		switchClass.command("ADD_SUBSCRIPTION MUSIC PREMIUM".split(" "));
//		switchClass.command("ADD_SUBSCRIPTION VIDEO PREMIUM".split(" "));
//		switchClass.command("ADD_SUBSCRIPTION PODCAST PERSONAL".split(" "));
//		switchClass.command("ADD_TOPUP FOUR_DEVICE 2".split(" "));

		
		old = System.out;
		System.setOut(ps);
		switchClass.command("PRINT_RENEWAL_DETAILS".split(" "));
		System.out.flush();
		System.setOut(old);
		
		String expected = "ADD_SUBSCRIPTION_FAILED INVALID_DATE";
		String actual = baos.toString().trim();
		
		String value = Arrays.stream(actual.split("\\n")).filter(e -> e.contains("ADD_SUBSCRIPTION_FAILED"))
				.collect(Collectors.toList()).get(0).trim();
		
		assertEquals(expected, value);
	}
	
	@Test
	public void printRenewalReminderMessageDuplicateCategoryTest() {
		switchClass.command("START_SUBSCRIPTION 25-07-2021".split(" "));
		switchClass.command("ADD_SUBSCRIPTION MUSIC PREMIUM".split(" "));
		switchClass.command("ADD_SUBSCRIPTION MUSIC PREMIUM".split(" "));
//		switchClass.command("ADD_SUBSCRIPTION PODCAST PERSONAL".split(" "));
//		switchClass.command("ADD_TOPUP FOUR_DEVICE 2".split(" "));

		
		old = System.out;
		System.setOut(ps);
		switchClass.command("PRINT_RENEWAL_DETAILS".split(" "));
		System.out.flush();
		System.setOut(old);
		String expected = "ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY";
		String actual = baos.toString().trim();
		
		String value = Arrays.stream(actual.split("\\n")).filter(e -> e.contains("ADD_SUBSCRIPTION_FAILED"))
				.collect(Collectors.toList()).get(0).trim();
		
		
		
		assertEquals(expected, value);
			
	}
	
	@Test
	public void printRenewalReminderMessageSubscriptionNotFoundTest() {
		switchClass.command("START_SUBSCRIPTION 25-07-2021".split(" "));
//		switchClass.command("ADD_SUBSCRIPTION MUSIC PREMIUM".split(" "));
//		switchClass.command("ADD_SUBSCRIPTION MUSIC PREMIUM".split(" "));
//		switchClass.command("ADD_SUBSCRIPTION PODCAST PERSONAL".split(" "));
//		switchClass.command("ADD_TOPUP FOUR_DEVICE 2".split(" "));

		
		old = System.out;
		System.setOut(ps);
		switchClass.command("PRINT_RENEWAL_DETAILS".split(" "));
		System.out.flush();
		System.setOut(old);
		
		String expected = "SUBSCRIPTION_NOT_FOUND";
		String actual = baos.toString().trim();
		
		String value = Arrays.stream(actual.split("\\n")).filter(e -> e.contains("SUBSCRIPTION_NOT_FOUND"))
				.collect(Collectors.toList()).get(0).trim();
		
		
		
		assertEquals(expected, value);
			
	}

}















