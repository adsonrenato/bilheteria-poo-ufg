/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.poo.bilheteria.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alunoinf
 */
public class DateUtil {

    /**
     * 
     * @param dd/MM/yyyy
     * @return 
     */
    public static Calendar getCalendarFromString(String date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(dateFormat.parse(date));
        } catch (ParseException ex) {
            System.out.println("Data inválida: " + date);;
        }
        
        return cal;
    }
    
    /**
     * Retorna dd/MM/yyyy
     * @param date
     * @return 
     */
    public static String getStringFromCalendar(Calendar date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date.getTime());
    }
}
