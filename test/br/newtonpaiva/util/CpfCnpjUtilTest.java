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
import static br.newtonpaiva.util.CpfCnpjUtil.*;

/**
 *
 * @author tarle
 */
public class CpfCnpjUtilTest {
    
    public CpfCnpjUtilTest() {
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
     * Test of formatarCpfCnpj method, of class CpfCnpjUtil.
     */
    @Test
    public void testFormatarCpfCnpj() {

        String result;
        
        result = formatarCpfCnpj(null);
        assertNull(result);
        
        result = formatarCpfCnpj("  ");
        assertNull(result);
        
        result = formatarCpfCnpj("16.521.155/0001-03");
        assertEquals("16.521.155/0001-03", result);
        
        result = formatarCpfCnpj("16521155000103");
        assertEquals("16.521.155/0001-03", result);
        
        result = formatarCpfCnpj("06744545665");
        assertEquals("067.445.456-65", result);
        
        result = formatarCpfCnpj("067.445.456-65");
        assertEquals("067.445.456-65", result);
    }

    /**
     * Test of removerFormatacaoCpfCnpj method, of class CpfCnpjUtil.
     */
    @Test
    public void testRemoverFormatacaoCpfCnpj() {
        String result;
        
        result = removerFormatacaoCpfCnpj(null);
        assertNull(result);
        
        result = removerFormatacaoCpfCnpj(" ");
        assertNull(result);
        
        result = removerFormatacaoCpfCnpj("16.521.155/0001-03");
        assertEquals("16521155000103", result);
        
        result = removerFormatacaoCpfCnpj("16521155000103");
        assertEquals("16521155000103", result);
        
        result = removerFormatacaoCpfCnpj("067.445.456-65");
        assertEquals("06744545665", result);
        
        result = removerFormatacaoCpfCnpj("06744545665");
        assertEquals("06744545665", result);
    }
    
     /**
     * Test of validarCNPJ method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarCNPJ() {
        assertTrue(validarCNPJ("16.521.155/0001-03"));
        assertTrue(validarCNPJ("16521155000103"));
        assertTrue(validarCNPJ("09.485.655/0001-71"));
        assertTrue(validarCNPJ("09485655000171"));
        assertTrue(validarCNPJ("38.194.699/0001-40"));
        assertTrue(validarCNPJ("38194699000140"));
        
        assertFalse(validarCNPJ(null));
        assertFalse(validarCNPJ(""));
        assertFalse(validarCNPJ("    "));
        assertFalse(validarCNPJ("067.445.456-65"));
        assertFalse(validarCNPJ("16.521.155/001-03"));
    }

    /**
     * Test of validarMascaraCpf method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarMascaraCpf() {
        assertTrue(validarMascaraCpf("067.445.456-65"));
        assertFalse(validarMascaraCpf("067.445.456.65"));
    }

    /**
     * Test of validarCPF method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarCPF() {
        assertTrue(validarCPF("534.354.384-79"));
        assertTrue(validarCPF("53435438479"));
        assertTrue(validarCPF("095.429.541-23"));
        assertTrue(validarCPF("09542954123"));
        assertTrue(validarCPF("318.438.051-38"));
        assertTrue(validarCPF("31843805138"));
        
        assertFalse(validarCPF(null));
        assertFalse(validarCPF(""));
        assertFalse(validarCPF("    "));
        assertFalse(validarCPF("67.445.456-65"));
        assertFalse(validarCPF("16.521.155/0001-03"));
    }
}
