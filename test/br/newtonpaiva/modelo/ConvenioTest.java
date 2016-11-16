package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excessoes.ConvenioInvalidoException;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Lucas Fornero
 */
public class ConvenioTest {
       

    @Test
    // Salvando novo convenio
    public void testSalvar() throws SQLException {
        
        String dateStr = "04/05/2016"; 
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy"); 
        Date dateObj = null; 
        try {
            dateObj = (Date) curFormater.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(ConvenioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar dataAssinatura = Calendar.getInstance();
        dataAssinatura.setTime(dateObj);
        
        dateStr = "04/11/2016";         
        dateObj = null; 
        try {
            dateObj = (Date) curFormater.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(ConvenioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar dataVencimento = Calendar.getInstance();
        dataVencimento.setTime(dateObj);
        
        Convenio u = new Convenio();
        u.setCurso(CursoEnum.DIREITO);
        u.setEmpresa(Empresa.buscarPorId(1));
        u.setSituacao(SituacaoConvenio.ANDAMENTO);
        u.setDataAssinatura(dataAssinatura);
        u.setDataVencimento(dataVencimento);
        
        try {
            // Inserir
            u.salvar();            
            Assert.assertNotNull(u.getId());
            
            // Atualizar
            u.setCurso(CursoEnum.ENFERMAGEM);            
            u.salvar();
            
            int numLinhasExcluidas = new Convenio().excluir(u.getId());
            Assert.assertEquals(1, numLinhasExcluidas);
        } catch (ConvenioInvalidoException | SQLException ex) {
            Assert.fail(ex.getMessage());
        }
    }
    @Test(expected = ConvenioInvalidoException.class)
    public void testEmpresa() throws ConvenioInvalidoException, SQLException {
        Convenio u = new Convenio();
        u.setCurso(CursoEnum.DIREITO);
//        u.setEmpresa(Empresa.buscarPorId(1));
        u.setSituacao(SituacaoConvenio.ANDAMENTO);        
        
        try {            
            u.salvar();            
        } catch(ConvenioInvalidoException | SQLException ex){           
            throw (new ConvenioInvalidoException(ex.getMessage()));
        }
    }
    
    
    @Test(expected = ConvenioInvalidoException.class)
    public void testSituacao() throws ConvenioInvalidoException, SQLException {
        Convenio u = new Convenio();
        u.setCurso(CursoEnum.DIREITO);
        u.setEmpresa(Empresa.buscarPorId(1));
//        u.setSituacao(SituacaoConvenio.ANDAMENTO);        
        
        try {            
            u.salvar();            
        } catch(ConvenioInvalidoException | SQLException ex){           
            throw (new ConvenioInvalidoException(ex.getMessage()));
        }
    }
    
    /**
     * Test of buscarPorId method, of class Empresa.
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscarPorId() throws Exception {
        
        Convenio u = Convenio.buscarPorId(1);       
        System.out.println(u.toString());
               
    }

    /**
     * Test of buscarTodos method, of class Empresa.     
     * @throws java.sql.SQLException
    */ 
    @Test
    public void testBuscarTodos() throws SQLException {
        List<Convenio> u = Convenio.buscarTodos();
        for(Object i : u)
            System.out.println(i);
    }
    
}
