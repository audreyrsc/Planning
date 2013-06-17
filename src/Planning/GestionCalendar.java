
package Planning;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * Gestion du calendrier pour un mois
 * @author Audrey
 */
public class GestionCalendar{
    
        private static int todayDate, todayMonth, todayYear; // Data for today
    
    private int currentDate, currentMonth, currentYear; // Data for the current month displaying
    
    private int lastMonth, lastYear, nextMonth, nextYear; // Data for the last and the next months
    private String todayStr, currentMonthStr;

    private Date today, currentDay;
    private ArrayList<Date> matrice;
    
    public GestionCalendar() {

        initToday();
        // Le mois d'avant //
        initLastMonth();
        // Le mois d'après //
        initNextMonth();
        
        initMatrice();
    }
       
    private void initToday() {
        GregorianCalendar gCalendar = new GregorianCalendar();

        todayDate = gCalendar.get(Calendar.DATE);
        todayMonth = gCalendar.get(Calendar.MONTH);
        todayYear = gCalendar.get(Calendar.YEAR);
        
        today = new GregorianCalendar(todayYear,todayMonth,todayDate).getTime();
        
        // Init current values with today's values //
        currentDay = today;
        currentDate = todayDate;
        currentMonth = todayMonth;
        currentYear = todayYear;
        
        ///////////////////////////

        todayStr = todayDate+" "+gCalendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.FRENCH)
                .replace(".", "") +" "+todayYear;
        currentMonthStr = gCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRENCH).toUpperCase();
    }
    
    /** 
     * Initialize datas for the last month 
     */
    private void initLastMonth() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(currentYear, currentMonth-1, 1);
        lastMonth = cal.get(Calendar.MONTH);
        lastYear = cal.get(Calendar.YEAR);
    }
    
    /**
     * Initialize datas for the next month
     */
    private void initNextMonth() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(currentYear, currentMonth+1,1);
        nextMonth = cal.get(Calendar.MONTH);
        nextYear = cal.get(Calendar.YEAR);
    }
    
    /**
     * Initialize a matrice of Day
     * @param currentMonth
     * @param currentYear 
     */
    private void initMatrice() {
        matrice = new ArrayList<Date>(42);
        
        int nbDaysOfLastMonth = getNbDaysOfMonth(lastMonth);    
        int firstDay = getFirstDayOfMonth(currentMonth, currentYear);
        int nbDaysOfCurrentMonth = getNbDaysOfMonth(currentMonth);
        
        // On initialise le mois precedent //
        int date, lastCellOfLastMonth;
        if(firstDay==1) {
            date=6;
            lastCellOfLastMonth=7;
        }
        else {
            date=firstDay-2;
            lastCellOfLastMonth = firstDay-1;
        }
            
        int x;
        for(x=0 ; x<lastCellOfLastMonth ; x++)   {
            matrice.add(new GregorianCalendar(lastYear,lastMonth,nbDaysOfLastMonth-date).getTime());
            date--;
        }

        date=1;
        int month = currentMonth;
        int year = currentYear;
        
        for(int i=x ; i<42 ; i++) {
            matrice.add(new GregorianCalendar(year,month,date).getTime());
            date++;
            
            if(date>nbDaysOfCurrentMonth) {
                month = nextMonth;
                year = nextYear;
                date = 1;
            }
        }
        
    }

    /**
     * Init the first day of the current month (Monday=1 - Sunday=7)
     * @param currentDate
     * @param currentMonth
     * @param currentYear 
     */
    private int getFirstDayOfMonth(int currentMonth, int currentYear) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(currentYear, currentMonth, 1);
        int firstDay = cal.get(Calendar.DAY_OF_WEEK)-1;
        if(firstDay==0)
            firstDay=7;
        return firstDay;
