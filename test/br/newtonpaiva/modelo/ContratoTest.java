/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excessoes.ContratoInvalidoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;


/**
 *
 * @author Tarley Lana
 */
public class ContratoTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();
            
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        
    }
    
    @After
    public void tearDown() {
        
    }
    
    /**
     * Testar quantidade de horas semanais maior que 30.
     *
     * @throws java.sql.SQLException
     * @throws br.newtonpaiva.modelo.excessoes.ContratoInvalidoException
     * @throws java.text.ParseException
     */
    public void testeMaisQue30HorasSemanais() throws SQLException, ContratoInvalidoException, ParseException {
        Contrato c = new Contrato();
        c.setAluno(Aluno.buscarPorId(1));
        c.setEmpresa(Empresa.buscarPorId(1));
        c.setTipo(TipoContrato.OBRIGATORIO);
        c.setSituacaoAtual(SituacaoContrato.ANDAMENTO);
        c.setNumProtocolo("123456");
        c.setDataEntrada(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataInicio(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataTermino(new GregorianCalendar(2017, Calendar.MARCH, 1));
        c.setValorBolsa(BigDecimal.valueOf(300.0));
        c.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        c.setValorCargaHorariaDiaria(BigDecimal.valueOf(6.0));
        c.setValorCargaHorariaSemanal(BigDecimal.valueOf(36.0));
        c.setObservacao("Teste");
        c.setIndAlunoContratado("N");
        
        try {
            c.salvar();
        } catch(ContratoInvalidoException e) {
            assertEquals("A carga horária semanal nao deve exceder 30 horas", e.getMessage());
        }
    }

    public void testeMaisQue6HorasDiarias() throws SQLException, ContratoInvalidoException, ParseException {
        Contrato c = new Contrato();
        c.setAluno(Aluno.buscarPorId(1));
        c.setEmpresa(Empresa.buscarPorId(1));
        c.setTipo(TipoContrato.OBRIGATORIO);
        c.setSituacaoAtual(SituacaoContrato.ANDAMENTO);
        c.setNumProtocolo("123456");
        c.setDataEntrada(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataInicio(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataTermino(new GregorianCalendar(2017, Calendar.MARCH, 1));
        c.setValorBolsa(BigDecimal.valueOf(300.0));
        c.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        c.setValorCargaHorariaDiaria(BigDecimal.valueOf(8.0));
        c.setValorCargaHorariaSemanal(BigDecimal.valueOf(30.0));
        c.setObservacao("Teste");
        c.setIndAlunoContratado("N");
        
        try {
            c.salvar();
        }catch(ContratoInvalidoException e) {
            assertEquals("A carga horária diária não deve exceder 6 horas", e.getMessage());
        }
    }

    /**
     * Testar prazo maior que dois anos para nao deficiente.
     *
     * @throws java.sql.SQLException
     * @throws br.newtonpaiva.modelo.excessoes.ContratoInvalidoException
     * @throws java.text.ParseException
     */
    @Test
    public void testeMaisDoisAnosParaAlunoNaoDeficiente() throws SQLException, ContratoInvalidoException, ParseException {
        Contrato c = new Contrato();
        c.setAluno(Aluno.buscarPorId(1));
        c.setEmpresa(Empresa.buscarPorId(1));
        c.setTipo(TipoContrato.OBRIGATORIO);
        c.setSituacaoAtual(SituacaoContrato.ANDAMENTO);
        c.setNumProtocolo("123456");
        c.setDataEntrada(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataInicio(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataTermino(new GregorianCalendar(2019, Calendar.JANUARY, 2));
        c.setValorBolsa(BigDecimal.valueOf(300.0));
        c.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        c.setValorCargaHorariaDiaria(BigDecimal.valueOf(2.0));
        c.setValorCargaHorariaSemanal(BigDecimal.valueOf(20.0));
        c.setObservacao("Teste");
        c.setIndAlunoContratado("N");
        
        try {
            c.salvar();
        }catch(ContratoInvalidoException e) {
            assertEquals("O aluno não pode ter mais que dois anos de contrato na mesma empresa", e.getMessage());
        }
    }

    /**
     * Testar prazo maior que dois anos para deficiente.
     *
     * @throws java.sql.SQLException
     * @throws br.newtonpaiva.modelo.excessoes.ContratoInvalidoException
     * @throws java.text.ParseException
     */
    @Test
    public void testeMaisDoisAnosDeficiente() throws SQLException, ContratoInvalidoException, ParseException {
        Contrato c = new Contrato();
        c.setAluno(Aluno.buscarPorId(2));
        c.setEmpresa(Empresa.buscarPorId(1));
        c.setTipo(TipoContrato.OBRIGATORIO);
        c.setSituacaoAtual(SituacaoContrato.ANDAMENTO);
        c.setNumProtocolo("123456");
        c.setDataEntrada(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataInicio(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataTermino(new GregorianCalendar(2019, Calendar.JANUARY, 2));
        c.setValorBolsa(BigDecimal.valueOf(300.0));
        c.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        c.setValorCargaHorariaDiaria(BigDecimal.valueOf(6.0));
        c.setValorCargaHorariaSemanal(BigDecimal.valueOf(30.0));
        c.setObservacao("Teste");
        c.setIndAlunoContratado("N");

        c.salvar();
        assertNotNull(c.getId());
        
        int numLinhasExcluidas = Contrato.excluir(c.getId());
        assertEquals(1, numLinhasExcluidas);
    }

    /**
     * Test of salvar method, of class Contrato.
     *
     * @throws java.sql.SQLException
     * @throws br.newtonpaiva.modelo.excessoes.ContratoInvalidoException
     */
    @Test
    public void testSalvarSucesso() throws SQLException, ContratoInvalidoException {
        Contrato c = new Contrato();
        c.setAluno(Aluno.buscarPorId(1));
        c.setEmpresa(Empresa.buscarPorId(1));
        c.setTipo(TipoContrato.OBRIGATORIO);
        c.setSituacaoAtual(SituacaoContrato.ANDAMENTO);
        c.setNumProtocolo("123456");
        c.setDataEntrada(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataInicio(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataTermino(new GregorianCalendar(2017, Calendar.MARCH, 1));
        c.setValorBolsa(BigDecimal.valueOf(300.0));
        c.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        c.setValorCargaHorariaDiaria(BigDecimal.valueOf(2.0));
        c.setValorCargaHorariaSemanal(BigDecimal.valueOf(20.0));
        c.setObservacao("Teste");
        c.setIndAlunoContratado("N");

        c.salvar();
        assertNotNull(c.getId());

        c.setSituacaoAtual(SituacaoContrato.CANCELADO);
        c.setDataRescisao(new GregorianCalendar(2017, Calendar.FEBRUARY, 1));
        c.salvar();
        
        int numLinhasExcluidas = Contrato.excluir(c.getId());
        assertEquals(1, numLinhasExcluidas);
    }

    @Test
    public void testAlunoNaoInformado() throws SQLException, ContratoInvalidoException {
        Contrato c = new Contrato();
        //a.setAluno(Aluno.buscarPorId(1));
        c.setEmpresa(Empresa.buscarPorId(1));
        c.setTipo(TipoContrato.OBRIGATORIO);
        c.setSituacaoAtual(SituacaoContrato.ANDAMENTO);
        c.setNumProtocolo("123456");
        c.setDataEntrada(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataInicio(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataTermino(new GregorianCalendar(2018, Calendar.JANUARY, 1));
        c.setValorBolsa(BigDecimal.valueOf(300.0));
        c.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        c.setValorCargaHorariaDiaria(BigDecimal.valueOf(6.0));
        c.setValorCargaHorariaSemanal(BigDecimal.valueOf(30.0));
        c.setObservacao("Teste");
        c.setIndAlunoContratado("N");

        try {
            c.salvar();
        } catch (ContratoInvalidoException e) {
            assertEquals("Aluno do contrato não informado", e.getMessage());
        }
    }

    @Test
    public void testEmpresaNaoInformada() throws SQLException, ContratoInvalidoException {
        Contrato c = new Contrato();
        c.setAluno(Aluno.buscarPorId(1));
        //a.setEmpresa(Empresa.buscarPorId(1));
        c.setTipo(TipoContrato.OBRIGATORIO);
        c.setSituacaoAtual(SituacaoContrato.ANDAMENTO);
        c.setNumProtocolo("123456");
        c.setDataEntrada(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataInicio(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataTermino(new GregorianCalendar(2018, Calendar.JANUARY, 1));
        c.setValorBolsa(BigDecimal.valueOf(300.0));
        c.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        c.setValorCargaHorariaDiaria(BigDecimal.valueOf(6.0));
        c.setValorCargaHorariaSemanal(BigDecimal.valueOf(30.0));
        c.setObservacao("Teste");
        c.setIndAlunoContratado("N");

        try {
            c.salvar();
        } catch (ContratoInvalidoException e) {
            assertEquals("Empresa do contrato não informada", e.getMessage());
        }
    }

    @Test
    public void testSituacaoNaoInformada() throws SQLException, ContratoInvalidoException {
        Contrato c = new Contrato();
        c.setAluno(Aluno.buscarPorId(1));
        c.setEmpresa(Empresa.buscarPorId(1));
        c.setTipo(TipoContrato.OBRIGATORIO);
        //a.setSituacaoAtual(SituacaoContrato.ANDAMENTO);
        c.setNumProtocolo("123456");
        c.setDataEntrada(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataInicio(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataTermino(new GregorianCalendar(2018, Calendar.JANUARY, 1));
        c.setValorBolsa(BigDecimal.valueOf(300.0));
        c.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        c.setValorCargaHorariaDiaria(BigDecimal.valueOf(6.0));
        c.setValorCargaHorariaSemanal(BigDecimal.valueOf(30.0));
        c.setObservacao("Teste");
        c.setIndAlunoContratado("N");

        try {
            c.salvar();
        } catch (ContratoInvalidoException e) {
            assertEquals("Situação do contrato não informada", e.getMessage());
        }
    }

    @Test
    public void testTipoNaoInformado() throws SQLException, ContratoInvalidoException {
        Contrato c = new Contrato();
        c.setAluno(Aluno.buscarPorId(1));
        c.setEmpresa(Empresa.buscarPorId(1));
        //c.setTipo(TipoContrato.OBRIGATORIO);
        c.setSituacaoAtual(SituacaoContrato.ANDAMENTO);
        c.setNumProtocolo("123456");
        c.setDataEntrada(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataInicio(new GregorianCalendar(2017, Calendar.JANUARY, 1));
        c.setDataTermino(new GregorianCalendar(2018, Calendar.JANUARY, 1));
        c.setValorBolsa(BigDecimal.valueOf(300.0));
        c.setAuxilioTransporte(BigDecimal.valueOf(80.0));
        c.setValorCargaHorariaDiaria(BigDecimal.valueOf(6.0));
        c.setValorCargaHorariaSemanal(BigDecimal.valueOf(30.0));
        c.setObservacao("Teste");
        c.setIndAlunoContratado("N");

        try {
            c.salvar();
        } catch (ContratoInvalidoException e) {
            assertEquals("Tipo do contrato não informado", e.getMessage());
        }
    }
    
    @Test
    public void testBuscarPorId() throws Exception {
        Contrato c = Contrato.buscarPorId(1);
        validarContratoPadrao(c);
    }

    @Test
    public void testBuscarPorIdAluno() throws SQLException {
        List<Contrato> c = Contrato.buscarPorIdAluno(1);

        assertEquals((int) 1, c.size());
        validarContratoPadrao(c.get(0));
    }

    @Test
    public void testBuscarTodos() throws SQLException {
        List<Contrato> c = new Contrato().buscarTodos();

        assertEquals((int) 1, c.size());
        validarContratoPadrao(c.get(0));
    }
    
    @Test
    public void testBuscarGeral() throws SQLException {
        List<Contrato> c = Contrato.buscarGeral("a.NUM_PROTOCOLO","");

        assertEquals((int) 1, c.size());
        validarContratoPadrao(c.get(0));
    }

    @Test
    public void testAnexarDocumento() throws SQLException, ContratoInvalidoException, FileNotFoundException, IOException {
        Contrato c = Contrato.buscarPorId(1);

        String arquivoTeste = criarArquivoTesteCasoNaoExista();
        Integer idAnexo = c.anexarDocumento(arquivoTeste);
        assertNotNull(idAnexo);        
    }

    @Test
    public void testBuscarDocumentos() throws SQLException, ContratoInvalidoException, FileNotFoundException, IOException {
        Contrato c = Contrato.buscarPorId(1);

        String arquivoTeste = criarArquivoTesteCasoNaoExista();
        Integer idAnexo = c.anexarDocumento(arquivoTeste);

        List<Documento> docs = Contrato.buscarDocumentos(c.getId());
        List<Documento> retorno = docs.stream()
                .filter(x -> x.getId().equals(idAnexo))
                .collect(Collectors.toList());
        
        assertTrue(retorno.size() > 0);              
    }
    
    @Test
    public void testBaixarDocumento() throws SQLException, ContratoInvalidoException, FileNotFoundException, IOException {
        Contrato c = Contrato.buscarPorId(1);

        String arquivoTeste = criarArquivoTesteCasoNaoExista();
        Integer idAnexo = c.anexarDocumento(arquivoTeste);

        String arquivo = temp.getRoot().getAbsolutePath() + "/teste.txt";
        
        File f = new File(arquivo);
        assertFalse(f.exists());
        
        Contrato.baixarDocumento(idAnexo, arquivo);

        assertTrue(f.exists());
        assertTrue(f.isFile());
    }

    public static void validarContratoPadrao(Contrato c) {
        assertEquals(1, (int) c.getId());

        AlunoTest.validarAlunoPadrao(c.getAluno());
        EmpresaTest.validarEmpresaNewtonPaiva(c.getEmpresa());

        assertEquals(TipoContrato.OBRIGATORIO, c.getTipo());
        assertEquals(SituacaoContrato.ANDAMENTO, c.getSituacaoAtual());
        assertEquals("123456", c.getNumProtocolo());
        assertEquals((new GregorianCalendar(2016, Calendar.OCTOBER, 1)), c.getDataEntrada());
        assertEquals((new GregorianCalendar(2016, Calendar.OCTOBER, 05)), c.getDataInicio());
        assertNull(c.getDataRescisao());
        assertNull(c.getDataTermino());
        assertEquals(new BigDecimal("4.0"), c.getValorCargaHorariaDiaria());
        assertEquals(new BigDecimal("10.0"), c.getValorCargaHorariaSemanal());
        assertEquals("Teste", c.getObservacao());
    }

    private String criarArquivoTesteCasoNaoExista() throws IOException {
        File arqTeste = temp.newFile();
                
        try (FileWriter w = new FileWriter(arqTeste);) {
            for (int i = 0; i < 10; i++) {
                w.write("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
                w.write(" Suspendisse eleifend risus tortor, at iaculis est");
                w.write(" ultricies eu. Proin tellus diam, ornare sed tellus");
                w.write(" sit amet, scelerisque elementum eros. Nunc mattis arcu");
                w.write(" ut ultricies viverra. Donec dapibus est id luctus");
                w.write(" interdum. Nulla ornare risus sed sollicitudin suscipit.");
                w.write(" Aenean ac volutpat tellus. Sed posuere sem eget metus");
                w.write(" eleifend, nec fringilla ligula vestibulum. Phasellus");
                w.write(" molestie pretium massa. In hac habitasse platea");
                w.write(" dictumst. Quisque tempor magna diam, eget commodo nisl");
                w.write(" fermentum sed. Morbi in venenatis nibh. Sed placerat");
                w.write(" egestas enim eget facilisis. Nullam diam libero,");
                w.write(" faucibus a est vitae, vestibulum finibus ante. Donec");
                w.write(" et magna ac odio blandit maximus.\n");
            }

            return arqTeste.getAbsolutePath();
        }
    }
}
