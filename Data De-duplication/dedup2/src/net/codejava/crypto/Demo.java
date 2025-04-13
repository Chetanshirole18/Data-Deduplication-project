package net.codejava.crypto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Demo {

	public static void main(String[] args) throws ParseException 
	{
		// TODO Auto-generated method stub
		    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String currTime=dateFormat.format(cal.getTime());
			java.sql.Time currentTime=null;
			currentTime= new java.sql.Time(dateFormat.parse(currTime).getTime());
		 	System.out.println(currentTime);
			
		 	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 	Date dateobj = new Date();
		 	String currDate=df.format(dateobj);
		 	java.sql.Date currentDate=null;
		 	currentDate= new java.sql.Date(df.parse(currDate).getTime());
		 	System.out.println(currentDate);
		 	
		 	//Substract two minutes from current time.
		 	cal = Calendar.getInstance();
		 	cal.add(Calendar.MINUTE, 2);
		 	String threeminutesAfter=dateFormat.format(cal.getTime());
		 	java.sql.Time threeMinutesAfterTime=null;
		 	threeMinutesAfterTime= new java.sql.Time(dateFormat.parse(threeminutesAfter).getTime());
		 	System.out.println(threeMinutesAfterTime);
		 	
		 	

	}

}
