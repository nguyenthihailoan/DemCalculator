package com.lx.ltuddd.democaculator.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by DaiPhongPC on 7/11/2018.
 */

public class FontCache {
    private static HashMap<String, Typeface> sFonts = new HashMap<>();

    public static Typeface getTypefaceFont(String fontname, Context context) throws Exception {
        Typeface typeface = sFonts.get(fontname);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(),
                    Contants.FOLDER_FONT + fontname);
            sFonts.put(fontname, typeface);
            return typeface;
        }
        return null;
    }
}
