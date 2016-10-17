/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excessoes.ContratoInvalidoException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
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
public class ContratoTest {

    /**
     * Testar quantidade de horas semanais maior que 30.
     * @throws java.sql.SQLException
     * @throws br.newtonpaiva.modelo.excessoes.ContratoInvalidoException
     */
    @Test(expected = ContratoInvalidoException.class)
    public void testeMaisQue30HorasSemanais() throws SQLException, ContratoInvalidoException {
        String dateStr = "01/01/2017";
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = null;
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

        Contrato a = new Contrato();
        a.setAluno(Aluno.buscarPorId(3));
        a.setEmpresa(Empresa.buscarPorId(1));
        a.setTipo(TipoContrato.OBRIGATORIO);
        a.setSituacaoAtual(Situacao.ANDAMENTO);
        a.setNumProtocolo("123456");
        a.setDataEntrada(dataInicio);
        a.setDataInicio(dataInicio);
        a.setDataTermino(dataTermino);
        a.setValorBolsa(BigDecimal.valueOf(300.0));
        a.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        a.setValorCargaHorariaDiaria(BigDecimal.valueOf(6.0));
        a.setValorCargaHorariaSemanal(BigDecimal.valueOf(36.0));
        a.setObservacao("Teste");
        a.setIndAlunoContratado("N");

        try {
            a.salvar(true);           
        } catch (ContratoInvalidoException | SQLException ex) {
            throw (new ContratoInvalidoException(ex.getMessage()));
        }
    }

    /**
     * Testar quantidade de horas diarias maior que 6.
     * @throws java.sql.SQLException
     * @throws br.newtonpaiva.modelo.excessoes.ContratoInvalidoException
     */
    @Test(expected = ContratoInvalidoException.class)
    public void testeMaisQue6HorasDiarias() throws SQLException, ContratoInvalidoException {
        String dateStr = "01/01/2017";
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = null;
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

        Contrato a = new Contrato();
        a.setAluno(Aluno.buscarPorId(3));
        a.setEmpresa(Empresa.buscarPorId(1));
        a.setTipo(TipoContrato.OBRIGATORIO);
        a.setSituacaoAtual(Situacao.ANDAMENTO);
        a.setNumProtocolo("123456");
        a.setDataEntrada(dataInicio);
        a.setDataInicio(dataInicio);
        a.setDataTermino(dataTermino);
        a.setValorBolsa(BigDecimal.valueOf(300.0));
        a.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        a.setValorCargaHorariaDiaria(BigDecimal.valueOf(8.0));
        a.setValorCargaHorariaSemanal(BigDecimal.valueOf(30.0));
        a.setObservacao("Teste");
        a.setIndAlunoContratado("N");

        try {
            a.salvar(true);           
        } catch (ContratoInvalidoException | SQLException ex) {
            throw (new ContratoInvalidoException(ex.getMessage()));
        }

    }

    /**
     * Testar quantidade de horas semanais maior que 30 Aula Prática.
     * @throws java.sql.SQLException
     * @throws br.newtonpaiva.modelo.excessoes.ContratoInvalidoException
     */
    @Test
    public void testeMaisQue30HorasSemanaisPratica() throws SQLException, ContratoInvalidoException {
        String dateStr = "01/01/2017";
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = null;
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

        Contrato a = new Contrato();
        a.setAluno(Aluno.buscarPorId(3));
        a.setEmpresa(Empresa.buscarPorId(1));
        a.setTipo(TipoContrato.OBRIGATORIO);
        a.setSituacaoAtual(Situacao.ANDAMENTO);
        a.setNumProtocolo("123456");
        a.setDataEntrada(dataInicio);
        a.setDataInicio(dataInicio);
        a.setDataTermino(dataTermino);
        a.setValorBolsa(BigDecimal.valueOf(300.0));
        a.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        a.setValorCargaHorariaDiaria(BigDecimal.valueOf(6.0));
        a.setValorCargaHorariaSemanal(BigDecimal.valueOf(36.0));
        a.setObservacao("Teste");
        a.setIndAlunoContratado("N");

        try {
            a.salvar(false);  
            Assert.assertNotNull(a.getId());
            int numLinhasExcluidas = Contrato.excluir(a.getId());
            Assert.assertEquals(1, numLinhasExcluidas);

        } catch (ContratoInvalidoException | SQLException ex) {
            throw (new ContratoInvalidoException(ex.getMessage()));
        }

    }

