package com.lx.ltuddd.democaculator.utils;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 7/11/2018.
 */

public class Utils {
    public static long stringToLong(String input) {
        try {
            long result = Long.parseLong(input);
            return result;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String convetArrToString(ArrayList<String> arrInput) {
        String result = "";
        for (String item : arrInput) {
            result += item;
        }
        return result;
    }
}
