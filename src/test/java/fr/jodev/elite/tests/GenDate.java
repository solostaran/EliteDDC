package fr.jodev.elite.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenDate {

	public static void main(String[] args) {
		System.out.println("["+new Date().getTime()+"]");
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(df.format(new Date()));
	}

}
