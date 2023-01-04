package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class StartDateTest {
	
	SwitchClass switchClass = new SwitchClass();
	StartDate date = new StartDate();

	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	// IMPORTANT: Save the old System.out!
	PrintStream old = System.out;
	
	
	@Test
	public void dateTest() {
		
		String setdate = "12-12-2022";
		StartDate date = new StartDate();
		date.setDate(setdate);
		String getDate = date.getDate();
		assertEquals(setdate,getDate);
		
	}
	
	@Test
	public void getReminderTest() {
		
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
		
		
		String expectedRenewalDate = "15-10-2021";
		String actualOutput = baos.toString().trim();
	
		String value = Arrays.stream(actualOutput.split("\\n")).filter(e -> e.contains("RENEWAL_REMINDER"))
				.collect(Collectors.toList()).get(0).split(" ")[2].trim();
	
		
		assertEquals(expectedRenewalDate, value);
	}
	
	@Test
	public void isValidDateTest() {
		StartDate date = new StartDate();
		String setDate = "25-07-2021";
		date.setDate(setDate);
		
		assertTrue(date.isValidDate());
		
	}
	
	@Test
	public void printDateMessageTest() {
		switchClass.command("START_SUBSCRIPTION 25-907-2021".split(" "));
//		switchClass.command("ADD_SUBSCRIPTION MUSIC PREMIUM".split(" "));
//		switchClass.command("ADD_SUBSCRIPTION VIDEO PREMIUM".split(" "));
//		switchClass.command("ADD_SUBSCRIPTION PODCAST PERSONAL".split(" "));
//		switchClass.command("ADD_TOPUP FOUR_DEVICE 2".split(" "));

		old = System.out;
		System.setOut(ps);
		switchClass.command("PRINT_RENEWAL_DETAILS".split(" "));
		System.out.flush();
		System.setOut(old);

		
		String expectedOutput = "INVALID_DATE";
		
		String actualOutput= baos.toString().trim();
		
		String value = Arrays.stream(actualOutput.split("\\n")).filter(e -> e.contains("INVALID_DATE"))
				.collect(Collectors.toList()).get(0).trim();
		
		
		assertEquals(expectedOutput, value);
		
	}
	
	
	
}