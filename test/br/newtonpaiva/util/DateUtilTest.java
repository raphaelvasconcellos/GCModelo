/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.util;

import java.util.Calendar;
import java.util.Date;
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
        System.out.println("converter");
        Calendar data = null;
        Date expResult = null;
        Date result = DateUtil.converter(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converter method, of class DateUtil.
     */
    @Test
    public void testConverter_Date() {
        System.out.println("converter");
        java.sql.Date data = null;
        Calendar expResult = null;
        Calendar result = DateUtil.converter(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converter method, of class DateUtil.
     */
    @Test
    public void testConverter_String() {
        System.out.println("converter");
        String data = "";
        Calendar expResult = null;
        Calendar result = DateUtil.converter(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDifferenceDays method, of class DateUtil.
     */
    @Test
    public void testGetDifferenceDays() {
        System.out.println("getDifferenceDays");
        Date d1 = null;
        Date d2 = null;
        Long expResult = null;
        Long result = DateUtil.getDifferenceDays(d1, d2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of getDifferenceDays method, of class DateUtil.
     */
    @Test
    public void testGetDifferenceDays2() {
        System.out.println("getDifferenceDays");
        Calendar d1 = null;
        Calendar d2 = null;
        Long expResult = null;
        Long result = DateUtil.getDifferenceDays(d1, d2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
