
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excessoes.ContratoInvalidoException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import static br.newtonpaiva.util.ConfigurationManager.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static br.newtonpaiva.util.DateUtil.*;
import java.util.ArrayList;

/**
 *
 * @author Tarley Lana
 */
public class Contrato {

    private Integer id;
    private Aluno aluno;
    private TipoContrato tipo;
    private Empresa empresa;
    private SituacaoContrato situacaoAtual;
    private String numProtocolo;
    private Calendar dataEntrada;
    private Calendar dataInicio;
    private Calendar dataTermino;
    private Calendar dataRescisao;
    private BigDecimal valorBolsa;
    private BigDecimal auxilioTransporte;
    private BigDecimal valorCargaHorariaDiaria;
    private BigDecimal valorCargaHorariaSemanal;
    private String observacao;
    private List<TermoAditivo> aditivo;
    private List<ContratoHistorico> historicoSituacao;
    private String indAlunoContratado;

    public String getIndAlunoContratado() {
        return indAlunoContratado;
    }

    public void setIndAlunoContratado(String indAlunoContratado) {
        this.indAlunoContratado = indAlunoContratado;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the aluno
     */
    public Aluno getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    /**
     * @return the tipo
     */
    public TipoContrato getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoContrato tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the situacaoAtual
     */
    public SituacaoContrato getSituacaoAtual() {
        return situacaoAtual;
    }

    /**
     * @param situacaoAtual the situacaoAtual to set
     */
    public void setSituacaoAtual(SituacaoContrato situacaoAtual) {
        this.situacaoAtual = situacaoAtual;
    }

    /**
     * @return the numProtocolo
     */
    public String getNumProtocolo() {
        return numProtocolo;
    }

    /**
     * @param numProtocolo the numProtocolo to set
     */
    public void setNumProtocolo(String numProtocolo) {
        this.numProtocolo = numProtocolo;
    }

    /**
     * @return the dataEntrada
     */
    public Calendar getDataEntrada() {
        return dataEntrada;
    }

    /**
     * @param dataEntrada the dataEntrada to set
     */
    public void setDataEntrada(Calendar dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    /**
     * @return the dataInicio
     */
    public Calendar getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataTermino
     */
    public Calendar getDataTermino() {
        return dataTermino;
    }

    /**
     * @param dataTermino the dataTermino to set
     */
    public void setDataTermino(Calendar dataTermino) {
        this.dataTermino = dataTermino;
    }

    /**
     * @return the dataRescisao
     */
    public Calendar getDataRescisao() {
        return dataRescisao;
    }

    /**
     * @param dataRescisao the dataRescisao to set
     */
    public void setDataRescisao(Calendar dataRescisao) {
        this.dataRescisao = dataRescisao;
    }

    /**
     * @return the valorBolsa
     */
    public BigDecimal getValorBolsa() {
        return valorBolsa;
    }

    /**
     * @param valorBolsa the valorBolsa to set'
     */
    public void setValorBolsa(BigDecimal valorBolsa) {
        this.valorBolsa = valorBolsa;
    }

    /**
     * @return the valorCargaHorariaDiaria
     */
    public BigDecimal getValorCargaHorariaDiaria() {
        return valorCargaHorariaDiaria;
    }

    /**
     * @param valorCargaHorariaDiaria the valorCargaHorariaDiaria to set
     */
    public void setValorCargaHorariaDiaria(BigDecimal valorCargaHorariaDiaria) {
        this.valorCargaHorariaDiaria = valorCargaHorariaDiaria;
    }

    /**
     * @return the valorCargaHorariaSemanal
     */
    public BigDecimal getValorCargaHorariaSemanal() {
        return valorCargaHorariaSemanal;
    }

    /**
     * @param valorCargaHorariaSemanal the valorCargaHorariaSemanal to set
     */
    public void setValorCargaHorariaSemanal(BigDecimal valorCargaHorariaSemanal) {
        this.valorCargaHorariaSemanal = valorCargaHorariaSemanal;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the auxilioTransporte
     */
    public BigDecimal getAuxilioTransporte() {
        return auxilioTransporte;
    }

    /**
     * @param auxilioTransporte the auxilioTransporte to set
     */
    public void setAuxilioTransporte(BigDecimal auxilioTransporte) {
        this.auxilioTransporte = auxilioTransporte;
    }

    /**
     * @return the aditivo
     */
    public List<TermoAditivo> getAditivo() {
        return aditivo;
    }

    /**
     * @param aditivo the aditivo to set
     */
    public void setAditivo(List<TermoAditivo> aditivo) {
        this.aditivo = aditivo;
    }

    /**
     * @return the historicoSituacao
     */
    public List<ContratoHistorico> getHistoricoSituacao() {
        return historicoSituacao;
    }

    /**
     * @param historicoSituacao the historicoSituacao to set
     */
    public void setHistoricoSituacao(List<ContratoHistorico> historicoSituacao) {
        this.historicoSituacao = historicoSituacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contrato other = (Contrato) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contrato{" + "id=" + id + ", aluno=" + aluno + ", tipo=" + tipo + ", empresa=" + empresa + ", situacaoAtual=" + situacaoAtual + ", numProtocolo=" + numProtocolo + ", dataEntrada=" + dataEntrada + ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino + ", dataRescisao=" + dataRescisao + ", valorBolsa=" + valorBolsa + ", auxilioTransporte=" + auxilioTransporte + ", valorCargaHorariaDiaria=" + valorCargaHorariaDiaria + ", valorCargaHorariaSemanal=" + valorCargaHorariaSemanal + ", indAlunoContratado=" + indAlunoContratado + '}';
    }

    public Double buscarTotalHorasDiariasAluno(Integer idAluno, Integer idContrato) throws SQLException {
        if (idContrato == null) {
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(
                            appSettings("contrato.select.horas.diarias"))) {
                stm.setInt(1, idAluno);
                try (ResultSet r = stm.executeQuery()) {
                    if (r.next())                         
                        return r.getDouble(1);
                    else
                        return 0.0;                    
                }
            }            
        } else {
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(
                            appSettings("contrato.select.horas.diarias.edicao"))) {
                stm.setInt(1, idAluno);
                stm.setInt(2, idContrato);
                try (ResultSet r = stm.executeQuery()) {
                    if (r.next())                         
                        return r.getDouble(1);
                    else
                        return 0.0;                    
                }
            }
        }
    }
    
     public Double buscarTotalHorasSemanaisAluno(Integer idAluno, Integer idContrato) throws SQLException {
        if (idContrato == null) {
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(
                            appSettings("contrato.select.horas.semanais"))) {
                stm.setInt(1, idAluno);
                try (ResultSet r = stm.executeQuery()) {
                    if (r.next())                         
                        return r.getDouble(1);
                    else
                        return 0.0;                    
                }
            }            
        } else {
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(
                            appSettings("contrato.select.horas.semanais.edicao"))) {
                stm.setInt(1, idAluno);
                stm.setInt(2, idContrato);
                try (ResultSet r = stm.executeQuery()) {
                    if (r.next())                         
                        return r.getDouble(1);
                    else
                        return 0.0;                    
                }
            }
        }
    }
     
