package com.example.geektrust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RenewalReminder extends StartDate {
	
	private StartDate date;
	private int monthIndex = 0;
	private int priceIndex = 1;
	private HashMap<String, Integer> categoryIndex = new HashMap<String, Integer>();
	private HashMap<String, Integer> planIndex = new HashMap<String, Integer>();

	private Set<String> duplicateFinder = new HashSet<String>();
	private boolean isDuplicateCategory = false;

	private final int[][][] data = new int[][][] { { { 0, 1 }, { 100, 1 }, { 250, 3 } },
			{ { 0, 1 }, { 200, 1 }, { 500, 3 } }, { { 0, 1 }, { 100, 1 }, { 300, 3 } } };

	private ArrayList<String> categoryList = new ArrayList<>();

	private ArrayList<Integer> priceList = new ArrayList<>();

	private ArrayList<Integer> monthsList = new ArrayList<>();

	public RenewalReminder() {
		super();
		this.categoryIndex.put("MUSIC", 0);
		this.categoryIndex.put("VIDEO", 1);
		this.categoryIndex.put("PODCAST", 2);

		this.planIndex.put("FREE", 0);
		this.planIndex.put("PERSONAL", 1);
		this.planIndex.put("PREMIUM", 2);
	
	}
	
	

	public void setRenewalReminderDate(StartDate date) {
		this.date = date;
	}



	public void addSubscription(String[] command) {
		
		String category = command[1];
		String plan = command[2];
		

		this.isDuplicateCategory = duplicateFinder.add(category) ? false : true;

		int[] selectedSubscription = this.data[this.categoryIndex.get(category)][this.planIndex.get(plan)];
		
		this.categoryList.add(category);
		
		this.priceList.add(selectedSubscription[0]);
		this.monthsList.add(selectedSubscription[1]);
		
//		System.out.println("categoryList:"+ this.categoryList+ "priceList: "+this.priceList+" Monthslist: "+this.monthsList);
			
	}
	
	public void printRenewalReminderMessage() {
		if (!this.date.isValidDate()) {
			this.monthsList.forEach((value) -> System.out.println("ADD_SUBSCRIPTION_FAILED INVALID_DATE"));
		} else if (this.isDuplicateCategory) {
			System.out.println("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");
		} else if (this.categoryList.isEmpty()) {
			System.out.println("SUBSCRIPTION_NOT_FOUND");
		}else if(this.date.isValidDate() && !categoryList.isEmpty()){
//			printRenewal();
		}
		
	}
	
	public void printRenewal() {
		if(this.date.isValidDate() && !categoryList.isEmpty()) {
			printRenewalReminder();
		}

	}
	
	public void printRenewalReminder() {
		duplicateFinder.clear();

		
		int count = 0;
		while (count < categoryList.size() && duplicateFinder.add(categoryList.get(count))) {

			System.out.println("RENEWAL_REMINDER " + categoryList.get(count) + " "
					+ date.getReminderDate(monthsList.get(count)));
			count++;
		}
	}



	public ArrayList<String> getCategoryList() {
		return categoryList;
	}



	public ArrayList<Integer> getPriceList() {
		return priceList;
	}



	public ArrayList<Integer> getMonthsList() {
		return monthsList;
	}
	
	
	
	

	
	
}
