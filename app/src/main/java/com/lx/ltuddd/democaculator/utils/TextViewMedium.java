package com.lx.ltuddd.democaculator.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by DaiPhongPC on 7/11/2018.
 */

public class TextViewMedium extends TextView {
    public TextViewMedium(Context context) {
        super(context);
    }

    public TextViewMedium(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewMedium(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        try {
            applyTypeface(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void applyTypeface(Context context) throws Exception {
        setTypeface(FontCache.getTypefaceFont(Contants.NAME_FONT, context));
    }
}
