package modelo.produto;

import static config.Config.JDBC_DRIVER;
import static config.Config.JDBC_SENHA;
import static config.Config.JDBC_URL;
import static config.Config.JDBC_USUARIO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.categoria.Categoria;
import modelo.categoria.CategoriaDAO;
import java.sql.Connection; 

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que implementa o padrão DAO para a entidade produto
 */
public class ProdutoDAO {

    /**
     * Método para listar um produto existente
     *
     * @param id
     * @return
     */
    public Produto listar(int id) {
        Produto produto = null;
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement ps = c.prepareStatement("SELECT id, titulo,autor,ano, preco, foto, quantidade, categoria_id FROM produto WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setAutor(rs.getString("autor"));
                produto.setAno(rs.getInt("ano"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setFoto(rs.getString("foto"));
                produto.setQuantidade(rs.getInt("quantidade"));
                Categoria categoria = categoriaDAO.listar(rs.getInt("categoria_id"));
                produto.setCategoria(categoria);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return produto;
    }

    /**
     * Método para listar todos os produtos existentes
     *
     * @return
     */
    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT id, titulo, autor,ano, preco, foto, quantidade, categoria_id FROM produto");
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setAutor(rs.getString("autor"));
                produto.setAno(rs.getInt("ano"));                
                produto.setPreco(rs.getDouble("preco"));
                produto.setFoto(rs.getString("foto"));
                produto.setQuantidade(rs.getInt("quantidade"));
                Categoria categoria = categoriaDAO.listar(rs.getInt("categoria_id"));
                produto.setCategoria(categoria);
                produtos.add(produto);
            }
            rs.close();
            s.close();
            c.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return produtos;
    }
    
    /**
     * Método para listar todos os produtos em estoque
     *
     * @return
     */
    public List<Produto> listarEmEstoque() {
        List<Produto> produtos = new ArrayList<>();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT id, titulo,autor,ano, preco, foto, quantidade, categoria_id FROM produto WHERE quantidade > 0");
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setAutor(rs.getString("autor"));
                produto.setAno(rs.getInt("ano"));                
                
               produto.setPreco(rs.getDouble("preco"));
                produto.setFoto(rs.getString("foto"));
                produto.setQuantidade(rs.getInt("quantidade"));
                Categoria categoria = categoriaDAO.listar(rs.getInt("categoria_id"));
                produto.setCategoria(categoria);
                produtos.add(produto);
            }
            rs.close();
            s.close();
            c.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return produtos;
    }

    /**
     * Método para listar todos os produtos que estão faltando no estoque
     *
     * @return
     */
    public List<Produto> listarProdutosFaltantes() {
    List<Produto> produtos = new ArrayList<>();
    // SQL alterado para incluir a ordenação
    String sql = "SELECT id, titulo, preco, quantidade FROM produto WHERE quantidade <= 0 ORDER BY titulo ASC";
    try (Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setTitulo(rs.getString("titulo"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setQuantidade(rs.getInt("quantidade"));
            produtos.add(produto);
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return produtos;
}
    
    /**
     * Método para listar todos os produtos existentes pela descrição
     *
     * @param titulo
     * @return
     */
    public List<Produto> listar(String titulo) {
        if (titulo == null || titulo.trim().length() == 0) {
            titulo = "%";
        } else {
            titulo = "%" + titulo + "%";
        }
        List<Produto> produtos = new ArrayList<>();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement ps = c.prepareStatement("SELECT id, titulo,autor,ano, preco, foto, quantidade, categoria_id FROM produto WHERE UPPER(titulo) LIKE UPPER(?)");
            ps.setString(1, titulo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setAutor(rs.getString("autor"));
                produto.setAno(rs.getInt("ano"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setFoto(rs.getString("foto"));
                produto.setQuantidade(rs.getInt("quantidade"));
                Categoria categoria = categoriaDAO.listar(rs.getInt("categoria_id"));
                produto.setCategoria(categoria);
                produtos.add(produto);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return produtos;
    }

    /**
     * Método para inserir um novo produto
     *
     * @param titulo
     * @param autor
     * @param ano
     * @param preco
     * @param foto
     * @param quantidade
     * @param categoriaId
     * @return
     */
    public boolean inserir(String titulo,String autor,int ano, double preco, String foto, int quantidade, int categoriaId) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement ps = c.prepareStatement("INSERT INTO produto (titulo,autor,ano, preco, foto, quantidade, categoria_id) VALUES (?,?,?, ?, ?, ?, ?)");
            ps.setString(1, titulo);
            ps.setString(2, autor);
            ps.setInt(3,ano);
            ps.setDouble(4, preco);
            if (foto == null || foto.trim().length() == 0) {
                ps.setNull(5, Types.VARCHAR);
            } else {
                ps.setString(5, foto);
            }
            ps.setInt(6, quantidade);
            ps.setInt(7, categoriaId);
            sucesso = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sucesso;
    }

    /**
     * Método para atualizar um produto existente
     *
     * @param descricao
     * @param preco
     * @param foto
     * @param quantidade
     * @param categoriaId
     * @param id
     * @return
     */
    public boolean atualizar(String titulo,String autor,int ano, double preco, String foto, int quantidade, int categoriaId, int id) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement ps = c.prepareStatement("UPDATE produto SET titulo = ?,autor = ?,ano = ?, preco = ?, foto = ?, quantidade = ?, categoria_id = ? WHERE id = ?");
             ps.setString(1, titulo);
            ps.setString(2, autor);
            ps.setInt(3,ano);
            ps.setDouble(4, preco);
            if (foto == null || foto.trim().length() == 0) {
                ps.setNull(5, Types.VARCHAR);
            } else {
                ps.setString(5, foto);
            }
            ps.setInt(6, quantidade);
            ps.setInt(7, categoriaId);
            ps.setInt(8, id);
            sucesso = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sucesso;
    }

    /**
     * Método para remover um produto existente
     *
     * @param id
     * @return
     */
    public boolean remover(int id) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement ps = c.prepareStatement("DELETE FROM produto WHERE id = ?");
            ps.setInt(1, id);
            sucesso = (ps.executeUpdate() == 1);
            ps.close();
            c.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sucesso;
    }

    public boolean atualizarFoto(String foto, int id) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement ps = c.prepareStatement("UPDATE produto SET foto = ? WHERE id = ?");
            ps.setString(1, foto);
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
 * Atualiza o estoque de um produto. Este método foi desenhado para ser usado DENTRO de uma transação.
 * @param produtoId O ID do produto a ser atualizado.
 * @param quantidadeComprada A quantidade que foi comprada (será subtraída do estoque).
 * @param conn A conexão de banco de dados existente (essencial para a transação).
 * @return true se a atualização foi bem-sucedida.
 * @throws SQLException Se ocorrer um erro no SQL.
 */
public boolean atualizarEstoque(int produtoId, int quantidadeComprada, Connection conn) throws SQLException {
    String sql = "UPDATE produto SET quantidade = quantidade - ? WHERE id = ? AND quantidade >= ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, quantidadeComprada);
        ps.setInt(2, produtoId);
        ps.setInt(3, quantidadeComprada); // Garante que não se venda produto sem estoque
        int linhasAfetadas = ps.executeUpdate();
        return linhasAfetadas == 1;
    }
}
    
}
