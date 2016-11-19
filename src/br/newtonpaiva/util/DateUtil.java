/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author tarle
 */
public class DateUtil {

    public static java.sql.Date converter(Calendar data) {
        if (data == null) {
            return null;
        }

        long timeInMillis = data.getTimeInMillis();
        return new java.sql.Date(timeInMillis);
    }

    public static Calendar converter(java.sql.Date data) {
        if (data == null) {
            return null;
        }

        long timeInMillis = data.getTime();
        Calendar retorno = Calendar.getInstance();
        retorno.setTimeInMillis(timeInMillis);
        return retorno;
    }

    public static Calendar converter(String data) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
            Calendar retorno = new GregorianCalendar();
            retorno.setTime(sd.parse(data));
            return retorno;
        } catch (ParseException e) {
            return null;
        }
    }

    public static Long getDifferenceDays(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return null;
        }

        long diff = d2.getTime() - d1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
        return diffDays;
    }
    
    public static Long getDifferenceDays(Calendar d1, Calendar d2) {
        return getDifferenceDays(d1.getTime(), d2.getTime());
    }
}
