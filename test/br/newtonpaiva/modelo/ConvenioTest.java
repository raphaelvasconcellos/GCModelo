package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excecoes.ConvenioInvalidoException;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Lucas Fornero
 */
public class ConvenioTest {
    @Test
    public void testSalvar() throws SQLException {
        Convenio c = new Convenio();
        c.setCurso(Curso.buscarPorId(1));
        c.setEmpresa(Empresa.buscarPorId(1));
        c.setSituacao(SituacaoConvenio.ANDAMENTO);
        c.setDataAssinatura(new GregorianCalendar(2016, Calendar.JANUARY, 4));
        c.setDataVencimento(new GregorianCalendar(2016, Calendar.NOVEMBER, 4));
        
        try {
            c.salvar();            
            assertNotNull(c.getId());
            
            // Atualizar
            c.setCurso(Curso.buscarPorId(2));            
            c.salvar();
            
            int numLinhasExcluidas = Convenio.excluir(c.getId());
            assertEquals(1, numLinhasExcluidas);
        } catch (ConvenioInvalidoException | SQLException ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void testEmpresa() throws ConvenioInvalidoException, SQLException {
        Convenio c = new Convenio();
        c.setCurso(Curso.buscarPorId(1));
        c.setSituacao(SituacaoConvenio.ANDAMENTO);
        try {
            c.salvar();
            fail("Salvou convenio sem empresa");
        }catch(ConvenioInvalidoException e) {
            assertEquals("A empresa deve ser informada.", e.getMessage());
        }
    }
    
    @Test
    public void testCurso() throws ConvenioInvalidoException, SQLException {
        Convenio c = new Convenio();
        c.setEmpresa(Empresa.buscarPorId(1));
        c.setSituacao(SituacaoConvenio.ANDAMENTO);        
        try {
            c.salvar();
            fail("Salvou convenio sem curso");
        }catch(ConvenioInvalidoException e) {
            assertEquals("O curso deve ser informada.", e.getMessage());
        }
    }
    
    @Test
    public void testSituacao() throws ConvenioInvalidoException, SQLException {
        Convenio c = new Convenio();
        c.setCurso(Curso.buscarPorId(1));
        c.setEmpresa(Empresa.buscarPorId(1));
        try {
            c.salvar();
            fail("Salvou convenio sem situacao");
        }catch(ConvenioInvalidoException e) {
            assertEquals("A situação deve ser informada.", e.getMessage());
        }
    }
    
    @Test
    public void testDataAssinatura() throws ConvenioInvalidoException, SQLException {
        Convenio c = new Convenio();
        c.setCurso(Curso.buscarPorId(1));
        c.setEmpresa(Empresa.buscarPorId(1));
        c.setSituacao(SituacaoConvenio.ANDAMENTO);  
        c.setDataVencimento(new GregorianCalendar(2016, Calendar.NOVEMBER, 4));
        try {
            c.salvar();
            fail("Salvou convenio sem data assinatura");
        }catch(ConvenioInvalidoException e) {
            assertEquals("A data da assinatura de ser informada.", e.getMessage());
        }
    }
    
     @Test
    public void testDataVencimento() throws ConvenioInvalidoException, SQLException {
        Convenio c = new Convenio();
        c.setCurso(Curso.buscarPorId(1));
        c.setSituacao(SituacaoConvenio.ANDAMENTO);  
        c.setEmpresa(Empresa.buscarPorId(1));
        c.setDataAssinatura(new GregorianCalendar(2016, Calendar.JANUARY, 4));
        try {
            c.salvar();
            fail("Salvou convenio sem vencimento");
        }catch(ConvenioInvalidoException e) {
            assertEquals("A data de vencimento de ser informada.", e.getMessage());
        }
    }
    
    @Test
    public void testDataAssinaturaMaiorDataVencimento() throws ConvenioInvalidoException, SQLException {
        Convenio c = new Convenio();
        c.setCurso(Curso.buscarPorId(1));
        c.setSituacao(SituacaoConvenio.ANDAMENTO);  
        c.setEmpresa(Empresa.buscarPorId(1));
        c.setDataAssinatura(new GregorianCalendar(2017, Calendar.JANUARY, 4));
        c.setDataVencimento(new GregorianCalendar(2016, Calendar.NOVEMBER, 4));
        try {
            c.salvar();
            fail("Salvou convenio com data de assinatura maior que a data de vencimento.");
        }catch(ConvenioInvalidoException e) {
            assertEquals("Data de vencimento menor que a data de assinatura.", e.getMessage());
        }
    }
    
    /**
     * Test of buscarPorId method, of class Empresa.
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscarPorId() throws Exception {        
        Convenio c = Convenio.buscarPorId(1);     
        validarConvenioPadrao(c);
    }

    /**
     * Test of buscarTodos method, of class Empresa.     
     * @throws java.sql.SQLException
    */ 
    @Test
    public void testBuscarPorCurso() throws SQLException {
        List<Convenio> c = Convenio.buscarPorCurso(1);
        assertEquals(1, c.size());
        validarConvenioPadrao(c.get(0));
    }
    
    /**
     * Test of buscarTodos method, of class Empresa.     
     * @throws java.sql.SQLException
    */ 
    @Test
    public void testBuscarTodos() throws SQLException {
        List<Convenio> c = Convenio.buscarTodos();
        assertEquals(1, c.size());
        validarConvenioPadrao(c.get(0));
    }
    
    /**
     * Test of buscarTodos method, of class Empresa.     
     * @throws java.sql.SQLException
    */ 
    @Test
    public void testBuscarTodosComFiltro01() throws SQLException {
        List<Convenio> c = Convenio.buscarTodos(null, null, null, null, 
                "16.521.155/0001-03", SituacaoConvenio.CANCELADO, "direi");
        assertEquals(1, c.size());
        validarConvenioPadrao(c.get(0));
    }
    
    /**
     * Test of buscarTodos method, of class Empresa.     
     * @throws java.sql.SQLException
    */ 
    @Test
    public void testBuscarTodosComFiltro02() throws SQLException {        
        Calendar inicio = new GregorianCalendar(2017, Calendar.OCTOBER, 31);
        Calendar fim = new GregorianCalendar(2017, Calendar.NOVEMBER, 2);
                
        List<Convenio> c = Convenio.buscarTodos(inicio, fim, null, null, 
                "16.521.155/0001-03", SituacaoConvenio.CANCELADO, "direi");
        assertEquals(1, c.size());
        validarConvenioPadrao(c.get(0));
    }
    
    /**
     * Test of buscarTodos method, of class Empresa.     
     * @throws java.sql.SQLException
    */ 
    @Test
    public void testBuscarTodosComFiltro03() throws SQLException {        
        Calendar inicio = new GregorianCalendar(2016, Calendar.NOVEMBER, 5);
        Calendar fim = new GregorianCalendar(2016, Calendar.NOVEMBER, 30);
                
        List<Convenio> c = Convenio.buscarTodos(null, null, inicio, fim, 
                "16.521.155/0001-03", SituacaoConvenio.CANCELADO, "direi");
        assertEquals(1, c.size());
        validarConvenioPadrao(c.get(0));
    }
    
    public static void validarConvenioPadrao(Convenio c) throws SQLException {
        assertEquals(Empresa.buscarPorId(1), c.getEmpresa());
        assertEquals(Curso.buscarPorId(1), c.getCurso());
        assertEquals(SituacaoConvenio.CANCELADO, c.getSituacao());
        assertEquals(new GregorianCalendar(2017, Calendar.NOVEMBER, 1), c.getDataVencimento());
        assertEquals(new GregorianCalendar(2016, Calendar.NOVEMBER, 19), c.getDataAssinatura());
    }
}
