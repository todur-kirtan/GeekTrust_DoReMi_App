package com.example.geektrust;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RenewalAmount {
	
	private StartDate date;
	private RenewalReminder renewalReminder;
	private Topup topup;
	private int count = 0;
	
	private int totalAmount = 0;

	private Set<String> duplicateFinder;
	
	private ArrayList<String> subscriptionCategoryList;
	private ArrayList<Integer> subscriptionPriceList;
	private ArrayList<Integer> subscriptionMonthsList;
	
	private ArrayList<String> topupCategoryList;
	private ArrayList<Integer> topupPriceList;
	private ArrayList<Integer> topupMonthsList;
	
	public RenewalAmount() {
		this.duplicateFinder = new HashSet<>();
		this.subscriptionCategoryList = new ArrayList<>();
		this.subscriptionMonthsList = new ArrayList<>();
		this.subscriptionPriceList = new ArrayList<>();
		
		this.topupCategoryList = new ArrayList<>();
		this.topupMonthsList = new ArrayList<>();
		this.topupPriceList = new ArrayList<>();
	
	}
	
	public void setRenewalAmountDate(StartDate date) {
		this.date = date;
	}
	
	public void setRenewalAmountReminder(RenewalReminder renewalReminder) {
		this.renewalReminder = renewalReminder;
		this.subscriptionCategoryList = this.renewalReminder.getCategoryList();
		this.subscriptionMonthsList = this.renewalReminder.getMonthsList();
		this.subscriptionPriceList = this.renewalReminder.getPriceList();
	}
	
	public void setRenewalAmountTopup(Topup topup) {
	
		this.topup = topup;
		this.topupCategoryList = this.topup.getTopupCategoryList();
		this.topupMonthsList = this.topup.getTopupMonthsList();
		this.topupPriceList = this.topup.getTopupPriceList();
	}
	
	public void printRenewalAmountMessage() {
		if(this.subscriptionCategoryList.isEmpty() || !this.date.isValidDate()) {
			System.out.println("SUBSCRIPTIONS_NOT_FOUND");
		}else {
			printSubscriptionRenewalAmount();
		}
	}
	
	
	
	
	
	public void printSubscriptionRenewalAmount() {
		
		
		while(this.count < this.subscriptionCategoryList.size() && this.duplicateFinder.add(this.subscriptionCategoryList.get(this.count))) {
			this.totalAmount += this.subscriptionPriceList.get(this.count);
			this.count++;
		}
		
		if(!this.topupCategoryList.isEmpty()) {
			printTopupRenewalamount();
		}
		
		
		System.out.println("RENEWAL_AMOUNT "+this.totalAmount);
	}
	
	
	public void printTopupRenewalamount() {
		this.count = 0;
		this.duplicateFinder.clear();
		
		while(this.count < this.topupCategoryList.size() && this.duplicateFinder.add(this.topupCategoryList.get(count))) {
			this.totalAmount += this.topupPriceList.get(count) * this.topupMonthsList.get(count);
			this.count++;
		}
		
	}
	
	
	
}