    public Integer buscarTempoCotratoAlunoEmpresa(Integer idAluno, Integer idEmpresa, Integer idContrato) throws SQLException{
       if (idContrato == null) {
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(
                            appSettings("contrato.select.tempo.contrato.aluno.empresa"))) {
                stm.setInt(1, idAluno);
                stm.setInt(2, idEmpresa);
                try (ResultSet r = stm.executeQuery()) {
                    if (r.next())                         
                        return r.getInt(1);
                    else
                        return 0;                    
                }
            }            
        } else {
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(
                            appSettings("contrato.select.tempo.contrato.aluno.empresa.edicao"))) {
                stm.setInt(1, idAluno);
                stm.setInt(2, idEmpresa);
                stm.setInt(3, idContrato);
                try (ResultSet r = stm.executeQuery()) {
                    if (r.next())                         
                        return r.getInt(1);
                    else
                        return 0;
                }
            }
        } 
    }

    public void salvar(Boolean validaHoras) throws ContratoInvalidoException, SQLException {
        if (getAluno() == null) {
            throw new ContratoInvalidoException("O Curso deve ser informado.");
        }
        if (getEmpresa() == null) {
            throw new ContratoInvalidoException("A Empresadeve ser informada.");
        }
        if (getSituacaoAtual() == null) {
            throw new ContratoInvalidoException("A Situação Atual deve ser informada.");
        }

        if (validaHoras) {
            Double totalHoraDiaria = buscarTotalHorasDiariasAluno(getAluno().getId(),getId());           
            Double totalHoraSemanal = buscarTotalHorasSemanaisAluno(getAluno().getId(), getId());            
            if ((totalHoraDiaria + valorCargaHorariaDiaria.doubleValue()) > 6.0) {
                throw new ContratoInvalidoException("A carga horária diária nao deve exceder 6 horas");
            }
            if ((totalHoraSemanal + valorCargaHorariaSemanal.doubleValue()) > 30.0) {
                throw new ContratoInvalidoException("A carga horária semanal nao deve exceder 30 horas");
            }
        }        
        if ("N".equals(getAluno().getDeficiente())) {
            Integer difDay = getDifferenceDays(converter(getDataInicio()), converter(getDataTermino()));
            Integer diasAntigos = buscarTempoCotratoAlunoEmpresa(getAluno().getId(), getEmpresa().getId(), getId());            
            if (diasAntigos+difDay > 731) {
                throw new ContratoInvalidoException("O aluno não pode ter mais que dois anos de contrato na mesma empresa");
            }
        }

        if (getId() == null) {
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(
                            appSettings("contrato.insert"), Statement.RETURN_GENERATED_KEYS)) {
                
                stm.setInt(1, getAluno().getId());
                stm.setInt(2, getEmpresa().getId());
                stm.setInt(3, getTipo().ordinal() + 1);
                stm.setInt(4, getSituacaoAtual().ordinal() + 1);
                stm.setString(5, getNumProtocolo());
                stm.setDate(6, converter(getDataEntrada()));
                stm.setDate(7, converter(getDataInicio()));
                stm.setDate(8, converter(getDataTermino()));
                if (getDataRescisao() == null)
                    stm.setNull(9, 0);
                else
                    stm.setDate(9, converter(getDataRescisao()));
                stm.setDouble(10, getValorBolsa().doubleValue());
                stm.setDouble(11, getAuxilioTransporte().doubleValue());
                stm.setDouble(12, getValorCargaHorariaDiaria().doubleValue());
                stm.setDouble(13, getValorCargaHorariaSemanal().doubleValue());
                stm.setString(14, getObservacao());
                stm.setString(15, getIndAlunoContratado());
                stm.executeUpdate();

                ResultSet rs = stm.getGeneratedKeys();

                if (rs.next()) {
                    setId(rs.getInt(1));
                } else {
                    throw new SQLException("Não foi possível inserir o usuário.");
                }
            }
        } else {
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(
                            appSettings("contrato.update"))) {
                stm.setInt(1, getAluno().getId());
                stm.setInt(2, getEmpresa().getId());
                stm.setInt(3, getTipo().ordinal() + 1);
                stm.setInt(4, getSituacaoAtual().ordinal() + 1);
                stm.setString(5, getNumProtocolo());
                stm.setDate(6, converter(getDataEntrada()));
                stm.setDate(7, converter(getDataInicio()));
                stm.setDate(8, converter(getDataTermino()));
                if (getDataRescisao() == null)
                    stm.setNull(9, 0);
                else
                    stm.setDate(9, converter(getDataRescisao()));
                stm.setDouble(10, getValorBolsa().doubleValue());
                stm.setDouble(11, getAuxilioTransporte().doubleValue());
                stm.setDouble(12, getValorCargaHorariaDiaria().doubleValue());
                stm.setDouble(13, getValorCargaHorariaSemanal().doubleValue());
                stm.setString(14, getObservacao());
                stm.setString(15, getIndAlunoContratado());
                stm.setInt(16, getId());
                stm.executeUpdate();

            }
        }

    }

    public static int excluir(Integer id) throws ContratoInvalidoException, SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(
                        appSettings("contrato.delete"));) {
            stm.setInt(1, id);
            return stm.executeUpdate();
        }
    }

    public void anexarDocumento(String nomeArquivo) throws FileNotFoundException, SQLException {
        FileInputStream in = new FileInputStream(nomeArquivo);
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(
                        appSettings("documento.digitalizado.insert"))) {
            stm.setInt(1, getId());
            stm.setBlob(2, in);
            stm.executeUpdate();
        }
    }

    public static void baixarDocumento(Integer idContrato, String nomeArquivo) throws FileNotFoundException, SQLException, IOException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(
                        appSettings("documento.digitalizado.select"))) {
            stm.setInt(1, idContrato);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    FileOutputStream out = new FileOutputStream(nomeArquivo);
                    out.write(rs.getBytes(1));
                }
            }
        }
    }

    public Contrato buscarPorId(Integer id) throws SQLException {

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(
                        appSettings("contrato.select.id"))) {
            stm.setInt(1, id);

            try (ResultSet r = stm.executeQuery()) {

                if (r.next()) {
                    Contrato c = new Contrato();
                    c.setId(r.getInt(1));
                    c.setAluno(Aluno.buscarPorId(r.getInt(2)));
                    c.setEmpresa(Empresa.buscarPorId(r.getInt(3)));
                    c.setTipo(TipoContrato.values()[r.getInt(4) - 1]);
                    c.setSituacaoAtual(SituacaoContrato.values()[r.getInt(5) - 1]);
                    c.setNumProtocolo(r.getString(6));
                    c.setDataEntrada(converter(r.getDate(7)));
                    c.setDataInicio(converter(r.getDate(8)));
                    c.setDataTermino(converter(r.getDate(9)));
                    c.setDataRescisao(converter(r.getDate(10)));
                    c.setValorBolsa(BigDecimal.valueOf(r.getDouble(11)));
                    c.setAuxilioTransporte(BigDecimal.valueOf(r.getDouble(12)));
                    c.setValorCargaHorariaDiaria(BigDecimal.valueOf(r.getDouble(13)));
                    c.setValorCargaHorariaSemanal(BigDecimal.valueOf(r.getDouble(14)));
                    c.setObservacao(r.getString(15));
                    c.setIndAlunoContratado(r.getString(15));

                    return c;
                } else {
                    return null;
                }
            }
        }

    }

    public List<Contrato> buscarTodos() throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(
                        appSettings("contrato.select"))) {

            try (ResultSet r = stm.executeQuery()) {
                List<Contrato> lista = new ArrayList();
                while (r.next()) {
                    Contrato c = new Contrato();
                    c.setId(r.getInt(1));
                    c.setAluno(Aluno.buscarPorId(r.getInt(2)));
                    c.setEmpresa(Empresa.buscarPorId(r.getInt(3)));
                    c.setTipo(TipoContrato.values()[r.getInt(4) - 1]);
                    c.setSituacaoAtual(SituacaoContrato.values()[r.getInt(5) - 1]);
                    c.setNumProtocolo(r.getString(6));
                    c.setDataEntrada(converter(r.getDate(7)));
                    c.setDataInicio(converter(r.getDate(8)));
                    c.setDataTermino(converter(r.getDate(9)));
                    c.setDataRescisao(converter(r.getDate(10)));
                    c.setValorBolsa(BigDecimal.valueOf(r.getDouble(11)));
                    c.setAuxilioTransporte(BigDecimal.valueOf(r.getDouble(12)));
                    c.setValorCargaHorariaDiaria(BigDecimal.valueOf(r.getDouble(13)));
                    c.setValorCargaHorariaSemanal(BigDecimal.valueOf(r.getDouble(14)));
                    c.setObservacao(r.getString(15));
                    c.setIndAlunoContratado(r.getString(15));
                    lista.add(c);
                }
                return lista;
            }
        }
    }

    public List<Contrato> buscarPorIdAluno(Integer id) throws SQLException {

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(
                        appSettings("contrato.select.aluno"))) {
            stm.setInt(1, id);

            try (ResultSet r = stm.executeQuery()) {
                List<Contrato> lista = new ArrayList();
                while (r.next()) {
                    Contrato c = new Contrato();
                    c.setId(r.getInt(1));
                    c.setAluno(Aluno.buscarPorId(r.getInt(2)));
                    c.setEmpresa(Empresa.buscarPorId(r.getInt(3)));
                    c.setTipo(TipoContrato.values()[r.getInt(4) - 1]);
                    c.setSituacaoAtual(SituacaoContrato.values()[r.getInt(5) - 1]);
                    c.setNumProtocolo(r.getString(6));
                    c.setDataEntrada(converter(r.getDate(7)));
                    c.setDataInicio(converter(r.getDate(8)));
                    c.setDataTermino(converter(r.getDate(9)));
                    c.setDataRescisao(converter(r.getDate(10)));
                    c.setValorBolsa(BigDecimal.valueOf(r.getDouble(11)));
                    c.setAuxilioTransporte(BigDecimal.valueOf(r.getDouble(12)));
                    c.setValorCargaHorariaDiaria(BigDecimal.valueOf(r.getDouble(13)));
                    c.setValorCargaHorariaSemanal(BigDecimal.valueOf(r.getDouble(14)));
                    c.setObservacao(r.getString(15));
                    c.setIndAlunoContratado(r.getString(15));
                    lista.add(c);
                }
                return lista;
            }
        }

    }

}
