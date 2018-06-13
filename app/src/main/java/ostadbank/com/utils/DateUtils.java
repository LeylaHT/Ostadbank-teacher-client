package ostadbank.com.utils;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ostadbank.com.R;
import ostadbank.com.classes.tools.calendar.CalendarTool;


/**
 * Created by Amir on 4/4/2017.
 */

public class DateUtils {

    public static String convertDateToString(long date, Context con) {

        try {
            String formatedDate = "";

            SimpleDateFormat formatter;

            Calendar entryDate = Calendar.getInstance();
            entryDate.setTimeInMillis(date);

            Calendar GregorianCalNow = Calendar.getInstance();
            CalendarTool JulianCal = new CalendarTool(entryDate.get(Calendar.YEAR), entryDate.get(Calendar.MONTH) + 1, entryDate.get(Calendar.DAY_OF_MONTH));
            CalendarTool JulianCalNow = new CalendarTool(GregorianCalNow.get(Calendar.YEAR), GregorianCalNow.get(Calendar.MONTH) + 1, GregorianCalNow.get(Calendar.DAY_OF_MONTH));

            if (JulianCalNow.getIranianYear() > JulianCal.getIranianYear()) {
                formatedDate = JulianCal.getIranianDateYMD();
            } else {
                long subDay = (GregorianCalNow.getTimeInMillis() - entryDate.getTimeInMillis()) / 86400000;
                if (JulianCalNow.getIranianMonth() > JulianCal.getIranianMonth() && subDay > 7) {
                    try {
                        formatedDate = JulianCal.getIranianDateMD();
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Logger.e(e);
                    }
                } else {

                    if (subDay > 6) {
                        formatedDate = JulianCal.getIranianDateMD();// + " " + String.format("%02d", entryDate.get(Calendar.HOUR_OF_DAY)) + ":" + String.format("%02d", entryDate.get(Calendar.MINUTE));
                    } else {
                        if (subDay != 0)
                            formatedDate = String.valueOf(subDay) + " " + con.getResources().getString(R.string.DayAgo_Title) + " ";
                        else {
                            long HourLength = (GregorianCalNow.getTimeInMillis() - entryDate.getTimeInMillis()) / 3600000;
                            if (HourLength > 0) {
                                formatedDate = String.valueOf(HourLength) + " " + con.getResources().getString(R.string.HoursAgo_Title) + " ";
                            } else {
                                long MinuteLength = (GregorianCalNow.getTimeInMillis() - entryDate.getTimeInMillis()) / 60000;
                                if (MinuteLength > 0) {
                                    formatedDate = String.valueOf(MinuteLength) + " " + con.getResources().getString(R.string.MinutesAgo_Title) + " ";
                                } else {
                                    long SecondLength = (GregorianCalNow.getTimeInMillis() - entryDate.getTimeInMillis()) / (1000);
                                    if (SecondLength > 0) {
                                        formatedDate = String.valueOf(SecondLength) + " " + con.getResources().getString(R.string.SecondAgo_Title) + " ";
                                    } else
                                        formatedDate = "همین الان";
                                }
                            }
                        }
                    }
                }
            }

            return formatedDate;
        } catch (Exception e) {
            e.printStackTrace();
            //Logger.e(e);
        }
        return DateFormat.format("yyyy-MM-dd hh:mm:ss", new java.util.Date()).toString();
    }

    public static String getFullTime(long date) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);

        Calendar customDate = Calendar.getInstance();
        customDate.setTimeInMillis(date);

        String formatedDate = "";
        CalendarTool calender = new CalendarTool(customDate.get(Calendar.YEAR), customDate.get(Calendar.MONTH) + 1, customDate.get(Calendar.DAY_OF_MONTH));


        formatedDate = calender.getIranianWeekDayStr() + " " +
                calender.getIranianDay() + " " + calender.getIranianMonthName() + " "
                + calender.getIranianYear() + "  " + String.format(Locale.US, "%02d", customDate.get(Calendar.HOUR_OF_DAY))
                + ":" + String.format(Locale.US, "%02d", customDate.get(Calendar.MINUTE))
                + ":" + String.format(Locale.US, "%02d", customDate.get(Calendar.SECOND))
        ;


        return formatedDate;
    }

    public static String getDate(long date) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd", Locale.US);

        Calendar customDate = Calendar.getInstance();
        customDate.setTimeInMillis(date);

        String formatedDate = "";
        CalendarTool calender = new CalendarTool(customDate.get(Calendar.YEAR), customDate.get(Calendar.MONTH) + 1, customDate.get(Calendar.DAY_OF_MONTH));


        formatedDate = calender.getIranianDateYMD();


        return formatedDate;
    }


    public static int getHourOfDay(long date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static String getDetailTime(long date) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);

        Calendar customDate = Calendar.getInstance();
        customDate.setTimeInMillis(date);

        String formatedDate = "";
        CalendarTool calender = new CalendarTool(customDate.get(Calendar.YEAR), customDate.get(Calendar.MONTH) + 1, customDate.get(Calendar.DAY_OF_MONTH));


        formatedDate = String.format(Locale.US, "%02d", customDate.get(Calendar.HOUR_OF_DAY))
                + ":" + String.format(Locale.US, "%02d", customDate.get(Calendar.MINUTE))
                + ":" + String.format(Locale.US, "%02d", customDate.get(Calendar.SECOND))
                + " " + calender.getIranianDateYMD();
        ;

        return formatedDate;
    }
}