    /**
     * Testar quantidade de horas diarias maior que 6 Aula Prática.
     */
    @Test
    public void testeMaisQue6HorasDiariasPratica() throws SQLException, ContratoInvalidoException {
        String dateStr = "01/01/2017";
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = null;
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

        Contrato a = new Contrato();
        a.setAluno(Aluno.buscarPorId(3));
        a.setEmpresa(Empresa.buscarPorId(1));
        a.setTipo(TipoContrato.OBRIGATORIO);
        a.setSituacaoAtual(Situacao.ANDAMENTO);
        a.setNumProtocolo("123456");
        a.setDataEntrada(dataInicio);
        a.setDataInicio(dataInicio);
        a.setDataTermino(dataTermino);
        a.setValorBolsa(BigDecimal.valueOf(300.0));
        a.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        a.setValorCargaHorariaDiaria(BigDecimal.valueOf(8.0));
        a.setValorCargaHorariaSemanal(BigDecimal.valueOf(30.0));
        a.setObservacao("Teste");
        a.setIndAlunoContratado("N");

        try {
            a.salvar(false);  
            Assert.assertNotNull(a.getId());
            int numLinhasExcluidas = Contrato.excluir(a.getId());
            Assert.assertEquals(1, numLinhasExcluidas);

        } catch (ContratoInvalidoException | SQLException ex) {
            throw (new ContratoInvalidoException(ex.getMessage()));
        }

    }

