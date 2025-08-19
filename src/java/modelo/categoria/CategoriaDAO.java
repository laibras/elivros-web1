package modelo.categoria;

import static config.Config.JDBC_DRIVER;
import static config.Config.JDBC_SENHA;
import static config.Config.JDBC_URL;
import static config.Config.JDBC_USUARIO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe que implementa o padrão DAO para a entidade categoria
 */
public class CategoriaDAO {
    
    /**
     * Método para listar uma a categoria existente
     * @param id
     * @return 
     */
    public Categoria listar(int id) {
        Categoria categoria = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL,JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement ps = c.prepareStatement("SELECT id, descricao FROM categoria WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));
            }
            rs.close();
            ps.close();
            c.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return categoria;
    }
    
    /**
     * Método para listar todas as categorias existentes
     * @return 
     */
    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL,JDBC_USUARIO, JDBC_SENHA);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT id, descricao FROM categoria");
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));
                categorias.add(categoria);
            }
            rs.close();
            s.close();
            c.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return categorias;
    }
    
    /**
     * Método para listar todas as categorias existentes pela descrição
     * @param descricao
     * @return 
     */
    public List<Categoria> listar(String descricao) {
        if (descricao == null || descricao.trim().length() == 0) {
            descricao = "%";
        } else {
            descricao = "%" + descricao + "%";
        }
        List<Categoria> categorias = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL,JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement ps = c.prepareStatement("SELECT id, descricao FROM categoria WHERE UPPER(descricao) LIKE UPPER(?)");
            ps.setString(1, descricao);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));
                categorias.add(categoria);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return categorias;
    }
    
    /**
     * Método para inserir uma nova categoria
     * 
     * @param descricao
     * @return 
     */
    public boolean inserir(String descricao) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL,JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement ps = c.prepareStatement("INSERT INTO categoria (descricao) VALUES (?)");
            ps.setString(1, descricao);
            sucesso = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sucesso;
    }
    
    /**
     * Método para atualizar uma categoria existente
     * 
     * @param descricao
     * @param id
     * @return 
     */
    public boolean atualizar(String descricao, int id) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL,JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement ps = c.prepareStatement("UPDATE categoria SET descricao = ? WHERE id = ?");
            ps.setString(1, descricao);
            ps.setInt(2, id);
            sucesso = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sucesso;
    }
    
    /**
     * Método para remover uma categoria existente
     * 
     * @param id
     * @return 
     */
    public boolean remover(int id) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL,JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement ps = c.prepareStatement("DELETE FROM categoria WHERE id = ?");
            ps.setInt(1, id);
            sucesso = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sucesso;
    }
    
}
