package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class RenewalAmountTest {
	
	SwitchClass switchClass = new SwitchClass();
	StartDate date = new StartDate();
	RenewalReminder renewal = new RenewalReminder();
	Topup topup = new Topup();
	RenewalAmount renewalAmount = new RenewalAmount();
	
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	// IMPORTANT: Save the old System.out!
	PrintStream old;
	
	@Test
	public void RenewalAmountDataTest() {
		switchClass.command("START_SUBSCRIPTION 25-07-2021".split(" "));
		switchClass.command("ADD_SUBSCRIPTION MUSIC PREMIUM".split(" "));
		switchClass.command("ADD_SUBSCRIPTION VIDEO PREMIUM".split(" "));
		switchClass.command("ADD_SUBSCRIPTION PODCAST PERSONAL".split(" "));
		switchClass.command("ADD_TOPUP FOUR_DEVICE 2".split(" "));
		
		
		old = System.out;
		System.setOut(ps);
		switchClass.command("PRINT_RENEWAL_DETAILS".split(" "));
		System.out.flush();
		System.setOut(old);
		
		String expected = "RENEWAL_AMOUNT 950";
		String actual = baos.toString().trim();
		actual = actual.split("\\n")[actual.split("\\n").length-1];
	
		assertEquals(expected, actual);
			
	}
	
	@Test
	public void RenewalAmountSubscriptionNotFoundTest() {
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
		
		String expected = "SUBSCRIPTIONS_NOT_FOUND";
		String actual = baos.toString().trim();
		actual = actual.split("\\n")[actual.split("\\n").length-1];
	
		assertEquals(expected, actual);
			
	}

}
