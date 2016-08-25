package com.damenghai.chahuitong.utils;

import android.app.DatePickerDialog;
import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    /**
     * 显示日期选择器窗口
     *
     */
    public static void showDateDialog(Context context, String dateStr, DatePickerDialog.OnDateSetListener listener) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            Date date = format.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            DatePickerDialog dialog = new DatePickerDialog(context, listener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DATE));
            dialog.show();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
