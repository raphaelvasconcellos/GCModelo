package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excessoes.EmpresaInvalidaException;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
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
        Empresa e = new Empresa();
        e.setNome("Hospital Vera Cruz");
        e.setCnpj("13.185.421/0001-08");
        e.setEndereco("Rua Paracatu, 700");
        e.setBairro("Barro Preto");
        e.setCep("30710580");
        e.setCidade("Belo Horizonte");
        e.setUf("MG");
        e.setEmail("ti@hvc.com.br");
        e.setTelefone("33711000");
        e.setContato("Lucas Fornero");
        e.setObs("Entrada Funcionários");
        
        assertEquals("Hospital Vera Cruz", e.getNome());
        assertEquals("Rua Paracatu, 700", e.getEndereco());
        assertEquals("ti@hvc.com.br", e.getEmail());
        assertEquals("33711000", e.getTelefone());
        assertEquals("Lucas Fornero", e.getContato());
        assertEquals("Entrada Funcionários", e.getObs());
        assertEquals("13185421000108", e.getCnpj());
        assertEquals("13.185.421/0001-08", e.getCnpjFormatado());
        assertEquals("Barro Preto", e.getBairro());
        assertEquals("30710580", e.getCep());
        assertEquals("Belo Horizonte", e.getCidade());
        assertEquals("MG", e.getUf());
        
        try {
            // Inserir
            e.salvar();            
            assertNotNull(e.getId());
            
            // Atualizar
            e.setNome("HVC");
            e.setEndereco("Rua Barbacena");
            e.setEmail("fornero@gmail.com");
            e.setTelefone("99999999");
            e.setContato("JOSE");
            e.salvar();
            
            e = Empresa.buscarPorId(e.getId());
            
            assertEquals("HVC", e.getNome());
            assertEquals("Rua Barbacena", e.getEndereco());
            assertEquals("fornero@gmail.com", e.getEmail());
            assertEquals("99999999", e.getTelefone());
            assertEquals("JOSE", e.getContato());
            
            int numLinhasExcluidas = Empresa.excluir(e.getId());
            assertEquals(1, numLinhasExcluidas);
        } catch (EmpresaInvalidaException | SQLException ex) {
            fail(ex.getMessage());
        }
    }
    @Test(expected = EmpresaInvalidaException.class)
    public void testNome() throws EmpresaInvalidaException, SQLException {
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
        
        emp1.salvar();
    }
    
    /**
     * Test of buscarPorId method, of class Empresa.
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscarPorId() throws Exception {        
        Empresa e = Empresa.buscarPorId(1);
        validarEmpresaNewtonPaiva(e);        
    }
    
    /**
     * Test of buscarPorId method, of class Empresa.
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscarPorCNPJ() throws Exception {        
        Empresa e = Empresa.buscarPorCNPJ("16.521.155/0001-03");
        validarEmpresaNewtonPaiva(e);
        
        e = Empresa.buscarPorCNPJ("16521155000103");
        validarEmpresaNewtonPaiva(e);        
    }

    /**
     * Test of buscarPorId method, of class Empresa.
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscarPorNome() throws Exception {        
        List<Empresa> e = Empresa.buscarPorNome("Newton");
        validarEmpresaNewtonPaiva(e.get(0));
        
        e = Empresa.buscarPorNome("Paiva");
        validarEmpresaNewtonPaiva(e.get(0));
        
        e = Empresa.buscarPorNome("Newton Paiva");
        validarEmpresaNewtonPaiva(e.get(0));
    }
    
    /**
     * Test of buscarTodos method, of class Empresa.     
     * @throws java.sql.SQLException
    */ 
    @Test
    public void testBuscarTodos() throws SQLException {
        List<Empresa> e = Empresa.buscarTodos();
        validarEmpresaNewtonPaiva(e.get(0));
    }
    
    private void validarEmpresaNewtonPaiva(Empresa e) {
        assertEquals(1L, (long) e.getId());
        assertEquals("Newton Paiva", e.getNome());
        assertEquals("16521155000103", e.getCnpj());
        assertEquals("16.521.155/0001-03", e.getCnpjFormatado());
        assertEquals("R Jose Claudio Rezende, 420", e.getEndereco());
        assertEquals("Estoril", e.getBairro());
        assertEquals("30494230", e.getCep());
        assertEquals("Belo Horizonte", e.getCidade());
        assertEquals("MG", e.getUf());
        assertNull(e.getObs());
        assertNull(e.getTelefone());
    }
}