    /**
     * Testar prazo maior que dois anos para nao deficiente.
     * @throws java.sql.SQLException
     * @throws br.newtonpaiva.modelo.excessoes.ContratoInvalidoException
     */
    @Test(expected = ContratoInvalidoException.class)
    public void testeMaisDoisAnos() throws SQLException, ContratoInvalidoException {
        String dateStr = "01/01/2017";
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = null;
        try {
            dateObj = (Date) curFormater.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(ConvenioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar dataInicio = Calendar.getInstance();
        dataInicio.setTime(dateObj);

        dateStr = "01/01/2020";
        dateObj = null;
        try {
            dateObj = (Date) curFormater.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(ConvenioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar dataTermino = Calendar.getInstance();
        dataTermino.setTime(dateObj);

        Contrato a = new Contrato();
        a.setAluno(Aluno.buscarPorId(3));
        a.setEmpresa(Empresa.buscarPorId(1));
        a.setTipo(TipoContrato.OBRIGATORIO);
        a.setSituacaoAtual(Situacao.ANDAMENTO);
        a.setNumProtocolo("123456");
        a.setDataEntrada(dataInicio);
        a.setDataInicio(dataInicio);
        a.setDataTermino(dataTermino);
        a.setValorBolsa(BigDecimal.valueOf(300.0));
        a.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        a.setValorCargaHorariaDiaria(BigDecimal.valueOf(6.0));
        a.setValorCargaHorariaSemanal(BigDecimal.valueOf(30.0));
        a.setObservacao("Teste");
        a.setIndAlunoContratado("N");

        try {
            a.salvar(true);           
        } catch (ContratoInvalidoException | SQLException ex) {
           throw (new ContratoInvalidoException(ex.getMessage()));
        }

    }

    /**
     * Testar prazo maior que dois anos para deficiente.
     *
     * @throws java.sql.SQLException
     * @throws br.newtonpaiva.modelo.excessoes.ContratoInvalidoException
     */
    @Test
    public void testeMaisDoisAnosDeficiente() throws SQLException, ContratoInvalidoException {
        String dateStr = "01/01/2017";
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = null;
        try {
            dateObj = (Date) curFormater.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(ConvenioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar dataInicio = Calendar.getInstance();
        dataInicio.setTime(dateObj);

        dateStr = "01/01/2020";
        dateObj = null;
        try {
            dateObj = (Date) curFormater.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(ConvenioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar dataTermino = Calendar.getInstance();
        dataTermino.setTime(dateObj);

        Contrato a = new Contrato();
        a.setAluno(Aluno.buscarPorId(8));
        a.setEmpresa(Empresa.buscarPorId(1));
        a.setTipo(TipoContrato.OBRIGATORIO);
        a.setSituacaoAtual(Situacao.ANDAMENTO);
        a.setNumProtocolo("123456");
        a.setDataEntrada(dataInicio);
        a.setDataInicio(dataInicio);
        a.setDataTermino(dataTermino);
        a.setValorBolsa(BigDecimal.valueOf(300.0));
        a.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        a.setValorCargaHorariaDiaria(BigDecimal.valueOf(6.0));
        a.setValorCargaHorariaSemanal(BigDecimal.valueOf(30.0));
        a.setObservacao("Teste");
        a.setIndAlunoContratado("N");

        try {
            a.salvar(true);
            Assert.assertNotNull(a.getId());
            int numLinhasExcluidas = Contrato.excluir(a.getId());
            Assert.assertEquals(1, numLinhasExcluidas);

        } catch (ContratoInvalidoException | SQLException ex) {
            throw (new ContratoInvalidoException(ex.getMessage()));
        }

    }
    
    

    /**
     * Test of salvar method, of class Contrato.
     *
     * @throws java.sql.SQLException
     * @throws br.newtonpaiva.modelo.excessoes.ContratoInvalidoException
     */
    @Test
    public void testSalvar() throws SQLException, ContratoInvalidoException {
        String dateStr = "01/01/2017";
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = null;
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

        Contrato a = new Contrato();
        a.setAluno(Aluno.buscarPorId(3));
        a.setEmpresa(Empresa.buscarPorId(1));
        a.setTipo(TipoContrato.OBRIGATORIO);
        a.setSituacaoAtual(Situacao.ANDAMENTO);
        a.setNumProtocolo("123456");
        a.setDataEntrada(dataInicio);
        a.setDataInicio(dataInicio);
        a.setDataTermino(dataTermino);
        a.setValorBolsa(BigDecimal.valueOf(300.0));
        a.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        a.setValorCargaHorariaDiaria(BigDecimal.valueOf(6.0));
        a.setValorCargaHorariaSemanal(BigDecimal.valueOf(30.0));
        a.setObservacao("Teste");
        a.setIndAlunoContratado("N");

        try {
            System.out.println(a.toString());
            a.salvar(true);
            Assert.assertNotNull(a.getId());

            dateStr = "25/01/2017";
            dateObj = null;
            try {
                dateObj = (Date) curFormater.parse(dateStr);
            } catch (ParseException ex) {
                Logger.getLogger(ConvenioTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            Calendar dataRescicao = Calendar.getInstance();
            dataRescicao.setTime(dateObj);

            a.setSituacaoAtual(Situacao.CANCELADO);
            a.setDataRescisao(dataRescicao);
            a.salvar(true);
            int numLinhasExcluidas = Contrato.excluir(a.getId());
            Assert.assertEquals(1, numLinhasExcluidas);

        } catch (ContratoInvalidoException | SQLException ex) {
           throw (new ContratoInvalidoException(ex.getMessage()));          
        }
    }
    
    
    @Test(expected = ContratoInvalidoException.class)
    public void testAluno() throws SQLException, ContratoInvalidoException {
        String dateStr = "01/01/2017";
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = null;
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

        Contrato a = new Contrato();
        //a.setAluno(Aluno.buscarPorId(3));
        a.setEmpresa(Empresa.buscarPorId(1));
        a.setTipo(TipoContrato.OBRIGATORIO);
        a.setSituacaoAtual(Situacao.ANDAMENTO);
        a.setNumProtocolo("123456");
        a.setDataEntrada(dataInicio);
        a.setDataInicio(dataInicio);
        a.setDataTermino(dataTermino);
        a.setValorBolsa(BigDecimal.valueOf(300.0));
        a.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        a.setValorCargaHorariaDiaria(BigDecimal.valueOf(6.0));
        a.setValorCargaHorariaSemanal(BigDecimal.valueOf(30.0));
        a.setObservacao("Teste");
        a.setIndAlunoContratado("N");

        try {
            a.salvar(true);

        } catch (ContratoInvalidoException | SQLException ex) {
            throw (new ContratoInvalidoException(ex.getMessage()));
        }
    }

    @Test(expected = ContratoInvalidoException.class)
    public void testEmpresa() throws SQLException, ContratoInvalidoException {
        String dateStr = "01/01/2017";
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = null;
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

        Contrato a = new Contrato();
        a.setAluno(Aluno.buscarPorId(3));
        //a.setEmpresa(Empresa.buscarPorId(1));
        a.setTipo(TipoContrato.OBRIGATORIO);
        a.setSituacaoAtual(Situacao.ANDAMENTO);
        a.setNumProtocolo("123456");
        a.setDataEntrada(dataInicio);
        a.setDataInicio(dataInicio);
        a.setDataTermino(dataTermino);
        a.setValorBolsa(BigDecimal.valueOf(300.0));
        a.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        a.setValorCargaHorariaDiaria(BigDecimal.valueOf(6.0));
        a.setValorCargaHorariaSemanal(BigDecimal.valueOf(30.0));
        a.setObservacao("Teste");
        a.setIndAlunoContratado("N");

        try {
            a.salvar(true);

        } catch (ContratoInvalidoException | SQLException ex) {
            throw new ContratoInvalidoException(ex.getMessage());
        }
    }

    @Test(expected = ContratoInvalidoException.class)
    public void testSituacao() throws SQLException, ContratoInvalidoException {
        String dateStr = "01/01/2017";
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = null;
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

        Contrato a = new Contrato();
        a.setAluno(Aluno.buscarPorId(3));
        a.setEmpresa(Empresa.buscarPorId(1));
        a.setTipo(TipoContrato.OBRIGATORIO);
        //a.setSituacaoAtual(Situacao.ANDAMENTO);
        a.setNumProtocolo("123456");
        a.setDataEntrada(dataInicio);
        a.setDataInicio(dataInicio);
        a.setDataTermino(dataTermino);
        a.setValorBolsa(BigDecimal.valueOf(300.0));
        a.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        a.setValorCargaHorariaDiaria(BigDecimal.valueOf(6.0));
        a.setValorCargaHorariaSemanal(BigDecimal.valueOf(30.0));
        a.setObservacao("Teste");
        a.setIndAlunoContratado("N");

        try {
            a.salvar(true);

        } catch (ContratoInvalidoException | SQLException ex) {
            throw new ContratoInvalidoException(ex.getMessage());
        }
    }
    
    @Test
    public void testBuscarPorId() throws Exception {
        
        Contrato u = new Contrato().buscarPorId(1);       
        System.out.println(u.toString());
               
    }
    
    @Test
    public void testBuscarIdAluno() throws SQLException {
        List<Contrato> u = new Contrato().buscarPorIdAluno(3);
        for(Object i : u)
            System.out.println(i);
    }
    
    @Test
    public void testBuscarTodos() throws SQLException {
        List<Contrato> u = new Contrato().buscarTodos();
        for(Object i : u)
            System.out.println(i);
    }
    
    @Test
    public void testAnexarDocumento()throws SQLException, ContratoInvalidoException, FileNotFoundException {
        Contrato c = new Contrato().buscarPorId(31);
        
        try {
            c.anexarDocumento("C:\teste.txt");
        } catch (SQLException ex) {
            throw new ContratoInvalidoException(ex.getMessage());
        }
        
    }
    
    @Test
    public void testBaixarDocumento()throws SQLException, ContratoInvalidoException, FileNotFoundException, IOException {
                
        try {
            Contrato.baixarDocumento(31, "C:\teste.txt");
        } catch (SQLException ex) {
            throw new ContratoInvalidoException(ex.getMessage());
        }
        
    }
        
        

    

}
