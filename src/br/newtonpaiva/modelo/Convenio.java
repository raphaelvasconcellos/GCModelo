/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excessoes.ConvenioInvalidoException;
import java.util.Calendar;
import java.util.Objects;
import static br.newtonpaiva.util.ConfigurationManager.*;
import static br.newtonpaiva.util.DateUtil.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        /*
            Salvar Convenio
         */
        if (getEmpresa() == null) {
            throw new ConvenioInvalidoException("A Empresa deve ser informada.");
        }

        if (getSituacao() == null) {
            throw new ConvenioInvalidoException("A Situação deve ser informada.");
        }

        if (getId() == null) {
            try (Connection con = DriverManager.getConnection(
                    DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(
                            appSettings("convenio.insert"), Statement.RETURN_GENERATED_KEYS)) {

                stm.setInt(1, getEmpresa().getId());
                stm.setInt(2, getCurso().ordinal()+1);
                stm.setInt(3, getSituacao().ordinal()+1);
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
                    PreparedStatement stm = con.prepareStatement(
                            appSettings("convenio.update"))) {
                stm.setInt(1, getEmpresa().getId());
                stm.setInt(2, getCurso().ordinal()+1);
                stm.setInt(3, getSituacao().ordinal()+1);
                stm.setDate(4, converter(getDataVencimento()));
                stm.setDate(5, converter(getDataAssinatura()));
                stm.setInt(6, getId());
                stm.executeUpdate();
            }
        }
    }
        
    
    public static int excluir(Integer id) throws ConvenioInvalidoException, SQLException{       
        
        try(Connection con = DriverManager.getConnection(DB_URL,DB_USUARIO,DB_SENHA);               
           PreparedStatement stm = con.prepareStatement(
                   appSettings("convenio.delete"));){
           stm.setInt(1, id);
           return stm.executeUpdate();                                          
        }           
    }
    
     public static Convenio buscarPorId(Integer id) throws SQLException, ConvenioInvalidoException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(
                        appSettings("convenio.select.id"))) {

            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {                       

                if (r.next()) {
                    Convenio u = new Convenio();
                    u.setId(r.getInt(1));
                    u.setEmpresa(Empresa.buscarPorId(r.getInt(2)));
                    u.setCurso(Curso.values()[r.getInt(3)-1]);
                    u.setSituacao(SituacaoConvenio.values()[r.getInt(4)-1]);
                    u.setDataVencimento(converter(r.getDate(5)));
                    u.setDataAssinatura(converter(r.getDate(6)));                   
                    
                    return u;
                } else {                    
                    return null;
                }

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
                    Convenio u = new Convenio();
                    u.setId(r.getInt(1));
                    u.setEmpresa(Empresa.buscarPorId(r.getInt(2)));
                    u.setCurso(Curso.values()[r.getInt(3)-1]);
                    u.setSituacao(SituacaoConvenio.values()[r.getInt(4)-1]);
                    u.setDataVencimento(converter(r.getDate(5)));
                    u.setDataAssinatura(converter(r.getDate(6)));    
                    lista.add(u);

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
                    Convenio u = new Convenio();
                    u.setId(r.getInt(1));
                    u.setEmpresa(Empresa.buscarPorId(r.getInt(2)));
                    u.setCurso(Curso.values()[r.getInt(3)-1]);
                    u.setSituacao(SituacaoConvenio.values()[r.getInt(4)-1]);
                    u.setDataVencimento(converter(r.getDate(5)));
                    u.setDataAssinatura(converter(r.getDate(6)));    
                    lista.add(u);

                }

                return lista;
            }
        }

    }

}
