/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excessoes.AlunoInvalidoException;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

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
        a.setNome("Eiderson");
        a.setCpf("0695846650");
        a.setEmail("eiderson@hotmail.com");
        a.setDeficiente("N");        
        
        try {            
            a.salvar();
            Assert.assertNotNull(a.getId());
            a.setNome("Einderson Novo");
            a.salvar();
            int numLinhasExcluidas = a.excluir(a.getId());
            Assert.assertEquals(1, numLinhasExcluidas);
            
        } catch (AlunoInvalidoException | SQLException ex) {
            Assert.fail();
        }
    }
    
    
    @Test(expected = AlunoInvalidoException.class)
    public void testNome() throws AlunoInvalidoException {
        Aluno a = new Aluno();        
        a.setCurso(Curso.ENGENHARIA_CIVIL);
        a.setRa("11412458");        
        a.setCpf("0695846650");
        a.setEmail("eiderson@hotmail.com");
        a.setDeficiente("N");        
        
        try {            
            a.salvar();            
        } catch(AlunoInvalidoException | SQLException ex){           
            throw (new AlunoInvalidoException(ex.getMessage()));
        }
    }
    
    @Test(expected = AlunoInvalidoException.class)
    public void testRA() throws AlunoInvalidoException {
        Aluno a = new Aluno();        
        a.setCurso(Curso.ENGENHARIA_CIVIL);
        a.setNome("Eiderson");       
        a.setCpf("0695846650");
        a.setEmail("eiderson@hotmail.com");
        a.setDeficiente("N");        
        
        try {            
            a.salvar();            
        } catch(AlunoInvalidoException | SQLException ex){           
            throw (new AlunoInvalidoException(ex.getMessage()));
        }
    }
    
    @Test(expected = AlunoInvalidoException.class)
    public void testCurso() throws AlunoInvalidoException {
        Aluno a = new Aluno();        
        a.setNome("Eiderson");       
        a.setRa("11412458");
        a.setCpf("0695846650");
        a.setEmail("eiderson@hotmail.com");
        a.setDeficiente("N");        
        
        try {            
            a.salvar();            
        } catch(AlunoInvalidoException | SQLException ex){           
            throw (new AlunoInvalidoException(ex.getMessage()));
        }
    }


    /**
     * Test of buscarPorId method, of class Aluno.
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscarPorId() throws Exception {
        Aluno a = new Aluno().buscarPorId(3);        
        System.out.println(a.toString());
               
    }

    /**
     * Test of buscarTodos method, of class Aluno.
    */ 
    @Test
    public void testBuscarTodos() throws SQLException {
        List<Aluno> a = new Aluno().buscarTodos();
        for(Object i : a)
            System.out.println(i);
    }
    
}
