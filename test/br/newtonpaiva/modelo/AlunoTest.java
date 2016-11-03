/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excessoes.AlunoInvalidoException;
import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Eiderson
 */
public class AlunoTest {
    

    /**
     * Test of salvar method, of class Aluno.
     */
    @Test
    public void testSalvar() {
        Aluno a = new Aluno();        
        a.setCurso(Curso.ENGENHARIA_CIVIL);
        a.setRa("11412458");
        a.setNome("Tarley Lana");
        a.setCpf("098.334.332-98");
        a.setEmail("tarley.lana@gmail.com");
        a.setDeficiente("N");        
        
        try {            
            a.salvar();
            assertNotNull(a.getId());
            
            a.setNome("Tarley Lana Campos");
            a.setCurso(Curso.ENGENHARIA_DE_PRODUCAO);
            a.salvar();
            
            a = Aluno.buscarPorId(a.getId());
            
            assertEquals("Tarley Lana Campos", a.getNome());
            assertEquals(Curso.ENGENHARIA_DE_PRODUCAO, a.getCurso());
            assertEquals("098.334.332-98", a.getCpfFormatado());
            assertEquals("09833433298", a.getCpf());
                        
            int numLinhasExcluidas = a.excluir(a.getId());
            assertEquals(1, numLinhasExcluidas);
        } catch (AlunoInvalidoException | SQLException ex) {
            fail();
        }
    }
    
    
    @Test(expected = AlunoInvalidoException.class)
    public void testNome() throws AlunoInvalidoException, SQLException {
        Aluno a = new Aluno();        
        a.setCurso(Curso.ENGENHARIA_CIVIL);
        a.setRa("11412458");        
        a.setCpf("0695846650");
        a.setEmail("eiderson@hotmail.com");
        a.setDeficiente("N");        
        
        a.salvar();
    }
    
    @Test(expected = AlunoInvalidoException.class)
    public void testRA() throws AlunoInvalidoException, SQLException {
        Aluno a = new Aluno();        
        a.setCurso(Curso.ENGENHARIA_CIVIL);
        a.setNome("Eiderson");       
        a.setCpf("0695846650");
        a.setEmail("eiderson@hotmail.com");
        a.setDeficiente("N");        
        a.salvar();
    }
    
    @Test(expected = AlunoInvalidoException.class)
    public void testCurso() throws AlunoInvalidoException, SQLException {
        Aluno a = new Aluno();        
        a.setNome("Eiderson");       
        a.setRa("11412458");
        a.setCpf("0695846650");
        a.setEmail("eiderson@hotmail.com");
        a.setDeficiente("N");        
        a.salvar();
    }


    /**
     * Test of buscarPorId method, of class Aluno.
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscarPorId() throws Exception {
        Aluno a = Aluno.buscarPorId(1);        
        validarAlunoTeste(a);
    }
    
    @Test
    public void testBuscarPorRA() throws Exception {
        Aluno a = Aluno.buscarPorRA("11223344");        
        validarAlunoTeste(a);
    }
    
    @Test
    public void testBuscarPorCPF() throws Exception {
        Aluno a = Aluno.buscarPorCPF("11111111111");        
        validarAlunoTeste(a);
        
        a = Aluno.buscarPorCPF("111.111.111-11");        
        validarAlunoTeste(a);
    }

    private void validarAlunoTeste(Aluno a) {
        assertEquals((int) 1, (int) a.getId());
        assertEquals(Curso.SISTEMA_INFORMACAO, a.getCurso());
        assertEquals("11223344" , a.getRa());
        assertEquals("Aluno Teste", a.getNome());
        assertEquals("11111111111", a.getCpf());
        assertEquals("111.111.111-11", a.getCpfFormatado());
        assertEquals("aluno@newtonpaiva.br" , a.getEmail());
        assertEquals("N", a.getDeficiente());              
    }

    /**
     * Test of buscarTodos method, of class Aluno.
    */ 
    @Test
    public void testBuscarTodos() throws SQLException {
        List<Aluno> a = Aluno.buscarTodos();
        
        assertEquals((int) 1, a.size());
        validarAlunoTeste(a.get(0));
    }
    
    @Test
    public void testBuscarPorNome() throws SQLException {
        List<Aluno> a = Aluno.buscarPorNome("Aluno");
        
        assertEquals((int) 1, a.size());
        validarAlunoTeste(a.get(0));
        
        a = Aluno.buscarPorNome("Teste");
        
        assertEquals((int) 1, a.size());
        validarAlunoTeste(a.get(0));
        
        a = Aluno.buscarPorNome("uno Tes");
        
        assertEquals((int) 1, a.size());
        validarAlunoTeste(a.get(0));
    }
}
