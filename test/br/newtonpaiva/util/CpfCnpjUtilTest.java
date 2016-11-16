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
    public void testFormatar() {

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
    public void testRemoverFormatacao() {
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
        System.out.println("validarCNPJ");
        String CNPJ = "";
        boolean expResult = false;
        boolean result = validarCNPJ(CNPJ);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarMascaraCpf method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarMascaraCpf() {
        System.out.println("validarMascaraCpf");
        String letras = "";
        boolean expResult = false;
        boolean result = validarMascaraCpf(letras);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarCPF method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarCPF() {
        System.out.println("validarCPF");
        String CPF = "";
        boolean expResult = false;
        boolean result = validarCPF(CPF);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of formatarCpfCnpj method, of class ValidacoesUtil.
     */
    @Test
    public void testFormatarCpfCnpj() {
        System.out.println("formatarCpfCnpj");
        String cpfCnpj = "";
        String expResult = "";
        String result = formatarCpfCnpj(cpfCnpj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerFormatacaoCpfCnpj method, of class ValidacoesUtil.
     */
    @Test
    public void testRemoverFormatacaoCpfCnpj() {
        System.out.println("removerFormatacaoCpfCnpj");
        String cpfCnpj = "";
        String expResult = "";
        String result = removerFormatacaoCpfCnpj(cpfCnpj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }    
}
