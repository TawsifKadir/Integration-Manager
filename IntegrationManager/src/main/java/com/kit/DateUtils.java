package com.kit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getCurrentDate(){

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date currentDate = new Date();
            return sdf.format(currentDate);
        }catch(Throwable t){

        }
        return "";
    }
}
