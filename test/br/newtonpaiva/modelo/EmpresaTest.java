package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excessoes.EmpresaInvalidaException;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lucas Fornero
 */
public class EmpresaTest {
    
    public EmpresaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    private Empresa u;
    
    @Before
    public void setUp() throws SQLException {
        
    }
    
    @After
    public void tearDown() throws SQLException {
        
    }

    @Test
    // Salvando nova Empresa
    public void testSalvar() {
        Empresa emp1 = new Empresa();
        emp1.setNome("Hospital Vera Cruz");
        emp1.setCnpj("13185421000108");
        emp1.setEndereco("Rua Paracatu, 700");
        emp1.setBairro("Barro Preto");
        emp1.setCep("30710580");
        emp1.setCidade("Belo Horizonte");
        emp1.setUf("MG");
        emp1.setEmail("ti@hvc.com.br");
        emp1.setTelefone("33711000");
        emp1.setContato("Lucas Fornero");
        emp1.setObs("Entrada Funcionários");
        
        try {
            // Inserir
            emp1.salvar();            
            Assert.assertNotNull(emp1.getId());
            
            // Atualizar
            emp1.setNome("HVC");
            emp1.setEndereco("Rua Barbacena");
            emp1.setEmail("fornero@gmail.com");
            emp1.setTelefone("99999999");
            emp1.setContato("JOSE");
            emp1.salvar();
            
            int numLinhasExcluidas = Empresa.excluir(emp1.getId());
            Assert.assertEquals(1, numLinhasExcluidas);
        } catch (EmpresaInvalidaException | SQLException ex) {
            Assert.fail(ex.getMessage());
        }
    }
    @Test(expected = EmpresaInvalidaException.class)
    public void testNome() throws EmpresaInvalidaException {
        Empresa emp1 = new Empresa();       
        emp1.setCnpj("13185421000108");
        emp1.setEndereco("Rua Paracatu, 700");
        emp1.setBairro("Barro Preto");
        emp1.setCep("30710580");
        emp1.setCidade("Belo Horizonte");
        emp1.setUf("MG");
        emp1.setEmail("ti@hvc.com.br");
        emp1.setTelefone("33711000");
        emp1.setContato("Lucas Fornero");
        emp1.setObs("Entrada Funcionários");    
        
        try {            
            emp1.salvar();            
        } catch(EmpresaInvalidaException | SQLException ex){           
            throw (new EmpresaInvalidaException(ex.getMessage()));
        }
    }
    
    /**
     * Test of buscarPorId method, of class Empresa.
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscarPorId() throws Exception {
        
        Empresa emp2 = Empresa.buscarPorId(1);       
        System.out.println(emp2.toString());
               
    }

    /**
     * Test of buscarTodos method, of class Empresa.     
     * @throws java.sql.SQLException
    */ 
    @Test
    public void testBuscarTodos() throws SQLException {
        List<Empresa> emp2 = Empresa.buscarTodos();
        for(Object i : emp2)
            System.out.println(i);
    }
    
}
