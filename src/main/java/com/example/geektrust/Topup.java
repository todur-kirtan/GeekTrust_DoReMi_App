package com.example.geektrust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Topup {
	
	private StartDate date;
	private RenewalReminder renewalReminder;
	private HashMap<String,Integer> deviceIndex = new HashMap<>();
	private int[] deviceData = new int[] {50,100};
	private boolean isDuplicateTopup = false;
	private Set<String> duplicateFinder;
	private ArrayList<String> subscriptionCategoryList = new ArrayList<>();
	private ArrayList<String> topupCategoryList = new ArrayList<>();
	private ArrayList<Integer> topupMonthsList = new ArrayList<>();
	private ArrayList<Integer> topupPriceList = new ArrayList<>();
	
	
	
	
	public Topup() {
		this.deviceIndex.put("FOUR_DEVICE", 0);
		this.deviceIndex.put("TEN_DEVICE", 1);
		this.duplicateFinder = new HashSet<>();
		
	}
	
	public void setTopupDate(StartDate date) {
		this.date = date;
	}
	
	public void setTopupRenewalReminder(RenewalReminder renewalReminder) {
		this.renewalReminder = renewalReminder;
		this.subscriptionCategoryList = this.renewalReminder.getCategoryList();
	}
	
	
	public void addTopup(String[] command) {
		String device = command[1];
		String months = command[2];
		
		this.isDuplicateTopup = this.duplicateFinder.add(device) ? false : true ;
		
		this.topupCategoryList.add(device);
		this.topupMonthsList.add(Integer.parseInt(months));
		this.topupPriceList.add(this.deviceData[this.deviceIndex.get(device)]);
			
	}
	
	public void printTopupMessage() {
		if(!this.date.isValidDate()) {
			System.out.println("ADD_TOPUP_FAILED INVALID_DATE");
		}else if(this.isDuplicateTopup) {
			System.out.println("ADD_TOPUP_FAILED DUPLICATE_TOPUP");
		}else if(this.subscriptionCategoryList.isEmpty()){
			System.out.println("ADD_TOPUP_FAILED SUBSCRIPTION_NOT_FOUND");
		}else {
//			System.out.println("TopupcategoryList: "+ this.topupCategoryList + " "  + "TOPMonth: "+ this.topupMonthsList 
//					+ "topPrice: "+ this.topupPriceList +" isDuplicateTopup: "+ this.isDuplicateTopup );
		}
	}

	public ArrayList<Integer> getTopupMonthsList() {
		return topupMonthsList;
	}

	public ArrayList<Integer> getTopupPriceList() {
		return topupPriceList;
	}
	
	public ArrayList<String> getTopupCategoryList() {
		return topupCategoryList;
	}
	
	
	
	
	
	
}
