package modelo.compra;

import static config.Config.JDBC_DRIVER;
import static config.Config.JDBC_SENHA;
import static config.Config.JDBC_URL;
import static config.Config.JDBC_USUARIO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import modelo.produto.Produto;
import modelo.produto.ProdutoDAO;

public class ItemCompraDAO {

    /**
     * Lista todos os itens de uma compra
     */
    public List<ItemCompra> listar(int compraId) {
        List<ItemCompra> itens = new ArrayList<>();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        CompraDAO compraDAO = new CompraDAO(); // Vamos usar este para buscar o objeto Compra

        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement ps = c.prepareStatement("SELECT id, compra_id, produto_id, quantidade, preco_unitario FROM item_compra WHERE compra_id = ?");
            ps.setInt(1, compraId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItemCompra item = new ItemCompra();
                item.setId(rs.getInt("id"));

                Compra compra = compraDAO.listar(rs.getInt("compra_id")); // busca a compra pelo ID
                item.setCompra(compra);

                Produto produto = produtoDAO.listar(rs.getInt("produto_id")); // busca o produto
                item.setProduto(produto);

                item.setQuantidade(rs.getInt("quantidade"));
                item.setPrecoUnitario(rs.getDouble("preco_unitario"));

                itens.add(item);
            }

            rs.close();
            ps.close();
            c.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar itens da compra: " + e.getMessage());
        }

        return itens;
    }

    /**
 * Insere um novo item de compra usando uma conexão existente, para ser usado em transações.
 * @param compraId O ID da compra pai.
 * @param produtoId O ID do produto.
 * @param quantidade A quantidade comprada.
 * @param precoUnitario O preço unitário no momento da compra.
 * @param conn A conexão de banco de dados ativa para a transação.
 * @return true se a inserção for bem-sucedida.
 * @throws SQLException Se ocorrer um erro no SQL, a exceção é propagada para o servlet.
 */
public boolean inserir(int compraId, int produtoId, int quantidade, double precoUnitario, Connection conn) throws SQLException {
    // SQL para inserir o item
    String sql = "INSERT INTO item_compra (compra_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
    
    // O PreparedStatement é criado a partir da conexão recebida (conn).
    // Usamos try-with-resources para garantir que o PreparedStatement (ps) seja fechado.
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        // Define os parâmetros da consulta
        ps.setInt(1, compraId);
        ps.setInt(2, produtoId);
        ps.setInt(3, quantidade);
        ps.setDouble(4, precoUnitario);
        
        // Executa a inserção e verifica se uma linha foi afetada
        int linhasAfetadas = ps.executeUpdate();
        return linhasAfetadas == 1;
    }
    // A conexão (conn) NÃO é fechada aqui. O servlet que a criou é responsável por fechá-la.
}
}