//        System.out.println("First day: "+firstDay);
    }
    
    /**
     * Change values for the last month
     */
    public void goToLastMonth() {
        currentDate = 1;
        currentMonth = lastMonth;
        currentYear = lastYear;
        
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.set(currentYear, currentMonth, currentDate);
        currentDay = gCalendar.getTime();
        
        currentMonthStr = gCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRENCH).toUpperCase();
        
        initLastMonth();
        initNextMonth();
        initMatrice();
    }
    
    /**
     * Change values for the next month
     */
    public void goToNextMonth() {
        currentDate = 1;
        currentMonth = nextMonth;
        currentYear = nextYear;
        
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.set(currentYear, currentMonth, currentDate);
        currentDay = gCalendar.getTime();
        
        currentMonthStr = gCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRENCH).toUpperCase();
        
        initLastMonth();
        initNextMonth();
        initMatrice();
    }
    
    /**
     * Return the number of days for the month in parameter
     * @return number of days
     */
    public int getNbDaysOfMonth(int month) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.MONTH, month+1);
        cal.add(Calendar.DATE, -1);
        if(month==Calendar.FEBRUARY) {
            if(cal.isLeapYear(currentYear))
                return 29;
            else return 28;
        }  
        else return cal.get(Calendar.DATE);
    }
    
    ////////////////////////////////////////////////////////////////

    public ArrayList<Date> getMatrice() {
        return matrice;
    }
    
    public Date getToday() {
        return today;
    }
    
    public int getCurrentDate() {
        return currentDate;
    }
    
    public int getCurrentMonth() {
        return currentMonth;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public String getTodayStr() {
        return todayStr;
    }

    /**
     * Return the date of today in JJ/MM/AAAA format
     * @return String
     */
    public String getTodayFormat() {
        return todayDate+"/"+(todayMonth+1)+"/"+todayYear;
    }
    
    public String getCurrentMonthStr() {
        return currentMonthStr;
    }
      
    /**
     * Affiche la matrice
     */
    public void matriceToString() {
        for(int i=0 ; i<42 ; i++) {
                System.out.println(matrice.get(i));
            System.out.println();
        }
    }
    
//    private static int todayDate, todayMonth, todayYear; // Data for today
//    private int currentDate, currentMonth, currentYear; // Data for the current month displaying
//    
//    private int lastMonth, lastYear, nextMonth, nextYear; // Data for the last and the next months
//    private String todayStr, currentMonthStr;
//    
//    private Day[][] matrice;
//    
//    public GestionCalendar() {
//
//        initToday();
//        // Le mois d'avant //
//        initLastMonth();
//        // Le mois d'après //
//        initNextMonth();
//        
//        initMatrice();
//    }
//       
//    private void initToday() {
//        GregorianCalendar gCalendar = new GregorianCalendar();
//        todayDate = gCalendar.get(Calendar.DATE);
//        todayMonth = gCalendar.get(Calendar.MONTH);
//        todayYear = gCalendar.get(Calendar.YEAR);
// 
//        // Init current values with today's values //
//        currentDate = todayDate;
//        currentMonth = todayMonth;
//        currentYear = todayYear;
//        ///////////////////////////
//
//        todayStr = todayDate+" "+gCalendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.FRENCH)
//                .replace(".", "") +" "+todayYear;
//        currentMonthStr = gCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRENCH).toUpperCase();
//    }
//    
//    /** 
//     * Initialize datas for the last month 
//     */
//    private void initLastMonth() {
//        GregorianCalendar cal = new GregorianCalendar();
//        cal.set(currentYear, currentMonth-1, 1);
//        lastMonth = cal.get(Calendar.MONTH);
//        lastYear = cal.get(Calendar.YEAR);
//    }
//    
//    /**
//     * Initialize datas for the next month
//     */
//    private void initNextMonth() {
//        GregorianCalendar cal = new GregorianCalendar();
//        cal.set(currentYear, currentMonth+1,1);
//        nextMonth = cal.get(Calendar.MONTH);
//        nextYear = cal.get(Calendar.YEAR);
//    }
//    
//    /**
//     * Initialize a matrice of Day
//     * @param currentMonth
//     * @param currentYear 
//     */
//    private void initMatrice() {
//        matrice = new Day[6][7];
//        int nbDaysOfLastMonth = getNbDaysOfMonth(lastMonth);
//        int firstDay = getFirstDayOfMonth(currentMonth, currentYear);
//        int nbDaysOfCurrentMonth = getNbDaysOfMonth(currentMonth);
//        
//        // On initialise le mois precedent //
//        int cpt, lastCellOfLastMonth;
//        if(firstDay==1) {
//            cpt=6;
//            lastCellOfLastMonth=7;
//        }
//        else {
//            cpt=firstDay-2;
//            lastCellOfLastMonth = firstDay-1;
//        }
//            
//        int x;
//        for(x=0 ; x<lastCellOfLastMonth ; x++)   {
//            matrice[0][x] = new Day(nbDaysOfLastMonth-cpt, lastMonth, lastYear);
//            cpt--;
//        }
//
//        boolean firstLine=true;
//        cpt=1;
//        int month = currentMonth;
//        int year = currentYear;
//        
//        for(int i=0; i<6 ; i++) {
//            for(int j=0 ; j<7 ; j++) {
//                if(firstLine) {
//                    j=x;
//                    x++;
//                    if(firstDay==1)
//                        continue;
//                }
//                matrice[i][j] = new Day(cpt, month, year);
//                if(getTodayFormat().equals(matrice[i][j].getFullDate()))
//                    matrice[i][j].setIsToday(true);
//                cpt++;
//                if(cpt>nbDaysOfCurrentMonth) {
//                    month = nextMonth;
//                    year = nextYear;
//                    cpt = 1;
//                }
//            }
//            firstLine = false;
//        }   
//    }
//
//    /**
//     * Init the first day of the current month (Monday=1 - Sunday=7)
//     * @param currentDate
//     * @param currentMonth
//     * @param currentYear 
//     */
//    private int getFirstDayOfMonth(int currentMonth, int currentYear) {
//        GregorianCalendar cal = new GregorianCalendar();
//        cal.set(currentYear, currentMonth, 1);
//        int firstDay = cal.get(Calendar.DAY_OF_WEEK)-1;
//        if(firstDay==0)
//            firstDay=7;
//        return firstDay;
////        System.out.println("First day: "+firstDay);
//    }
//    
//    /**
//     * Change values for the last month
//     */
//    public void goToLastMonth() {
//        currentDate = 1;
//        currentMonth = lastMonth;
//        currentYear = lastYear;
//        
//        GregorianCalendar gCalendar = new GregorianCalendar();
//        gCalendar.set(currentYear, currentMonth, currentDate);
//
//        currentMonthStr = gCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRENCH).toUpperCase();
//        
//        initLastMonth();
//        initNextMonth();
//        initMatrice();
//    }
//    
//    /**
//     * Change values for the next month
//     */
//    public void goToNextMonth() {
//        currentDate = 1;
//        currentMonth = nextMonth;
//        currentYear = nextYear;
//        
//        GregorianCalendar gCalendar = new GregorianCalendar();
//        gCalendar.set(currentYear, currentMonth, currentDate);
//        
//        currentMonthStr = gCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRENCH).toUpperCase();
//        
//        initLastMonth();
//        initNextMonth();
//        initMatrice();
//    }
//    
//    /**
//     * Return the number of days for the month in parameter
//     * @return number of days
//     */
//    public int getNbDaysOfMonth(int month) {
//        GregorianCalendar cal = new GregorianCalendar();
//        cal.set(Calendar.DATE, 1);
//        cal.set(Calendar.MONTH, month+1);
//        cal.add(Calendar.DATE, -1);
//        if(month==Calendar.FEBRUARY && cal.isLeapYear(currentYear))
//            return 29;
//        else return cal.get(Calendar.DATE);
//    }
//    
//    ////////////////////////////////////////////////////////////////
//
//    public Day[][] getMatrice() {
//        return matrice;
//    }
//
//    public int getCurrentDate() {
//        return currentDate;
//    }
//    
//    public int getCurrentMonth() {
//        return currentMonth;
//    }
//
//    public int getCurrentYear() {
//        return currentYear;
//    }
//
//    public String getTodayStr() {
//        return todayStr;
//    }
//
//    /**
//     * Return the date of today in JJ/MM/AAAA format
//     * @return String
//     */
//    public String getTodayFormat() {
//        return todayDate+"/"+(todayMonth+1)+"/"+todayYear;
//    }
//    
//    public String getCurrentMonthStr() {
//        return currentMonthStr;
//    }
//      
//    /**
//     * Affiche la matrice
//     */
//    public void matriceToString() {
//        for(int i=0 ; i<6 ; i++) {
//            for(int j=0 ; j<7 ; j++)
//                System.out.print(matrice[i][j].getFullDate()+" ");
//            System.out.println();
//        }
//    }
}
