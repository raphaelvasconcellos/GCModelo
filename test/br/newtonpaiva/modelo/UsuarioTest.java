/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;


import br.newtonpaiva.modelo.excecoes.LoginException;
import br.newtonpaiva.modelo.excecoes.SenhaException;
import br.newtonpaiva.modelo.excecoes.UsuarioInvalidoException;
import java.sql.SQLException;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.*;
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
        assertTrue(valor);        
    }
    
    /**
     * Test of Login method, of class Usuario.
     * @throws java.sql.SQLException
     */
    @Test(expected = LoginException.class)
    public void testLoginException() throws SQLException{
        Usuario.validar("", "admin");
        fail();
    }
    /**
     * 
     * Test of Senha method, of class Usuario.
     * @throws java.sql.SQLException
     */
    @Test(expected = SenhaException.class)
    public void testSenhaException() throws SQLException{
        Usuario.validar("admin", "123456");
        fail();
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
            assertNotNull(u.getId());
            u.setNome("Novo teste");
            u.salvar();
            int numLinhasExcluidas = Usuario.excluir(u.getId());
            assertEquals(1, numLinhasExcluidas);
            
        } catch (UsuarioInvalidoException | SQLException ex) {
            fail(ex.getMessage());
        }
    }
    
    /**
     * Test of buscarPorId method, of class Usuario.
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscarPorId() throws Exception {
        Usuario u = Usuario.buscarPorId(1);
        validarUsuarioAdmin(u);        
    }

    /**
     * Test of buscarTodos method, of class Usuario.
     * @throws java.sql.SQLException
    */ 
    @Test
    public void testBuscarTodos() throws SQLException {
        List<Usuario> a = new Usuario().buscarTodos();
        validarUsuarioAdmin(a.get(0));        
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
    
    public void testSalvarSenha() {
        fail();
    }
    
    public void testBuscarSenhaPorLogin() {
        fail();
    }
    
    public static void validarUsuarioAdmin(Usuario u) {
        assertEquals(1, (int) u.getId());
        assertEquals("Administrador", u.getNome());
        assertEquals("admin", u.getLogin());
        assertEquals("admin@newtonpaiva.br", u.getEmail());
        
        if(u.getSenha() != null)
            assertEquals("123", u.getSenha());
    }
}