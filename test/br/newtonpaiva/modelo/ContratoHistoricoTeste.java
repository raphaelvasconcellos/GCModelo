/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excessoes.AlunoInvalidoException;
import br.newtonpaiva.modelo.excessoes.ContratoHistoricoInvalidoException;
import br.newtonpaiva.modelo.excessoes.ContratoInvalidoException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author claudio
 */
public class ContratoHistoricoTeste {
    
    /**
     * Test of salvar method, of class Contrato Historico.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSalvar() throws SQLException, ContratoHistoricoInvalidoException{
        String dateStr = "10/10/2016";
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = null;
        try {
            dateObj = (Date) curFormater.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(ConvenioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar dataTeste= Calendar.getInstance();
        dataTeste.setTime(dateObj);  
        
        ContratoHistorico a = new ContratoHistorico();
        a.setContrato(new Contrato().buscarPorId(31));        
        a.setSituacao(Situacao.ANDAMENTO);
        a.setData(dataTeste);
        a.setObservacao("Teste histórico");
        System.out.println(a.toString());
        
        try {            
            a.salvar();
            Assert.assertNotNull(a.getId());     
            int numLinhasExcluidas = ContratoHistorico.excluir(a.getId());
            Assert.assertEquals(1, numLinhasExcluidas);
            
        } catch (SQLException ex) {
            throw (new ContratoHistoricoInvalidoException(ex.getMessage()));      
        }
    }
    
    
    /**
     * Test of buscarPorId method, of class Aluno.
     * @throws java.lang.Exception
     */
    @Test
    public void testBuscarPorIdContrato() throws Exception {
        List<ContratoHistorico> a = ContratoHistorico.buscarPorIdContrato(31);
        for(Object i : a)
            System.out.println(i);
               
    }
    
    
}
