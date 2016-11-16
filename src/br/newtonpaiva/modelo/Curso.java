/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excecoes.CursoInvalidoIdNomeException;
import br.newtonpaiva.modelo.excecoes.CursoInvalidoNomeException;
import br.newtonpaiva.modelo.excecoes.CursoInvalidoNomeNuloException;
import br.newtonpaiva.modelo.excecoes.CursoInvalidoNomeTamanhoException;
import static br.newtonpaiva.util.ConfigurationManager.DB_SENHA;
import static br.newtonpaiva.util.ConfigurationManager.DB_URL;
import static br.newtonpaiva.util.ConfigurationManager.DB_USUARIO;
import static br.newtonpaiva.util.ConfigurationManager.appSettings;
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

    private Integer id;//cod_curso
    private String curso;//nom_curso
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
     */
    public Integer getTotalConvenio() {
        return totalConvenio;
    }

    /**
     * @param totalConvenio the totalConvenio to set
     */
    public void setTotalConvenio(Integer totalConvenio) {
        this.totalConvenio = totalConvenio;
    }

    /**
     * @return the totalAluno
     */
    public Integer getTotalAluno() {
        return totalAluno;
    }

    /**
     * @param totalAluno the totalAluno to set
     */
    public void setTotalAluno(Integer totalAluno) {
        this.totalAluno = totalAluno;
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
    public void salvar() throws SQLException, CursoInvalidoNomeNuloException, CursoInvalidoIdNomeException, CursoInvalidoNomeTamanhoException,
            CursoInvalidoNomeException {
        /*
        Valida algumas regras de campos
         */
        validacoes();
        /*
        * Salva no banco de dados
         */
        if (getId() == null) {//Insere se o ID estiver vazio

            //Verifica se o nome do curso existe no banco de dados
            if (buscarPorNome(getCurso()).size() > 0) {
                throw new CursoInvalidoNomeException();
            }

            //Conexão com o banco de dados já abre e fecha
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(appSettings("curso.insert"), Statement.RETURN_GENERATED_KEYS)) {
                //Prepara os parametros de entrada para o banco de dados
                stm.setString(1, getCurso());//nom_curso
                //Executa o insert
                stm.executeUpdate();
                //Retorna o ID que acabou de inserir ou retorna o erro
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    setId(rs.getInt(1));
                } else {
                    throw new SQLException("Falha ao inserir o curso");
                }

            }
        } else {//Altera se o ID não estiver vazio
            //Verifica se o nome do curso diferente do ID existe no banco de dados
            if (buscarPorIdNome(getId(), getCurso()).size() > 0) {
                throw new CursoInvalidoIdNomeException();
            }

            //Conexão com o banco de dados já abre e fecha
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(appSettings("curso.update"))) {
                //Prepara os parametros de entrada para o banco de dados
                stm.setString(1, getCurso());//nom_curso
                stm.setInt(2, getId());//cod_curso
                //Executa o Update
                stm.executeUpdate();
            }
        }
    }

    /*
    * utilizar para excluir um registro passando um ID
     */
    public int excluir(Integer id) throws SQLException {
        //Conexão com o banco de dados já abre e fecha
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("curso.delete"))) {
            //Prepara os parametros de entrada para o banco de dados
            stm.setInt(1, id);
            //Executa a exclusão
            return stm.executeUpdate();
        }
    }

    /*
    * Retorna a classe de cursos pelo id
     */
    public static Curso buscarPorId(Integer id) throws SQLException {
        //Conexão com o banco de dados já abre e fecha
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("curso.select.id"))) {
            //Prepara os parametros de entrada para o banco de dados
            stm.setInt(1, id);
            //Retorna os dados em uma array
            try (ResultSet r = stm.executeQuery()) {
                if (r.next()) {
                    return new Curso(r);
                } else {
                    return null;
                }
            }
        }
    }

    /*
    * Retorna a classe de cursos pelo nome e diferente do ID, somente no caso de lteração
     */
    public static List<Curso> buscarPorIdNome(Integer id, String Nome) throws SQLException {
        //Conexão com o banco de dados já abre e fecha
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("curso.select.id.nome"))) {
            //Prepara os parametros de entrada para o banco de dados
            stm.setInt(1, id);
            stm.setString(2, Nome);
            //Retorna os dados em uma array
            try (ResultSet r = stm.executeQuery()) {
                List<Curso> lista = new ArrayList<>();
                while (r.next()) {
                    Curso u = new Curso(r);
                    lista.add(u);
                }

                return lista;
            }
        }
    }

    /*
    * Retorna a classe de cursos pelo nome
     */
    public static List<Curso> buscarPorNome(String nome) throws SQLException {
        //Conexão com o banco de dados já abre e fecha
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("curso.select.nome"))) {
            //Prepara os parametros de entrada para o banco de dados
            stm.setString(1, nome);
            //Retorna os dados em uma array
            try (ResultSet r = stm.executeQuery()) {
                List<Curso> lista = new ArrayList<>();

                while (r.next()) {
                    Curso u = new Curso(r);
                    lista.add(u);
                }

                return lista;
            }
        }
    }

    /*
    * Retorna toda a lista de cursos 
     */
    public static List<Curso> buscarTodos(String Nome) throws SQLException {
        //Conexão com o banco de dados já abre e fecha
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("curso.select"))) {
            //Prepara os parametros de entrada para o banco de dados
            stm.setString(1, "%" + Nome + "%");
            //Retorna os dados em uma array
            try (ResultSet r = stm.executeQuery()) {
                List<Curso> lista = new ArrayList<>();

                while (r.next()) {
                    Curso u = new Curso(r);
                    lista.add(u);
                }

                return lista;
            }
            //Retorna vazio se não existir registro
        }
    }

    /*
    * Retorna o total de contratos
     */
    public static Curso buscarPorTotalConvenios(Integer id) throws SQLException {
        //Conexão com o banco de dados já abre e fecha
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("curso.select.total.convenio"))) {
            //Prepara os parametros de entrada para o banco de dados
            stm.setInt(1, id);
            //Retorna os dados em uma array
            try (ResultSet r = stm.executeQuery()) {
                if (r.next()) {
                    Curso c = new Curso();
                    c.setTotalConvenio(r.getInt(1));//Total de contratos do aluno
                    return c;
                } else {
                    return null;
                }
            }
        }
    }

    /*
    * Retorna o total de contratos
     */
    public static Curso buscarPorTotalAlunos(Integer id) throws SQLException {
        //Conexão com o banco de dados já abre e fecha
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("curso.select.total.aluno"))) {
            //Prepara os parametros de entrada para o banco de dados
            stm.setInt(1, id);
            //Retorna os dados em uma array
            try (ResultSet r = stm.executeQuery()) {
                if (r.next()) {
                    Curso c = new Curso();
                    c.setTotalAluno(r.getInt(1));//Total de contratos do aluno
                    return c;
                } else {
                    return null;
                }
            }
        }
    }

    private void validacoes() throws CursoInvalidoNomeNuloException, CursoInvalidoNomeTamanhoException {

        /*
        * Nome é nulo
         */
        if (getCurso().equals("")) {
            throw new CursoInvalidoNomeNuloException();
        }

        /*
        * Tamanho da descrição do tipo de contrato
         */
        if (!validarTamanhoTexto(getCurso(), 100)) {
            throw new CursoInvalidoNomeTamanhoException();
        }
    }

}
