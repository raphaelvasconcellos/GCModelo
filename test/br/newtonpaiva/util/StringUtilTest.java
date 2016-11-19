/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.util;

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
public class StringUtilTest {
    
    public StringUtilTest() {
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
     * Test of isNullOrWhiteSpace method, of class StringUtil.
     */
    @Test
    public void testIsNullOrWhiteSpace() {
        assertTrue(StringUtil.isNullOrWhiteSpace(""));
        assertTrue(StringUtil.isNullOrWhiteSpace("      "));
        assertTrue(StringUtil.isNullOrWhiteSpace(null));
        
        assertFalse(StringUtil.isNullOrWhiteSpace("."));
        assertFalse(StringUtil.isNullOrWhiteSpace(" . "));
        assertFalse(StringUtil.isNullOrWhiteSpace("1"));
        assertFalse(StringUtil.isNullOrWhiteSpace("a"));
    }
    
}
