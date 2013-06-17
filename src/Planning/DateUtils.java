/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Planning;

import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;

/**
 *
 * @author Audrey
 */
public class DateUtils {

    public DateUtils() {
    }
    
    /**
     * Reverse a string to a date
     * @param dateStr : dd/mm/yyyy
     * @return the date
     */
    public static Date stringToDate(String dateStr) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date d = formatter.parse(dateStr);
            return d;
        } catch (ParseException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Simplify a date in a dd/MM/yyyy string format
     */
    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
    
    public static String timeToString(Time time) {
        Format formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(time);
    }
    /*
     * Get a list of minutes or hours
     */
    private static String[] getTime(int nb) {
        String[] list = new String[nb];
        for(int i=0 ; i<nb ; i++){
            if(i<10)
                list[i]="0"+i;
            else list[i]=i+"";
        }
        return list;
    }
    
    public static String[] getHours() {
        return getTime(24);
    }
    
    public static String[] getMinutes() {
        return getTime(60);
    }
    
    /* Methodes pour récupérer indépendemment la date, le mois ou l'année d'un objet de type Date */
        	
    public static int getDate(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        return gc.get(Calendar.DATE);
    }

    public static int getMonth(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        return gc.get(Calendar.MONTH);
    }

    public static int getYear(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        return gc.get(Calendar.YEAR);
    }
}
