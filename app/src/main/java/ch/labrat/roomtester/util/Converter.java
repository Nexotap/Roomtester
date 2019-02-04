package ch.labrat.roomtester.util;

import java.text.DateFormat;
import java.util.Date;

import androidx.databinding.InverseMethod;
import ch.labrat.roomtester.MyApplication;

/**
 * Converter class for databindings
 */
public class Converter {
    @InverseMethod("dateToString")
    public static Date stringToDate(String dateString) {

        DateFormat dateFormat = MyApplication.getDateFormat();

        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public static String dateToString(Date date) {
        String formattedDate;
        if (date != null) {
            DateFormat dateFormat = MyApplication.getDateFormat();
            formattedDate = dateFormat.format(date);
        }
        else {
            formattedDate = "Please enter Date";
        }
        return formattedDate;
    }
}
