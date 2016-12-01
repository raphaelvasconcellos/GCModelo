/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import static br.newtonpaiva.util.ConfigurationManager.*;
import static br.newtonpaiva.util.ValidacoesUtil.*;

import br.newtonpaiva.modelo.excecoes.LoginException;
import br.newtonpaiva.modelo.excecoes.SenhaException;
import br.newtonpaiva.modelo.excecoes.UsuarioInvalidoException;
import br.newtonpaiva.modelo.excecoes.UsuarioInvalidoConfirmacaoSenhaNuloException;
import br.newtonpaiva.modelo.excecoes.UsuarioInvalidoSenhaConfirmacaoSenhaNuloException;
import br.newtonpaiva.modelo.excecoes.UsuarioInvalidoSenhaNuloException;
import br.newtonpaiva.modelo.excecoes.UsuarioSenhaAtualInvalidoException;
import br.newtonpaiva.modelo.excecoes.UsuarioSenhaAtualNuloInvalidoException;
import br.newtonpaiva.modelo.excecoes.UsuarioTamanhoSenhaInvalidoException;
import br.newtonpaiva.util.StringUtil;

import java.util.Objects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Tarley Lana
 */
public class Usuario {
    
    private Integer id;
    private String nome;
    private String login;
    private String senha;
    private String senhaAtual;
    private String senhaConfirmacao;
    private String email;

    public Usuario() {

    }

    public Usuario(ResultSet r) throws SQLException {
        id = r.getInt(1);
        nome = r.getString(2);
        login = r.getString(3);
        email = r.getString(4);
        senha = r.getString(5);
        senhaAtual = senha;
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the senhaAtual
     */
    public String getSenhaAtual() {
        return senhaAtual;
    }

    /**
     * @param senhaAtual the senhaAtual to set
     */
    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }
    
    /**
     * @return the senhaConfirmacao
     */
    public String getSenhaConfirmacao() {
        return senhaConfirmacao;
    }

