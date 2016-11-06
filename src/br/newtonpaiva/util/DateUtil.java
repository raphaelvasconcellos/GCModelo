/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.util;

import java.sql.Date;
import java.sql.Types;
import java.util.Calendar;

/**
 *
 * @author tarle
 */
public class DateUtil {
    public static java.sql.Date converter(Calendar data) {
        if(data == null)
            return null;
        
        long timeInMillis = data.getTimeInMillis();
        return new java.sql.Date(timeInMillis);
    }
    
    public static Calendar converter(java.sql.Date data) {
        if(data == null)
            return null;
        
        long timeInMillis = data.getTime();
        Calendar retorno = Calendar.getInstance();
        retorno.setTimeInMillis(timeInMillis);
        return retorno;
    }
    
    public static Integer getDifferenceDays(Date d1, Date d2) {
        if(d1 == null || d2 == null)
            return null;
        
        int daysdiff = 0;
        long diff = d2.getTime() - d1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
        daysdiff = (int) diffDays;
        return daysdiff;
    }
}
