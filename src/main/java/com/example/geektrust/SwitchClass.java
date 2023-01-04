package com.example.geektrust;

import java.util.Arrays;

public class SwitchClass {
	
	private StartDate date = new StartDate();
	private RenewalReminder renewalReminder = new RenewalReminder();
	private Topup topup = new Topup();
	private RenewalAmount renewalAmount = new RenewalAmount();
	
	public void command(String[] command) {
		
		switch (Arrays.stream(command).findFirst().get()) {
		
		case "START_SUBSCRIPTION":	date.setDate(command[1]);
		                            renewalReminder.setRenewalReminderDate(date);
		                            topup.setTopupDate(date);
		                            renewalAmount.setRenewalAmountDate(date);
									
			
			break;
			
		case "ADD_SUBSCRIPTION":	 renewalReminder.addSubscription(command);
									topup.setTopupRenewalReminder(renewalReminder);
									renewalAmount.setRenewalAmountReminder(renewalReminder);
								
								 
			break;
		case "ADD_TOPUP": 			topup.addTopup(command);
									renewalAmount.setRenewalAmountTopup(topup);
								
								
								
			break;
			
		case "PRINT_RENEWAL_DETAILS":  	date.printDateMessage();
										renewalReminder.printRenewalReminderMessage();
										topup.printTopupMessage();
										renewalReminder.printRenewal();
										renewalAmount.printRenewalAmountMessage();

								
			break;
		default:
			break;
		}
		
		
	}
	

}
