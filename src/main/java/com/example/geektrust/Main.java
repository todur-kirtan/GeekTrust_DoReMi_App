package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	StartDate date = new StartDate();
    	SwitchClass switchClass = new SwitchClass();
        try {

            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); 

            while (sc.hasNextLine()) {
   
            	
            	String[] commandLine = sc.nextLine().split(" ");
            	
            	switchClass.command(commandLine);
            		
            	
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }
        
    }
}
