package modelo.usuario;

import java.sql.*;
import static config.Config.*;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe que implementa o padrão DAO para a entidade usuário
 */
public class UsuarioDAO {

    /**
     * Método para inserir um novo cliente
     * 
     * @param nome
     * @param cpf
     * @param endereco
     * @param email
     * @param login
     * @param senha
     * @return 
     */
    public boolean inserirCliente(String nome, String cpf, String endereco, String email, String login, String senha) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement s = c.prepareStatement("INSERT INTO usuario (nome, cpf, endereco, email, login, senha, administrador) VALUES (?, ?, ?, ?, ?, ?, FALSE)");
            s.setString(1, nome);
            s.setString(2, cpf);
            s.setString(3, endereco);
            s.setString(4, email);
            s.setString(5, login);
            s.setString(6, senha);
            sucesso = (s.executeUpdate() == 1);
            s.close();
            c.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            sucesso = false;
        }
        return sucesso;
    }

    /**
     * Método para inserir um novo administrador
     * 
     * @param nome
     * @param cpf
     * @param endereco
     * @param email
     * @param login
     * @param senha
     * @return 
     */
    public boolean inserirAdministrador(String nome, String cpf, String endereco, String email, String login, String senha) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement s = c.prepareStatement("INSERT INTO usuario (nome, cpf, endereco, email, login, senha, administrador) VALUES (?, ?, ?, ?, ?, ?, TRUE)");
            s.setString(1, nome);
             s.setString(2, cpf);
            s.setString(3, endereco);
            s.setString(4, email);
            s.setString(5, login);
            s.setString(6, senha);
            sucesso = (s.executeUpdate() == 1);
            s.close();
            c.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            sucesso = false;
        }
        return sucesso;
    }

    /**
     * Método para um usuário pelo login e senha
     * 
     * @param login
     * @param senha
     * @return 
     */
    public Usuario obter(String login, String senha) {
        Usuario usuario = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement s = c.prepareStatement("SELECT id, nome, cpf, endereco, email, login, senha, administrador FROM usuario WHERE login = ? AND senha = ?");
            s.setString(1, login);
            s.setString(2, senha);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEndereco(rs.getString("endereco"));
                usuario.setEmail(rs.getString("email"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setAdministrador(rs.getBoolean("administrador"));
            }
            rs.close();
            s.close();
            c.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            usuario = null;
        }
        return usuario;
    }
    
    /**
     * Método para atualizar um usuário existente
     * 
     * @param nome
     * @param cpf
     * @param endereco
     * @param email
     * @param login
     * @param senha
     * @param id
     * @return 
     */
    public boolean atualizar(String nome, String cpf, String endereco, String email, String login, String senha, int id) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement s = c.prepareStatement("UPDATE usuario SET nome = ?, cpf=?, endereco = ?, email = ?, login = ?, senha = ? WHERE id = ?");
            s.setString(1, nome);
            s.setString(2, cpf);
            s.setString(3, endereco);
            s.setString(4, email);
            s.setString(5, login);
            s.setString(6, senha);
            s.setInt(7, id);
            sucesso = (s.executeUpdate() == 1);
            s.close();
            c.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            sucesso = false;
        }
        return sucesso;
    }
    
    /**
     * Método para remover um usuário existente
     * 
     * @param id
     * @return 
     */
    public boolean remover(int id) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement s = c.prepareStatement("DELETE FROM usuario WHERE id = ?");
            s.setInt(1, id);
            sucesso = (s.executeUpdate() == 1);
            s.close();
            c.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            sucesso = false;
        }
        return sucesso;
    }

    public Usuario obter(int id) {
    Usuario usuario = null;
    try {
        Class.forName(JDBC_DRIVER);
        Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement s = c.prepareStatement("SELECT id, nome, cpf, endereco, email, login, senha, administrador FROM usuario WHERE id = ?");
        s.setInt(1, id);
        ResultSet rs = s.executeQuery();
        if (rs.next()) {
            usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setCpf(rs.getString("cpf"));
            usuario.setEndereco(rs.getString("endereco"));
            usuario.setEmail(rs.getString("email"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setAdministrador(rs.getBoolean("administrador"));
        }
        rs.close();
        s.close();
        c.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return usuario;
}

}
