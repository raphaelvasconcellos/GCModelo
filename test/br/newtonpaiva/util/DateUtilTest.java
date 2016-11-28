/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tarle
 */
public class DateUtilTest {

    public DateUtilTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of converter method, of class DateUtil.
     */
    @Test
    public void testConverter_Calendar() {
        assertNull("Falha na conversao de Calendar nulo", DateUtil.converter((Calendar) null));

        Calendar expResult = new GregorianCalendar(2016, Calendar.NOVEMBER, 27, 22, 17, 00);
        java.sql.Date result = DateUtil.converter(expResult);
        assertEquals(new java.sql.Date(expResult.getTimeInMillis()), result);
    }

    /**
     * Test of converter method, of class DateUtil.
     */
    @Test
    public void testConverter_Date() {

        assertNull("Falha na conversao de java.sql.Date nula", DateUtil.converter((java.sql.Date) null));

        Calendar expResult = new GregorianCalendar(2016, Calendar.NOVEMBER, 27, 22, 17, 00);
        Calendar result = DateUtil.converter(new java.sql.Date(expResult.getTimeInMillis()));
        assertEquals(expResult, result);
    }

    /**
     * Test of converter method, of class DateUtil.
     */
    @Test
    public void testConverter_String() {

        assertNull("Falha na conversao de String nula", DateUtil.converter((String) null));

        Calendar expResult = new GregorianCalendar(2016, Calendar.NOVEMBER, 27);
        Calendar result = DateUtil.converter("27/11/2016");
        assertEquals(expResult, result);
    }

    /**
     * Test of getDifferenceDays method, of class DateUtil.
     */
    @Test
    public void testGetDifferenceDays_Date01() {
        System.out.println("testGetDifferenceDays_Date01");

        assertNull("Falha na diferença de datas na Data nula", DateUtil
                .getDifferenceDays((java.util.Date) null, (java.util.Date) null));
    }

    /**
     * Test of getDifferenceDays method, of class DateUtil.
     */
    @Test
    public void testGetDifferenceDays_Date02() {
        System.out.println("testGetDifferenceDays_Date02");

        Calendar d1 = new GregorianCalendar(2016, Calendar.NOVEMBER, 27);
        Calendar d2 = new GregorianCalendar(2016, Calendar.NOVEMBER, 30);

        Long expResult = 3L;
        Long result = DateUtil.getDifferenceDays(
                new Date(d1.getTimeInMillis()), new Date(d2.getTimeInMillis()));
        assertEquals(expResult, result);
    }

    /**
     * Test of getDifferenceDays method, of class DateUtil.
     */
    @Test
    public void testGetDifferenceDays_Date03() {
        System.out.println("testGetDifferenceDays_Date03");

        Calendar d1 = new GregorianCalendar(2016, Calendar.NOVEMBER, 27);
        Calendar d2 = new GregorianCalendar(2016, Calendar.NOVEMBER, 30, 12, 57, 30);

        Long expResult = 3L;
        Long result = DateUtil.getDifferenceDays(
                new Date(d1.getTimeInMillis()), new Date(d2.getTimeInMillis()));
        assertEquals(expResult, result);
    }

    /**
     * Test of getDifferenceDays method, of class DateUtil.
     */
    @Test
    public void testGetDifferenceDays_Date04() {
        System.out.println("testGetDifferenceDays_Date04");

        Calendar d1 = new GregorianCalendar(2016, Calendar.NOVEMBER, 27);
        Calendar d2 = new GregorianCalendar(2017, Calendar.NOVEMBER, 27);

        Long expResult = 365L;
        Long result = DateUtil.getDifferenceDays(
                new Date(d1.getTimeInMillis()), new Date(d2.getTimeInMillis()));
        assertEquals(expResult, result);
    }

    /**
     * Test of getDifferenceDays method, of class DateUtil.
     */
    @Test
    public void testGetDifferenceDays_Calendar01() {
        System.out.println("testGetDifferenceDays_Calendar01");

        assertNull("Falha na diferenca de datas Calendar nula", DateUtil
                .getDifferenceDays((Calendar) null, (Calendar) null));
    }

    /**
     * Test of getDifferenceDays method, of class DateUtil.
     */
    @Test
    public void testGetDifferenceDays_Calendar02() {
        System.out.println("testGetDifferenceDays_Calendar02");

        Calendar d1 = new GregorianCalendar(2016, Calendar.NOVEMBER, 27);
        Calendar d2 = new GregorianCalendar(2016, Calendar.NOVEMBER, 30);

        Long expResult = 3L;
        Long result = DateUtil.getDifferenceDays(d1, d2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDifferenceDays method, of class DateUtil.
     */
    @Test
    public void testGetDifferenceDays_Calendar03() {
        System.out.println("testGetDifferenceDays_Calendar03");

        Calendar d1 = new GregorianCalendar(2016, Calendar.NOVEMBER, 27);
        Calendar d2 = new GregorianCalendar(2016, Calendar.NOVEMBER, 30, 15, 40, 40);

        Long expResult = 3L;
        Long result = DateUtil.getDifferenceDays(d1, d2);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of getDifferenceDays method, of class DateUtil.
     */
    @Test
    public void testGetDifferenceDays_Calendar04() {
        System.out.println("testGetDifferenceDays_Calendar04");

        Calendar d1 = new GregorianCalendar(2016, Calendar.NOVEMBER, 27);
        Calendar d2 = new GregorianCalendar(2017, Calendar.NOVEMBER, 27);

        Long expResult = 365L;
        Long result = DateUtil.getDifferenceDays(d1, d2);
        assertEquals(expResult, result);
    }
}
