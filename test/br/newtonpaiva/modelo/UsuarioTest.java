/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;


import br.newtonpaiva.modelo.excessoes.LoginException;
import br.newtonpaiva.modelo.excessoes.SenhaException;
import br.newtonpaiva.modelo.excessoes.UsuarioInvalidoException;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Tarley Lana
 */
public class UsuarioTest {
    
    /**
     * Test of validar method, of class Usuario.
     * @throws java.sql.SQLException
     */
    @Test
    public void testValidar() throws SQLException {
        Boolean valor = Usuario.validar("admin", "123");
        Assert.assertTrue(valor);
        
    }
    
    /**
     * Test of Login method, of class Usuario.
     * @throws java.sql.SQLException
     */
    @Test(expected = LoginException.class)
    public void testLoginException() throws SQLException{
        Usuario.validar("", "admin");
        Assert.fail();
    }
    /**
     * 
     * Test of Senha method, of class Usuario.
     * @throws java.sql.SQLException
     */
    @Test(expected = SenhaException.class)
    public void testSenhaException() throws SQLException{
        Usuario.validar("admin", "123456");
        Assert.fail();
    }
    
    /**
     * 
     * Test of Salvar method, of class Usuario.
     */
    @Test
    public void testSalvar(){
        Usuario u = new Usuario();
        u.setNome("Teste Salvar");
        u.setLogin("teste");
        u.setSenha("123456");
        u.setEmail("teste@newtonpaiva.br");
        try {            
            u.salvar();
            Assert.assertNotNull(u.getId());
            u.setNome("Novo teste");
            u.salvar();
            int numLinhasExcluidas = Usuario.excluir(u.getId());
            Assert.assertEquals(1, numLinhasExcluidas);
            
        } catch (UsuarioInvalidoException | SQLException ex) {
            Assert.fail(ex.getMessage());
        }
    }
    
    /**
     * Test of buscarPorId method, of class Usuario.
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscarPorId() throws Exception {
        Usuario u = Usuario.buscarPorId(1);        
        Assert.assertEquals("admin", u.getLogin());               
    }

    /**
     * Test of buscarTodos method, of class Usuario.
     * @throws java.sql.SQLException
    */ 
    @Test
    public void testBuscarTodos() throws SQLException {
        List<Usuario> a = new Usuario().buscarTodos();
        Assert.assertEquals("admin", a.get(0).getLogin());
    }
    
    @Test(expected = UsuarioInvalidoException.class)
    public void testNome() throws UsuarioInvalidoException, SQLException {
        Usuario u = new Usuario();        
        u.setLogin("teste");
        u.setSenha("123456");
        u.setEmail("teste@newtonpaiva.br");
        u.salvar();       
    }
    
    @Test(expected = UsuarioInvalidoException.class)
    public void testLogin() throws UsuarioInvalidoException, SQLException {
        Usuario u = new Usuario();
        u.setNome("Teste");       
        u.setSenha("123456");
        u.setEmail("teste@newtonpaiva.br");
        u.salvar();       
    }
    
    @Test(expected = UsuarioInvalidoException.class)
    public void testSenha() throws UsuarioInvalidoException, SQLException {
        Usuario u = new Usuario();
        u.setNome("Teste");       
        u.setLogin("teste");
        u.setEmail("teste@newtonpaiva.br");
        u.salvar();          
    }
    
    @Test(expected = UsuarioInvalidoException.class)
    public void testEmail() throws UsuarioInvalidoException, SQLException {
        Usuario u = new Usuario();
        u.setNome("Teste");       
        u.setSenha("123456");
        u.setLogin("teste");
        u.salvar();     
    }
}