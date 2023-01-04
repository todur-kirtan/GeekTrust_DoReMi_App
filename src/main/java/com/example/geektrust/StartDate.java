package com.example.geektrust;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StartDate {
	
	
	private DateTimeFormatter format;
	private LocalDate originalDate;
	private boolean isValidDate = false;
	private static final int minusDays = 10;
	
public StartDate() {
		
		this.format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		this.originalDate  = LocalDate.now();
		this.isValidDate = false;
	}
	
	public void setDate(String date) {
		
		
		
			try {
				this.originalDate = LocalDate.parse(date,format);
				this.isValidDate = true;
			} catch (DateTimeParseException e) {
				// TODO Auto-generated catch block
				this.isValidDate = false;
			}
		
		 
	}
	
	
	public String getDate() {
		
		return this.isValidDate ? this.originalDate.format(format) : "INVALID_DATE";
		
		
	}

	public boolean isValidDate() {
		return isValidDate;
	}
	
	
	public void printDateMessage() {
		if(!this.isValidDate) {
			System.out.println("INVALID_DATE");
		}
	}
	
	public String getReminderDate(int months) {
		if(isValidDate) {
		LocalDate temp;
		String returnDate;
		
		temp = this.originalDate;
		
		this.originalDate = this.originalDate.plusMonths(months);
		this.originalDate = this.originalDate.minusDays(minusDays);
		
		returnDate = this.originalDate.format(format);
		
		this.originalDate = temp;
		
		return returnDate;
		}
		else {
			return "INVALID_DATE";
		}
	}
	

}