    /**
     * @param senhaConfirmacao the senhaConfirmacao to set
     */
    public void setSenhaConfirmacao(String senhaConfirmacao) {
        this.senhaConfirmacao = senhaConfirmacao;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", login=" + login + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.login);
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.login, other.login);
    }

    public static List<Usuario> buscarTodos() throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(
                        appSettings("usuario.select"))) {

            try (ResultSet r = stm.executeQuery()) {
                List<Usuario> lista = new ArrayList();
                while (r.next()) {
                    lista.add(new Usuario(r));
                }
                return lista;
            }
        }
    }
    
     public static List<Usuario> buscarTodos(String nome, String login, String email) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("usuario.select.por.filtro"))) {

            if(StringUtil.isNullOrWhiteSpace(nome)) {
                stm.setNull(1, Types.VARCHAR);
                stm.setNull(2, Types.VARCHAR);
            } else {
                stm.setString(1, nome);
                stm.setString(2, "%" + nome + "%");
            }
            
            if(StringUtil.isNullOrWhiteSpace(login)) {
                stm.setNull(3, Types.VARCHAR);
                stm.setNull(4, Types.VARCHAR);
            } else {
                stm.setString(3, login);
                stm.setString(4, "%" + login + "%");
            }
            
            if(StringUtil.isNullOrWhiteSpace(email)) {
                stm.setNull(5, Types.VARCHAR);
                stm.setNull(6, Types.VARCHAR);
            } else {
                stm.setString(5, email);
                stm.setString(6, "%" + email + "%");
            }
            
            try (ResultSet r = stm.executeQuery()) {
                List<Usuario> lista = new ArrayList();
                while (r.next()) {
                    lista.add(new Usuario(r));
                }
                return lista;
            }
        }
    }
    
    public void salvar() throws UsuarioInvalidoException, SQLException {
        if (getNome() == null) {
            throw new UsuarioInvalidoException("O Nome deve ser informado.");
        }
        if (getLogin() == null) {
            throw new UsuarioInvalidoException("O Login deve ser informado.");
        }
        if (getSenha() == null) {
            throw new UsuarioInvalidoException("A enha deve ser informado.");
        }
        if (getEmail() == null) {
            throw new UsuarioInvalidoException("O Email deve ser informado.");
        }

        if (getId() == null) {

            try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(
                            appSettings("usuario.insert"), Statement.RETURN_GENERATED_KEYS)) {
                stm.setString(1, getNome());
                stm.setString(2, getLogin());
                stm.setString(3, getSenha());
                stm.setString(4, getEmail());

                stm.executeUpdate();

                ResultSet rs = stm.getGeneratedKeys();

                if (rs.next()) {
                    setId(rs.getInt(1));
                } else {
                    throw new SQLException("Não foi possível inserir o usuário.");
                }
            }
        } else {
            try (Connection con = DriverManager.getConnection(
                    DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(
                            appSettings("usuario.update"))) {

                stm.setString(1, getNome());
                stm.setString(2, getLogin());
                stm.setString(3, getSenha());
                stm.setString(4, getEmail());
                stm.setInt(5, getId());

                stm.executeUpdate();
            }
        }
    }

    public static int excluir(Integer id) throws UsuarioInvalidoException, SQLException {

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(
                        appSettings("usuario.delete"));) {
            stm.setInt(1, id);
            return stm.executeUpdate();
        }
    }

    public static Usuario buscarPorId(Integer id) throws SQLException {

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(
                        appSettings("usuario.select.id"))) {
            stm.setInt(1, id);

            try (ResultSet r = stm.executeQuery()) {

                if (r.next()) {
                    return new Usuario(r);
                } else {
                    return null;
                }
            }
        }
    }

    public static boolean validar(String login, String senha) throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(
                        appSettings("usuario.select.login"))) {

            s.setString(1, login);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    if (r.getString(1).equals(senha)) {
                        return true;
                    }
                    throw new SenhaException();
                } else {
                    throw new LoginException();
                }
            }
        }
    }

    //Utilizado para alterar somente a senha do usuário
    public void salvarSenha() throws SQLException, UsuarioInvalidoSenhaNuloException, UsuarioInvalidoConfirmacaoSenhaNuloException,
            UsuarioInvalidoSenhaConfirmacaoSenhaNuloException, UsuarioTamanhoSenhaInvalidoException, UsuarioSenhaAtualInvalidoException,
            UsuarioSenhaAtualNuloInvalidoException {
        /*
        * validar regras de senhas
         */
        somenteAlterarSenha();
        validacaoSenhas();
        /*
        * Salva no banco de dados
         */
        //Conexão com o banco de dados já abre e fecha
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("usuario.update.senha"))) {
            //Prepara os parametros de entrada para o banco de dados
            stm.setString(1, getSenha());
            stm.setInt(2, getId());
            //Executa o Update
            stm.executeUpdate();
        }
    }
        
    public static Usuario buscarSenhaPorLogin(String login) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("usuario.select.login"))) {
            stm.setString(1, login);
            try (ResultSet r = stm.executeQuery()) {
                if (r.next()) {
                    return new Usuario(r);
                } else {
                    return null;
                }
            }
        }
    }
    
    private void somenteAlterarSenha() throws SQLException, UsuarioSenhaAtualInvalidoException, UsuarioSenhaAtualNuloInvalidoException {
        /*
        * Senha arual é nulo
         */
        if (getSenhaAtual().equals("")) {
            throw new UsuarioSenhaAtualNuloInvalidoException("usuario.senha.atual.nulo");
        }
        //Senha digitada é difirente da atual
        if (!buscarSenhaPorLogin(getLogin()).getSenha().equals(getSenhaAtual())) {
            throw new UsuarioSenhaAtualInvalidoException("usuario.senha.atual.diferente");

        }
    }
    
    private void validacaoSenhas() throws UsuarioInvalidoSenhaNuloException, UsuarioInvalidoConfirmacaoSenhaNuloException,
            UsuarioInvalidoSenhaConfirmacaoSenhaNuloException, UsuarioTamanhoSenhaInvalidoException {
        /*
        * Senha do usuário é nulo
         */
        if (getSenha().equals("")) {
            throw new UsuarioInvalidoSenhaNuloException();
        } else if (!validarTamanhoTexto(getSenha(), 100)) {
            /*
                * Tamanho do nome do usuário
             */
            throw new UsuarioTamanhoSenhaInvalidoException();
        }
        /*
        * Confirmação da Senha do usuário é nulo
         */
        if (getSenhaConfirmacao().equals("")) {
            throw new UsuarioInvalidoConfirmacaoSenhaNuloException();
        }
        /*
         * Confirmação da Senha do usuário é diferente da senha
         */
        if (!getSenhaAtual().equals(getSenha())) {
            throw new UsuarioInvalidoSenhaConfirmacaoSenhaNuloException();
        }
    }
}
