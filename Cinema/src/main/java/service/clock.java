package service;

import java.util.Calendar;

public class clock {
	public static String updateClock() {
	    Calendar cal = Calendar.getInstance();
	    int hour = cal.get(Calendar.HOUR_OF_DAY);
	    int minute = cal.get(Calendar.MINUTE);
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    int month = cal.get(Calendar.MONTH) + 1;
	    int year = cal.get(Calendar.YEAR);

	    String ampm;
	    if (hour >= 12) {
	        ampm = "PM";
	        if (hour > 12) {
	            hour -= 12;
	        }
	    } else {
	        ampm = "AM";
	        if (hour == 0) {
	            hour = 12;
	        }
	    }
	    return String.format("%02d:%02d %s  %02d/%02d/%04d", hour, minute, ampm, day, month, year);
	}
}
