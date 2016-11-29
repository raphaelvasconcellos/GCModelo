/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excecoes.ConvenioInvalidoException;
import static br.newtonpaiva.util.ConfigurationManager.*;
import static br.newtonpaiva.util.DateUtil.*;

import br.newtonpaiva.util.CpfCnpjUtil;
import br.newtonpaiva.util.StringUtil;
import java.util.Calendar;
import java.util.Objects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tarley Lana
 */
public class Convenio {

    private Integer id;
    private Empresa empresa;
    private Curso curso;
    private SituacaoConvenio situacao;
    private Calendar dataVencimento;
    private Calendar dataAssinatura;

    public Convenio() {

    }

    public Convenio(ResultSet r) throws SQLException {
        id = r.getInt(1);
        empresa = Empresa.buscarPorId(r.getInt(2));
        curso = Curso.buscarPorId(r.getInt(3));
        situacao = SituacaoConvenio.values()[r.getInt(4)];
        dataVencimento = converter(r.getDate(5));
        dataAssinatura = converter(r.getDate(6));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.empresa);
        hash = 97 * hash + Objects.hashCode(this.dataVencimento);
        return hash;
    }

    @Override
    public String toString() {
        return "Convenio{" + "id=" + id + ", empresa=" + empresa + ", curso=" + curso + ", situacao=" + situacao + ", dataVencimento=" + dataVencimento + ", dataAssinatura=" + dataAssinatura + '}';
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
        final Convenio other = (Convenio) obj;
        if (!Objects.equals(this.empresa, other.empresa)) {
            return false;
        }
        if (!Objects.equals(this.dataVencimento, other.dataVencimento)) {
            return false;
        }
        return true;
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
     * @return the curso
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     * @return the situacao
     */
    public SituacaoConvenio getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(SituacaoConvenio situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the dataVencimento
     */
    public Calendar getDataVencimento() {
        return dataVencimento;
    }

    /**
     * @param dataVencimento the dataVencimento to set
     */
    public void setDataVencimento(Calendar dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    /**
     * @return the dataAssinatura
     */
    public Calendar getDataAssinatura() {
        return dataAssinatura;
    }

    /**
     * @param dataAssinatura the dataAssinatura to set
     */
    public void setDataAssinatura(Calendar dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    public void salvar() throws ConvenioInvalidoException, SQLException {

        if (getCurso() == null) {
            throw new ConvenioInvalidoException("O curso deve ser informada.");
        }

        if (getEmpresa() == null) {
            throw new ConvenioInvalidoException("A empresa deve ser informada.");
        }

        if (getSituacao() == null) {
            throw new ConvenioInvalidoException("A situação deve ser informada.");
        }
        
        if (getDataAssinatura() == null) {
            throw new ConvenioInvalidoException("A data da assinatura de ser informada.");
        }

        if (getDataVencimento() == null) {
            throw new ConvenioInvalidoException("A data de vencimento de ser informada.");
        }

        if (getDifferenceDays(getDataAssinatura(), getDataVencimento()) <= 0) {
            throw new ConvenioInvalidoException("Data de vencimento menor que a data de assinatura.");
        }

        if (getId() == null) {
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(appSettings("convenio.insert"), Statement.RETURN_GENERATED_KEYS)) {

                stm.setInt(1, getEmpresa().getId());
                stm.setInt(2, getCurso().getId());
                stm.setInt(3, getSituacao().ordinal());
                stm.setDate(4, converter(getDataVencimento()));
                stm.setDate(5, converter(getDataAssinatura()));

                stm.executeUpdate();

                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    setId(rs.getInt(1));
                } else {
                    throw new SQLException("Não foi possivel inserir a Empresa");
                }
            }
        } else {
            try (Connection con = DriverManager.getConnection(
                    DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(appSettings("convenio.update"))) {
                stm.setInt(1, getEmpresa().getId());
                stm.setInt(2, getCurso().getId());
                stm.setInt(3, getSituacao().ordinal());
                stm.setDate(4, converter(getDataVencimento()));
                stm.setDate(5, converter(getDataAssinatura()));
                stm.setInt(6, getId());
                stm.executeUpdate();
            }
        }
    }

    public static int excluir(Integer id) throws ConvenioInvalidoException, SQLException {

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(
                        appSettings("convenio.delete"));) {
            stm.setInt(1, id);
            return stm.executeUpdate();
        }
    }

    public static Convenio buscarPorId(Integer id) throws SQLException, ConvenioInvalidoException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(appSettings("convenio.select.id"))) {

            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {

                if (r.next()) {
                    return new Convenio(r);
                } else {
                    return null;
                }
            }
        }
    }

    public static List<Convenio> buscarPorCurso(Integer idCurso) throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(
                        appSettings("convenio.select.curso"))) {

            s.setInt(1, idCurso);
            
            try (ResultSet r = s.executeQuery()) {
                List<Convenio> lista = new ArrayList<>();

                while (r.next()) {
                    lista.add(new Convenio(r));
                }

                return lista;
            }
        }
    }
    
    public static List<Convenio> buscarTodos() throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(
                        appSettings("convenio.select"))) {

            try (ResultSet r = s.executeQuery()) {
                List<Convenio> lista = new ArrayList<>();

                while (r.next()) {
                    lista.add(new Convenio(r));
                }

                return lista;
            }
        }
    }

    public static List<Convenio> buscarPorIdEmpresa(Integer id) throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(
                        appSettings("convenio.select.empresa"))) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                List<Convenio> lista = new ArrayList<>();

                while (r.next()) {
                    lista.add(new Convenio(r));
                }

                return lista;
            }
        }
    }

    public static List<Convenio> buscarTodos(Calendar dataAssinaturaInicio, Calendar dataAssinaturaFim, 
            Calendar dataVencimentoInicio, Calendar dataVencimentoFim,
            String cnpj, SituacaoConvenio situacao, String curso) throws SQLException {
        
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("convenio.select.por.filtro"))) {
            
            if(dataAssinaturaInicio == null) {
                stm.setNull(1, Types.DATE);
                stm.setNull(2, Types.DATE);
            } else {
                stm.setDate(1, converter(dataAssinaturaInicio));
                stm.setDate(2, converter(dataAssinaturaInicio));
            }
            
            if(dataAssinaturaFim == null) {
                stm.setNull(3, Types.DATE);
                stm.setNull(4, Types.DATE);
            } else {
                stm.setDate(3, converter(dataAssinaturaFim));
                stm.setDate(4, converter(dataAssinaturaFim));
            }
            
            if(dataVencimentoInicio == null) {
                stm.setNull(5, Types.DATE);
                stm.setNull(6, Types.DATE);
            } else {
                stm.setDate(5, converter(dataVencimentoInicio));
                stm.setDate(6, converter(dataVencimentoInicio));
            }
            
            if(dataVencimentoFim == null) {
                stm.setNull(7, Types.DATE);
                stm.setNull(8, Types.DATE);
            } else {
                stm.setDate(7, converter(dataVencimentoFim));
                stm.setDate(8, converter(dataVencimentoFim));
            }

            if(situacao == null) {
                stm.setNull(9, Types.INTEGER);
                stm.setNull(10, Types.INTEGER);
            } else {
                stm.setInt(9, situacao.ordinal());
                stm.setInt(10, situacao.ordinal());
            }
            
            cnpj = CpfCnpjUtil.removerFormatacaoCpfCnpj(cnpj);
            
            if(StringUtil.isNullOrWhiteSpace(cnpj)) {
                stm.setNull(11, Types.VARCHAR);
                stm.setNull(12, Types.VARCHAR);
            } else {
                stm.setString(11, cnpj);
                stm.setString(12, cnpj);
            }
            
            if(StringUtil.isNullOrWhiteSpace(curso)) {
                stm.setNull(13, Types.VARCHAR);
                stm.setNull(14, Types.VARCHAR);
            } else {
                stm.setString(13, curso);
                stm.setString(14, "%" + curso + "%");
            }

            try (ResultSet r = stm.executeQuery()) {
                List<Convenio> lista = new ArrayList<>();
                
                while (r.next()) {
                    lista.add(new Convenio(r));
                }
                return lista;
            }
        }
    }
}
