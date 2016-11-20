/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excecoes.CursoInvalidoException;
import static br.newtonpaiva.util.ConfigurationManager.DB_SENHA;
import static br.newtonpaiva.util.ConfigurationManager.DB_URL;
import static br.newtonpaiva.util.ConfigurationManager.DB_USUARIO;
import static br.newtonpaiva.util.ConfigurationManager.appSettings;
import br.newtonpaiva.util.StringUtil;
import static br.newtonpaiva.util.ValidacoesUtil.validarTamanhoTexto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Guilherme
 */
public class Curso {

    private Integer id;
    private String curso;
    private List<Aluno> listaAlunos;
    private List<Convenio> listaConvenios;
    private Integer totalConvenio;
    private Integer totalAluno;

    public Curso() {

    }

    public Curso(ResultSet rs) throws SQLException {
        id = rs.getInt(1);
        curso = rs.getString(2);
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
     * @return the curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * @return the totalConvenio
     * @throws java.sql.SQLException
     */
    public int getTotalConvenio() throws SQLException {
        if(listaConvenios == null)
            carregarConvenios();
        
        return listaConvenios == null ? 0 : listaConvenios.size();
    }

    /**
     * @return the totalAluno
     * @throws java.sql.SQLException
     */
    public int getTotalAluno() throws SQLException {
        if(listaAlunos == null)
            carregarAlunos();
        
        return listaAlunos == null ? 0 : listaAlunos.size();
    }

    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(List<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }

    public List<Convenio> getListaConvenios() {
        return listaConvenios;
    }

    public void setListaConvenios(List<Convenio> listaConvenios) {
        this.listaConvenios = listaConvenios;
    }
    
    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", curso=" + curso + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.curso);
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
        final Curso other = (Curso) obj;
        if (!Objects.equals(this.curso, other.curso)) {
            return false;
        }
        return true;
    }

    /*
    * Utilizado para inserir ou alterar
     */
    public void salvar() throws SQLException, CursoInvalidoException {
        validacoes();
        
        try {
            if (getId() == null) {

                try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                        PreparedStatement stm = con.prepareStatement(appSettings("curso.insert"), Statement.RETURN_GENERATED_KEYS)) {
                    stm.setString(1, getCurso());
                    stm.executeUpdate();
                    ResultSet rs = stm.getGeneratedKeys();
                    if (rs.next()) {
                        setId(rs.getInt(1));
                    } else {
                        throw new SQLException("Falha ao inserir o curso");
                    }
                }
            } else {
                
                try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                        PreparedStatement stm = con.prepareStatement(appSettings("curso.update"))) {
                    stm.setString(1, getCurso());
                    stm.setInt(2, getId());
                    stm.executeUpdate();
                }
            }
        }catch(SQLException e) {
            if(e.getMessage().contains("curso_i01")) {
                throw new CursoInvalidoException("Já existe um curso cadastrado com este nome.");
            }
            
            throw e;
        }
    }

    public static int excluir(Integer id) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("curso.delete"))) {
            stm.setInt(1, id);
            return stm.executeUpdate();
        }
    }

    public static Curso buscarPorId(Integer id) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("curso.select.id"))) {
            stm.setInt(1, id);
            try (ResultSet r = stm.executeQuery()) {
                if (r.next())
                    return new Curso(r);
                else
                    return null;
            }
        }
    }

    public static List<Curso> buscarPorNome(String nome) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("curso.select.nome"))) {
            stm.setString(1, "%" + nome + "%");
            try (ResultSet r = stm.executeQuery()) {
                List<Curso> lista = new ArrayList<>();

                while (r.next()) {
                    lista.add(new Curso(r));
                }

                return lista;
            }
        }
    }

    public static List<Curso> buscarTodos() throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("curso.select"))) {
            try (ResultSet r = stm.executeQuery()) {
                List<Curso> lista = new ArrayList<>();

                while (r.next()) {
                    lista.add(new Curso(r));
                }

                return lista;
            }
        }
    }

    public void carregarConvenios() throws SQLException {
        if(getId() != null)
            setListaConvenios(Convenio.buscarPorCurso(getId()));
    }

    public void carregarAlunos() throws SQLException {
        if(getId() != null)        
            setListaAlunos(Aluno.buscarPorCurso(getId()));
    }

    private void validacoes() throws CursoInvalidoException {

        if (StringUtil.isNullOrWhiteSpace(curso)) {
            throw new CursoInvalidoException("O nome do curso deve ser informado.");
        }

        if (!validarTamanhoTexto(getCurso(), 100)) {
            throw new CursoInvalidoException("O nome do curso deve ter menos de 100 caracteres.");
        }
    }
}
