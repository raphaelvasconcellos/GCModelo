/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excessoes.AlunoInvalidoException;
import static br.newtonpaiva.util.ConfigurationManager.*;
import br.newtonpaiva.util.CpfCnpjUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.newtonpaiva.util.CpfCnpjUtil.*;
import br.newtonpaiva.util.StringUtil;
import static br.newtonpaiva.util.ValidacoesUtil.validarEmail;
import static br.newtonpaiva.util.ValidacoesUtil.validarTamanhoTexto;
import java.sql.Types;
/**
 *
 * @author tarle
 */
public class Aluno {

    public static final String DEFICIENTE = "S";
    public static final String NAO_DEFICIENTE = "N";
    
    private Integer id;
    private String ra;
    private String nome;
    private String email;
    private String cpf;
    private Curso curso;
    private String deficiente;
    private List<Contrato> contratos;

    public Aluno() {

    }

    public Aluno(ResultSet r) throws SQLException {
        id = r.getInt(1);
        curso = Curso.buscarPorId(r.getInt(2));
        ra = r.getString(3);
        nome = r.getString(4);
        cpf = r.getString(5);
        email = r.getString(6);
        deficiente = r.getString(7);
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
     * @return the ra
     */
    public String getRa() {
        return ra;
    }

    /**
     * @param ra the ra to set
     */
    public void setRa(String ra) {
        this.ra = ra;
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
     * @return the contratos
     */
    public List<Contrato> getContratos() {
        return contratos;
    }

    /**
     * @param contratos the contratos to set
     */
    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", ra=" + ra + ", nome=" + nome + ", email=" + email + ", curso=" + curso + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.ra);
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
        final Aluno other = (Aluno) obj;
        if (!Objects.equals(this.ra, other.ra)) {
            return false;
        }
        return true;
    }

    public void salvar() throws AlunoInvalidoException, SQLException {
        if (getCurso() == null) {
            throw new AlunoInvalidoException("O Curso deve ser informado.");
        }

        if (getRa() == null) {
            throw new AlunoInvalidoException("O RA deve ser informado.");
        }

        if (getNome() == null) {
            throw new AlunoInvalidoException("O Nome deve ser informado.");
        }

        if (getId() == null) {
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(appSettings("aluno.insert"), Statement.RETURN_GENERATED_KEYS)) {

                stm.setInt(1, getCurso().getId());
                stm.setString(2, getRa());
                stm.setString(3, getNome());
                stm.setString(4, getEmail());
                stm.setString(5, getCpf());
                stm.setString(6, getDeficiente());

                stm.executeUpdate();

                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    setId(rs.getInt(1));
                } else {
                    throw new SQLException("Não foi possivel inserir o usuario");
                }
            }
        } else {
            try (Connection con = DriverManager.getConnection(
                    DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(appSettings("aluno.update"))) {

                stm.setInt(1, getCurso().getId());
                stm.setString(2, getRa());
                stm.setString(3, getNome());
                stm.setString(4, getEmail());
                stm.setString(5, getCpf());
                stm.setString(6, getDeficiente());
                stm.setInt(7, getId());

                stm.executeUpdate();
            }
        }
    }

    public static int excluir(Integer id) throws AlunoInvalidoException, SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(appSettings("aluno.delete"));) {

            s.setInt(1, id);
            return s.executeUpdate();
        }
    }

    public static Aluno buscarPorId(Integer id) throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(appSettings("aluno.select.id"))) {

            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {

                if (r.next())
                    return new Aluno(r);
                else
                    return null;
            }
        }
    }
    
    public static List<Aluno> buscarPorCurso(Integer idCurso) throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(appSettings("aluno.select.curso"))) {

            s.setInt(1, idCurso);

            try (ResultSet r = s.executeQuery()) {
                List<Aluno> lista = new ArrayList();
                while (r.next()) {
                    lista.add(new Aluno(r));
                }
                return lista;
            }
        }
    }
    
    public static Aluno buscarPorRA(String ra) throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(appSettings("aluno.select.ra"))) {

            s.setString(1, ra);

            try (ResultSet r = s.executeQuery()) {
                if (r.next())
                    return new Aluno(r);
                else
                    return null;
            }
        }
    }
    
    public static Aluno buscarPorCPF(String cpf) throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(appSettings("aluno.select.cpf"))) {

            s.setString(1, removerFormatacaoCpfCnpj(cpf));

            try (ResultSet r = s.executeQuery()) {
                if (r.next())
                    return new Aluno(r);
                else
                    return null;
            }
        }
    }

    public static List<Aluno> buscarTodos() throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("aluno.select"))) {

            try (ResultSet r = stm.executeQuery()) {
                List<Aluno> lista = new ArrayList();
                while (r.next()) {
                    lista.add(new Aluno(r));
                }
                return lista;
            }
        }
    }
    
    public static List<Aluno> buscarTodos(String nome, String curso, 
            String ra, String cpf, String deficiente) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("aluno.select.por.filtro"))) {
            
            if(StringUtil.isNullOrWhiteSpace(ra)) {
                stm.setNull(1, Types.VARCHAR);
                stm.setNull(2, Types.VARCHAR);
            } else {
                stm.setString(1, ra);
                stm.setString(2, ra);
            }
            
            cpf = CpfCnpjUtil.removerFormatacaoCpfCnpj(cpf);
            
            if(StringUtil.isNullOrWhiteSpace(cpf)) {
                stm.setNull(3, Types.VARCHAR);
                stm.setNull(4, Types.VARCHAR);
            } else {
                stm.setString(3, cpf);
                stm.setString(4, cpf);
            }
            
            if(StringUtil.isNullOrWhiteSpace(nome)) {
                stm.setNull(5, Types.VARCHAR);
                stm.setNull(6, Types.VARCHAR);
            } else {
                stm.setString(5, nome);
                stm.setString(6, "%" + nome + "%");
            }
            
            if(StringUtil.isNullOrWhiteSpace(curso)) {
                stm.setNull(7, Types.VARCHAR);
                stm.setNull(8, Types.VARCHAR);
            } else {
                stm.setString(7, curso);
                stm.setString(8, "%" + curso + "%");
            }
            
            if(StringUtil.isNullOrWhiteSpace(deficiente)) {
                stm.setNull(9, Types.VARCHAR);
                stm.setNull(10, Types.VARCHAR);
            } else {
                stm.setString(9, deficiente);
                stm.setString(10, deficiente);
            }
            
            try (ResultSet r = stm.executeQuery()) {
                List<Aluno> lista = new ArrayList();
                while (r.next()) {
                    lista.add(new Aluno(r));
                }
                return lista;
            }
        }
    }
    
    public static List<Aluno> buscarPorNome(String nome) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement stm = con.prepareStatement(appSettings("aluno.select.nome"))) {

            stm.setString(1, "%" + nome + "%");
            
            try (ResultSet r = stm.executeQuery()) {
                List<Aluno> lista = new ArrayList();
                while (r.next()) {
                    lista.add(new Aluno(r));
                }
                return lista;
            }
        }
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @return the cpf
     */
    public String getCpfFormatado() {
        return formatarCpfCnpj(cpf);
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = removerFormatacaoCpfCnpj(cpf);
    }

    /**
     * @return the deficiente
     */
    public String getDeficiente() {
        return deficiente;
    }

    /**
     * @param deficiente the deficiente to set
     */
    public void setDeficiente(String deficiente) {
        this.deficiente = deficiente;
    }
    
    public void carregarContratos() throws SQLException {
        setContratos(Contrato.buscarPorIdAluno(getId()));
    }
}
