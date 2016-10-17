package br.newtonpaiva.modelo;

import br.newtonpaiva.modelo.excessoes.EmpresaInvalidaException;
import static br.newtonpaiva.util.ConfigurationManager.*;
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
 * @author Lucas Fornero
 */
public class Empresa {

    private Integer id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String email;
    private String telefone;
    private String contato;
    private String obs;

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "Empresa{" + "id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", endereco=" + endereco + ", bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade + ", uf=" + uf + ", email=" + email + ", telefone=" + telefone + ", contato=" + contato + ", obs=" + obs + '}';
    }

    public void salvar() throws EmpresaInvalidaException, SQLException {
        /*
            Salvar Empresa
         */
        if (getNome() == null) {
            throw new EmpresaInvalidaException("O Nome deve ser informado.");
        }

        if (getId() == null) {
            try (Connection con = DriverManager.getConnection(
                    DB_URL, DB_USUARIO, DB_SENHA);
                    PreparedStatement stm = con.prepareStatement(
                            appSettings("empresa.insert"), Statement.RETURN_GENERATED_KEYS)) {

                stm.setString(1, getNome());
                stm.setString(2, getCnpj());
                stm.setString(3, getEndereco());
                stm.setString(4, getBairro());
                stm.setString(5, getCep());
                stm.setString(6, getCidade());
                stm.setString(7, getUf());
                stm.setString(8, getEmail());
                stm.setString(9, getTelefone());
                stm.setString(10, getContato());
                stm.setString(11, getObs());

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
                    PreparedStatement stm = con.prepareStatement(appSettings("empresa.update"))) {

                stm.setString(1, getNome());
                stm.setString(2, getCnpj());
                stm.setString(3, getEndereco());
                stm.setString(4, getBairro());
                stm.setString(5, getCep());
                stm.setString(6, getCidade());
                stm.setString(7, getUf());
                stm.setString(8, getEmail());
                stm.setString(9, getTelefone());
                stm.setString(10, getContato());
                stm.setString(11, getObs());
                stm.setInt(12, getId());

                stm.executeUpdate();
            }
        }
    }

    public static int excluir(Integer id) throws EmpresaInvalidaException, SQLException {

        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(appSettings("empresa.delete"));) {

            s.setInt(1, id);
            return s.executeUpdate();
        }
    }

    public static Empresa buscarPorId(Integer id) throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(appSettings("empresa.selectid"))) {

            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {

                if (r.next()) {
                    Empresa u = new Empresa();
                    u.setId(r.getInt(1));
                    u.setNome(r.getString(2));
                    u.setCnpj(r.getString(3));
                    u.setEndereco(r.getString(4));
                    u.setBairro(r.getString(5));
                    u.setCep(r.getString(6));
                    u.setCidade(r.getString(7));
                    u.setUf(r.getString(8));
                    u.setEmail(r.getString(9));
                    u.setTelefone(r.getString(10));
                    u.setContato(r.getString(11));
                    u.setObs(r.getString(12));

                    return u;
                } else {
                    return null;
                }

            }
        }
    }

    public static List<Empresa> buscarTodos() throws SQLException {
        try (Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);
                PreparedStatement s = c.prepareStatement(appSettings("empresa.select"))) {

            try (ResultSet r = s.executeQuery()) {
                List<Empresa> lista = new ArrayList<>();

                while (r.next()) {
                    Empresa u = new Empresa();
                    u.setId(r.getInt(1));
                    u.setNome(r.getString(2));
                    u.setCnpj(r.getString(3));
                    u.setEndereco(r.getString(4));
                    u.setBairro(r.getString(5));
                    u.setCep(r.getString(6));
                    u.setCidade(r.getString(7));
                    u.setUf(r.getString(8));
                    u.setEmail(r.getString(9));
                    u.setTelefone(r.getString(10));
                    u.setContato(r.getString(11));
                    u.setObs(r.getString(12));
                    lista.add(u);

                }

                return lista;
            }
        }

    }

}
