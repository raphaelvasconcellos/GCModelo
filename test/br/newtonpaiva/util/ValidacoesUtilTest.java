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
        assertTrue(ValidacoesUtil.validarTamanhoTexto(null, 1));
        assertTrue(ValidacoesUtil.validarTamanhoTexto("", 10));
        assertTrue(ValidacoesUtil.validarTamanhoTexto("          ", 10));
        assertTrue(ValidacoesUtil.validarTamanhoTexto("012345678", 10));
        assertTrue(ValidacoesUtil.validarTamanhoTexto("0123456789", 10));
        
        assertFalse(ValidacoesUtil.validarTamanhoTexto("123", 2));
        assertFalse(ValidacoesUtil.validarTamanhoTexto("   ", 2));
    }

    /**
     * Test of validarEmail method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarEmail() {
        assertTrue(ValidacoesUtil.validarEmail("tarley.lana@gmail.com"));
        
        assertFalse(ValidacoesUtil.validarEmail("tarley.lana"));
        assertFalse(ValidacoesUtil.validarEmail("tarley.lana@tarley"));
        assertFalse(ValidacoesUtil.validarEmail("tarley.lana.com"));
        assertFalse(ValidacoesUtil.validarEmail("@gmail.com"));
        assertFalse(ValidacoesUtil.validarEmail(""));
        assertFalse(ValidacoesUtil.validarEmail(null));
    }

    /**
     * Test of validarLetras method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarLetras() {
        assertTrue(ValidacoesUtil.validarLetras(null));
        assertTrue(ValidacoesUtil.validarLetras(""));
        assertTrue(ValidacoesUtil.validarLetras("     "));
        assertTrue(ValidacoesUtil.validarLetras("ABCDEFGH"));
        
        assertFalse(ValidacoesUtil.validarLetras("ABCDE1FGH"));
        assertFalse(ValidacoesUtil.validarLetras(" 123"));        
        assertFalse(ValidacoesUtil.validarLetras("1"));
        assertFalse(ValidacoesUtil.validarLetras("12345"));
        
    }

    /**
     * Test of validarInteiro method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarInteiro() {
        assertTrue(ValidacoesUtil.validarInteiro("12334"));
        
        assertFalse(ValidacoesUtil.validarInteiro(null));
        assertFalse(ValidacoesUtil.validarInteiro(" "));
        assertFalse(ValidacoesUtil.validarInteiro("     "));
        assertFalse(ValidacoesUtil.validarInteiro("12345A6789"));
    }

    /**
     * Test of validarCep method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarCep() {
        assertTrue(ValidacoesUtil.validarCep("32295181"));
        assertTrue(ValidacoesUtil.validarCep("32.295-181"));
        
        assertFalse(ValidacoesUtil.validarCep(null));
        assertFalse(ValidacoesUtil.validarCep(" "));
        assertFalse(ValidacoesUtil.validarCep("     "));
        assertFalse(ValidacoesUtil.validarCep("12345A6789"));
    }

    /**
     * Test of validarTelefone method, of class ValidacoesUtil.
     */
    @Test
    public void testValidarTelefone() {
        assertTrue(ValidacoesUtil.validarTelefone("(31) 98888-1111"));
        assertTrue(ValidacoesUtil.validarTelefone("(31) 98877-2233"));
        assertTrue(ValidacoesUtil.validarTelefone("(31) 8877-2233"));
        assertTrue(ValidacoesUtil.validarTelefone("(31) 3333-4466"));
                
        assertFalse(ValidacoesUtil.validarTelefone(null));
        assertFalse(ValidacoesUtil.validarTelefone(" "));
        assertFalse(ValidacoesUtil.validarTelefone("     "));
        assertFalse(ValidacoesUtil.validarTelefone("988772222"));
        assertFalse(ValidacoesUtil.validarTelefone("(31) 998877-2222"));
        assertFalse(ValidacoesUtil.validarTelefone("(31) 18877-2222"));

    }
}
