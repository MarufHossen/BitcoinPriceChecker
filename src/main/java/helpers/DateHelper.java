package helpers;

import java.time.LocalDate;

public class DateHelper {
    public static String getCurrentDate(){
        LocalDate todayDate = LocalDate.now();
        return todayDate.toString();
    }

    public static String getDate(int daysBefore){
        LocalDate todayDate = LocalDate.now();
        return todayDate.minusDays(daysBefore).toString();
    }
}
