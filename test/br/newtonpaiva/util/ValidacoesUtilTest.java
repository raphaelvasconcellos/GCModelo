/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.util;

import java.util.Calendar;
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
public class ValidacoesUtilTest {
    
    public ValidacoesUtilTest() {
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
     * Test of validarTamanhoTexto method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarTamanhoTexto() {
        System.out.println("validarTamanhoTexto");
        String Texto = "";
        Integer Tamanho = null;
        boolean expResult = false;
        boolean result = ValidacoesUtil.validarTamanhoTexto(Texto, Tamanho);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarEmail method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarEmail() {
        System.out.println("validarEmail");
        String email = "";
        boolean expResult = false;
        boolean result = ValidacoesUtil.validarEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarLetras method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarLetras() {
        System.out.println("validarLetras");
        String letras = "";
        boolean expResult = false;
        boolean result = ValidacoesUtil.validarLetras(letras);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarInteiro method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarInteiro() {
        System.out.println("validarInteiro");
        String letras = "";
        boolean expResult = false;
        boolean result = ValidacoesUtil.validarInteiro(letras);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarCep method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarCep() {
        System.out.println("validarCep");
        String letras = "";
        boolean expResult = false;
        boolean result = ValidacoesUtil.validarCep(letras);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarTelefone method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarTelefone() {
        System.out.println("validarTelefone");
        String letras = "";
        boolean expResult = false;
        boolean result = ValidacoesUtil.validarTelefone(letras);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of trocarString method, of class ValidacoesUtil.
     */
    @Test
    public void testTrocarString() {
        System.out.println("trocarString");
        String Texto = "";
        String Fixa = "";
        String Substituicao = "";
        String expResult = "";
        String result = ValidacoesUtil.trocarString(Texto, Fixa, Substituicao);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }  
}
