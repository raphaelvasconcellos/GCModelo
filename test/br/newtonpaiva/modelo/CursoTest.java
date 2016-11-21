/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import static org.junit.Assert.*;

import br.newtonpaiva.modelo.excecoes.CursoInvalidoException;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author tarle
 */
public class CursoTest {
    
    public static final String DIREITO = "Direito";
    public static final String ENFERMAGEM = "Enfermagem";
    public static final String ENGENHARIA_CIVIL = "Engenharia Civil";
    public static final String ENGENHARIA_PRODUCAO = "Engenharia de ProduÃ§Ã£o";
    public static final String ENGENHARIA_ELETRICA = "Engenharia Eletrica";
    public static final String ENGENHARIA_MECANICA = "Engenharia Mecanica";
    public static final String ENGENHARIA_QUIMICA = "Engenharia Quimica";
    public static final String FARMACIA = "Farmacia";
    public static final String ODONTOLOGIA = "Odontologia";
    public static final String PSICOLOGIA = "Psicologia";
    public static final String SISTEMAS_INFORMACAO = "Sistemas de Informação";
    
    
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
     * Test of salvar method, of class Curso.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSalvar() throws SQLException {
        Curso c = new Curso();
        c.setCurso("Teste de cadastro");
        
        try {
            c.salvar();            
            assertNotNull(c.getId());
            
            // Atualizar
            c.setCurso("Alteração do curso de teste");            
            c.salvar();
            
            int numLinhasExcluidas = Curso.excluir(c.getId());
            assertEquals(1, numLinhasExcluidas);
        } catch (CursoInvalidoException e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testSalvarCursoDuplicado() throws SQLException {
        Curso c = new Curso();
        c.setCurso("Direito");
        
        try {
            c.salvar();
        } catch (CursoInvalidoException e) {
            assertEquals("Já existe um curso cadastrado com este nome.", e.getMessage());
        }
    }
    
    @Test
    public void testSalvarCursoNaoInformado() throws SQLException {
        Curso c = new Curso();
        
        try {
            c.salvar();
        } catch (CursoInvalidoException e) {
            assertEquals("O nome do curso deve ser informado.", e.getMessage());
        }
    }
    
    @Test
    public void testSalvarCursoComMais100Caracteres() throws SQLException {
        Curso c = new Curso();
        c.setCurso("asdfadfadfadfadfadfadfadfakjfdlaj"
                 + "dflkjafldkjalkffalfjlssjfflajdfla"
                 + "jdflajdflassfljalfdfjaldkfjaldfjas");
        try {
            c.salvar();
        } catch (CursoInvalidoException e) {
            assertEquals("O nome do curso deve ter menos de 100 caracteres.", e.getMessage());
        }
    }
    
    /**
     * Test of buscarPorId method, of class Curso.
     * @throws java.sql.SQLException
     */
    @Test
    public void testBuscarPorId() throws SQLException {
        Curso c = Curso.buscarPorId(1);     
        assertEquals(DIREITO, c.getCurso());
    }

    /**
     * Test of buscarPorNome method, of class Curso.
     * @throws java.sql.SQLException
     */
    @Test
    public void testBuscarPorNome() throws SQLException {
        List<Curso> c = Curso.buscarPorNome("Engenharia");
        assertEquals(5, c.size());
        assertEquals(ENGENHARIA_CIVIL, c.get(0).getCurso());
        assertEquals(ENGENHARIA_PRODUCAO, c.get(1).getCurso());
        assertEquals(ENGENHARIA_ELETRICA, c.get(2).getCurso());
        assertEquals(ENGENHARIA_MECANICA, c.get(3).getCurso());
        assertEquals(ENGENHARIA_QUIMICA, c.get(4).getCurso());
    }

    /**
     * Test of buscarTodos method, of class Curso.
     * @throws java.sql.SQLException
     */
    @Test
    public void testBuscarTodos() throws SQLException {
        List<Curso> c = Curso.buscarTodos();
        assertEquals(11, c.size());
        assertEquals(DIREITO, c.get(0).getCurso());
        assertEquals(ENFERMAGEM, c.get(1).getCurso());
        assertEquals(ENGENHARIA_CIVIL, c.get(2).getCurso());
        assertEquals(ENGENHARIA_PRODUCAO, c.get(3).getCurso());
        assertEquals(ENGENHARIA_ELETRICA, c.get(4).getCurso());
        assertEquals(ENGENHARIA_MECANICA, c.get(5).getCurso());        
        assertEquals(ENGENHARIA_QUIMICA, c.get(6).getCurso());
    }

    /**
     * Test of buscarPorTotalConvenios method, of class Curso.
     * @throws java.sql.SQLException
     */
    @Test
    public void testCarregarConvenios() throws SQLException {
        Curso c = Curso.buscarPorId(1);
        c.carregarConvenios();
        
        assertEquals(1, c.getListaConvenios().size());
        ConvenioTest.validarConvenioPadrao(c.getListaConvenios().get(0));
    }

    /**
     * Test of buscarPorTotalAlunos method, of class Curso.
     * @throws java.sql.SQLException
     */
    @Test
    public void testBuscarPorTotalAlunos() throws SQLException {
        Curso c = Curso.buscarPorId(1);
        c.carregarAlunos();
        
        assertEquals(1, c.getListaAlunos().size());
        AlunoTest.validarAlunoDeficiente(c.getListaAlunos().get(0));
    }
    
    /**
     * Test of getTotalConvenio method, of class Curso.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetTotalConvenio() throws SQLException {
        Curso c = Curso.buscarPorId(1);
        c.carregarConvenios();
        
        assertEquals(1, c.getTotalConvenio());
    }

    /**
     * Test of getTotalAluno method, of class Curso.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetTotalAluno() throws SQLException {
        Curso c = Curso.buscarPorId(1);
        c.carregarAlunos();
        
        assertEquals(1, c.getTotalAluno());
    }
}
