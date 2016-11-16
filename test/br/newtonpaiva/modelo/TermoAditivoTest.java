/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excessoes.AlunoInvalidoException;
import br.newtonpaiva.modelo.excessoes.TermoAditivoInvalidoException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Tarley Lana
 */
public class TermoAditivoTest {
    
    /**
     * Test of salvar method, of class Termo Aditivo.
     * @throws java.sql.SQLException
     * @throws br.newtonpaiva.modelo.excessoes.TermoAditivoInvalidoException
     */
    @Test
    public void testSalvar() throws SQLException, TermoAditivoInvalidoException {
        String dateStr = "01/01/2017";
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = null;
        try {
            dateObj = (Date) curFormater.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(ConvenioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar dataTermo = Calendar.getInstance();
        dataTermo.setTime(dateObj);
        
        dateStr = "01/01/2017";
        dateObj = null;
        try {
            dateObj = (Date) curFormater.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(ConvenioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar dataInicio = Calendar.getInstance();
        dataInicio.setTime(dateObj);
        
        dateStr = "01/03/2017";
        dateObj = null;
        try {
            dateObj = (Date) curFormater.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(ConvenioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar dataTermino = Calendar.getInstance();
        dataTermino.setTime(dateObj);
        
        TermoAditivo a = new TermoAditivo();        
        a.setContrato(new Contrato().buscarPorId(31));
        a.setData(dataTermo);
        a.setDataInicio(dataInicio);
        a.setDataTemino(dataTermino);
        a.setObservacao("Teste");
        
        try {            
            a.salvar();
            Assert.assertNotNull(a.getId());
            a.setObservacao("Einderson Novo");
            a.salvar();
           int numLinhasExcluidas = TermoAditivo.excluir(a.getId());
            Assert.assertEquals(1, numLinhasExcluidas);
            
        } catch (SQLException ex) {
            throw (new TermoAditivoInvalidoException(ex.getMessage()));
        }
    }
    
    @Test
    public void testBuscarPorId() throws Exception {
        
        TermoAditivo u = TermoAditivo.buscarPorId(1);       
        System.out.println(u.toString());
               
    }
    
    @Test
    public void testBuscarTodos() throws SQLException {
        List<TermoAditivo> u = TermoAditivo.buscarPorIdContrato(31);
        for(Object i : u)
            System.out.println(i);
    }
    
    @Test
    public void testAnexarDocumento()throws SQLException, TermoAditivoInvalidoException, IOException {
        TermoAditivo c = new TermoAditivo().buscarPorId(1);
        
        try {
            c.anexar("C:\teste.txt");
        } catch (SQLException ex) {
            throw new TermoAditivoInvalidoException(ex.getMessage());
        }
        
    }
    
    @Test
    public void testBaixarDocumento()throws SQLException, TermoAditivoInvalidoException, IOException {
                
        try {
            TermoAditivo.baixar(31, "C:\teste.txt");
        } catch (SQLException ex) {
            throw new TermoAditivoInvalidoException(ex.getMessage());
        }
        
    }
}
