/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

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
public class ContratoHistorico {

    private Integer id;
    private Contrato contrato;
    private SituacaoContrato situacao;
    private Calendar data;
    private String observacao;

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
     * @return the contrato
     */
    public Contrato getContrato() {
        return contrato;
    }

    /**
     * @param contrato the contrato to set
     */
    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    /**
     * @return the situacao
     */
    public SituacaoContrato getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(SituacaoContrato situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the data
     */
    public Calendar getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Calendar data) {
        this.data = data;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.contrato);
        hash = 37 * hash + Objects.hashCode(this.situacao);
        hash = 37 * hash + Objects.hashCode(this.data);
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
        final ContratoHistorico other = (ContratoHistorico) obj;
        if (!Objects.equals(this.contrato, other.contrato)) {
            return false;
        }
        if (this.situacao != other.situacao) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContratoHistorico{" + "id=" + id + ", contrato=" + contrato + ", situacao=" + situacao + ", data=" + data + '}';
    }

    public void salvar() throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(
                        appSettings("contrato.historico.insert"), Statement.RETURN_GENERATED_KEYS)) {
            stm.setInt(1, getContrato().getId());
            stm.setInt(2, getSituacao().ordinal()+1);
            stm.setDate(3, converter(getData()));
            stm.setString(4, getObservacao());

            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                setId(rs.getInt(1));
            } else {
                throw new SQLException("Não foi possivel inserir o histórico");
            }

        }
    }

    public static int excluir(Integer id) throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(
                        appSettings("contrato.historico.delete"));) {
            s.setInt(1, id);
            return s.executeUpdate();
        }
    }

    public static List<ContratoHistorico> buscarPorIdContrato(Integer id) throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(
                        appSettings("contrato.historico.select"))) {
            s.setInt(1, id);
            try (ResultSet r = s.executeQuery()) {
                List<ContratoHistorico> lista = new ArrayList();
                while (r.next()) {
                    ContratoHistorico x = new ContratoHistorico();
                    x.setId(r.getInt(1));
                    x.setContrato(new Contrato().buscarPorId(r.getInt(2)));
                    x.setSituacao(SituacaoContrato.values()[r.getInt(3)-1]);
                    x.setData(converter(r.getDate(4)));
                    x.setObservacao(r.getString(5));
                    lista.add(x);
                }
                return lista;
            }
        }
    }
}
