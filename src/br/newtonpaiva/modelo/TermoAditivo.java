/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excessoes.TermoAditivoInvalidoException;
import static br.newtonpaiva.util.ConfigurationManager.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Objects;
import static br.newtonpaiva.util.DateUtil.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tarley Lana
 */
public class TermoAditivo {

    private Integer id;
    private Contrato contrato;
    private Calendar data;
    private Calendar dataInicio;
    private Calendar dataTemino;
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
        hash = 23 * hash + Objects.hashCode(this.contrato);
        hash = 23 * hash + Objects.hashCode(this.data);
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
        final TermoAditivo other = (TermoAditivo) obj;
        if (!Objects.equals(this.contrato, other.contrato)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TermoAditivo{" + "id=" + id + ", contrato=" + contrato + ", data=" + data + ", dataInicio=" + dataInicio + ", dataTemino=" + dataTemino + ", observacao=" + observacao + '}';
    }

    public void salvar() throws SQLException {
        if (getId() == null) {
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(
                            appSettings("termoaditivo.insert"), Statement.RETURN_GENERATED_KEYS)) {
                stm.setInt(1, getContrato().getId());
                stm.setDate(2, converter(getData()));
                stm.setString(3, getObservacao());
                stm.setDate(4, converter(getDataInicio()));
                stm.setDate(5, converter(getDataTemino()));

                stm.executeUpdate();

                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    setId(rs.getInt(1));
                } else {
                    throw new SQLException("Não foi possivel inserir o histórico");
                }

            }
        } else {
             try (Connection con = DriverManager.getConnection(
                    DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(appSettings("termoaditivo.update"))) {

                stm.setInt(1, getContrato().getId());
                stm.setDate(2, converter(getData()));
                stm.setString(3, getObservacao());
                stm.setDate(4, converter(getDataInicio()));
                stm.setDate(5, converter(getDataTemino()));
                stm.setInt(6, getId());

                stm.executeUpdate();
            }
        }
    }
    
    public static int excluir(Integer id) throws TermoAditivoInvalidoException, SQLException {

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("termoaditivo.delete"));) {
            stm.setInt(1, id);
            return stm.executeUpdate();
        }
    }


    public int anexar(String nomeArquivo) throws FileNotFoundException, SQLException, IOException {
        FileInputStream in = new FileInputStream(nomeArquivo);
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(
                        appSettings("documento.digitalizado.insert.termo"),
                        Statement.RETURN_GENERATED_KEYS)) {
            stm.setInt(1, getContrato().getId());
            stm.setInt(2, getId());
            stm.setString(3, nomeArquivo);
            stm.setBlob(4, in);
            stm.executeUpdate();
            
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Não foi possivel anexar o documento");
            }
        }
                      
    }

    public static void baixar(Integer idTermoAditivo, String nomeArquivo) throws FileNotFoundException, SQLException, IOException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("documento.digitalizado.select"))) {
            stm.setInt(1, idTermoAditivo);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    FileOutputStream out = new FileOutputStream(nomeArquivo);
                    out.write(rs.getBytes(1));
                }
            }
        }
    }

    public static List<Documento> buscarDocumentos(Integer idContrato) throws FileNotFoundException, SQLException, IOException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(
                        appSettings("documento.digitalizado.select.termo"))) {
            stm.setInt(1, idContrato);
            
            try (ResultSet r = stm.executeQuery()) {
                
                List<Documento> retorno = new ArrayList<>();
                
                while (r.next()) {
                    retorno.add(new Documento(r));
                }
                
                return retorno;
            }
        }
    }   
    public static TermoAditivo buscarPorId(Integer id) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("termoaditivo.select"))) {

            stm.setInt(1, id);

            try (ResultSet r = stm.executeQuery()) {

                if (r.next()) {
                    TermoAditivo t = new TermoAditivo();
                    t.setId(r.getInt(1));
                    t.setContrato(new Contrato().buscarPorId(r.getInt(2)));
                    t.setData(converter(r.getDate(3)));
                    t.setObservacao(r.getString(4));
                    t.setDataInicio(converter(r.getDate(5)));
                    t.setDataTemino(converter(r.getDate(6)));

                    return t;
                } else {
                    return null;

                }

            }
        }
    }
    
    public static List<TermoAditivo> buscarPorIdContrato(Integer idContrato) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("termoaditivo.selectidcontrato"))) {

            stm.setInt(1, idContrato);
            try(ResultSet r = stm.executeQuery()){
               List<TermoAditivo> lista = new ArrayList();
               while (r.next()){
                   TermoAditivo t = new TermoAditivo();
                   t.setId(r.getInt(1));
                   t.setContrato(new Contrato().buscarPorId(r.getInt(2)));
                   t.setData(converter(r.getDate(3)));
                   t.setObservacao(r.getString(4));
                   t.setDataInicio(converter(r.getDate(5)));
                   t.setDataTemino(converter(r.getDate(6)));
                   lista.add(t);
               }
               return lista;
           }
        }
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
     * @return the dataTemino
     */
    public Calendar getDataTemino() {
        return dataTemino;
    }

    /**
     * @param dataTemino the dataTemino to set
     */
    public void setDataTemino(Calendar dataTemino) {
        this.dataTemino = dataTemino;
    }
}
