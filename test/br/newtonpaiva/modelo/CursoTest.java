/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import java.util.List;
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
public class CursoTest {
    
    public CursoTest() {
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
     * Test of getId method, of class Curso.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Curso instance = new Curso();
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Curso.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = null;
        Curso instance = new Curso();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurso method, of class Curso.
     */
    @Test
    public void testGetCurso() {
        System.out.println("getCurso");
        Curso instance = new Curso();
        String expResult = "";
        String result = instance.getCurso();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurso method, of class Curso.
     */
    @Test
    public void testSetCurso() {
        System.out.println("setCurso");
        String curso = "";
        Curso instance = new Curso();
        instance.setCurso(curso);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalConvenio method, of class Curso.
     */
    @Test
    public void testGetTotalConvenio() {
        System.out.println("getTotalConvenio");
        Curso instance = new Curso();
        Integer expResult = null;
        Integer result = instance.getTotalConvenio();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTotalConvenio method, of class Curso.
     */
    @Test
    public void testSetTotalConvenio() {
        System.out.println("setTotalConvenio");
        Integer totalConvenio = null;
        Curso instance = new Curso();
        instance.setTotalConvenio(totalConvenio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalAluno method, of class Curso.
     */
    @Test
    public void testGetTotalAluno() {
        System.out.println("getTotalAluno");
        Curso instance = new Curso();
        Integer expResult = null;
        Integer result = instance.getTotalAluno();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTotalAluno method, of class Curso.
     */
    @Test
    public void testSetTotalAluno() {
        System.out.println("setTotalAluno");
        Integer totalAluno = null;
        Curso instance = new Curso();
        instance.setTotalAluno(totalAluno);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Curso.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Curso instance = new Curso();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Curso.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Curso instance = new Curso();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Curso.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Curso instance = new Curso();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvar method, of class Curso.
     */
    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Curso instance = new Curso();
        instance.salvar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluir method, of class Curso.
     */
    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        Integer id = null;
        Curso instance = new Curso();
        int expResult = 0;
        int result = instance.excluir(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorId method, of class Curso.
     */
    @Test
    public void testBuscarPorId() throws Exception {
        System.out.println("buscarPorId");
        Integer id = null;
        Curso expResult = null;
        Curso result = Curso.buscarPorId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorIdNome method, of class Curso.
     */
    @Test
    public void testBuscarPorIdNome() throws Exception {
        System.out.println("buscarPorIdNome");
        Integer id = null;
        String Nome = "";
        List<Curso> expResult = null;
        List<Curso> result = Curso.buscarPorIdNome(id, Nome);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorNome method, of class Curso.
     */
    @Test
    public void testBuscarPorNome() throws Exception {
        System.out.println("buscarPorNome");
        String nome = "";
        List<Curso> expResult = null;
        List<Curso> result = Curso.buscarPorNome(nome);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarTodos method, of class Curso.
     */
    @Test
    public void testBuscarTodos() throws Exception {
        System.out.println("buscarTodos");
        String Nome = "";
        List<Curso> expResult = null;
        List<Curso> result = Curso.buscarTodos(Nome);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorTotalConvenios method, of class Curso.
     */
    @Test
    public void testBuscarPorTotalConvenios() throws Exception {
        System.out.println("buscarPorTotalConvenios");
        Integer id = null;
        Curso expResult = null;
        Curso result = Curso.buscarPorTotalConvenios(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorTotalAlunos method, of class Curso.
     */
    @Test
    public void testBuscarPorTotalAlunos() throws Exception {
        System.out.println("buscarPorTotalAlunos");
        Integer id = null;
        Curso expResult = null;
        Curso result = Curso.buscarPorTotalAlunos(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
