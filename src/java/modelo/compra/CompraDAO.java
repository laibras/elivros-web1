package modelo.compra;

import static config.Config.*;

import java.sql.*;
import java.util.Date;

import java.util.ArrayList;

import java.util.List;

import modelo.usuario.Usuario;
import modelo.usuario.UsuarioDAO;
import modelo.relatorio.*;

public class CompraDAO {

    /**
     * Método para listar uma compra específica pelo ID
     */
    public Compra listar(int id) {
        Compra compra = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement ps = c.prepareStatement("SELECT id, usuario_id, data_compra, status FROM compra WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                compra = new Compra();
                compra.setId(rs.getInt("id"));
                Usuario usuario = usuarioDAO.obter(rs.getInt("usuario_id"));
                compra.setData(rs.getTimestamp("data"));
                compra.setStatus(rs.getString("status"));
            }
            rs.close();
            ps.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return compra;
    }

    /**
     * Método para listar todas as compras
     */
    public List<Compra> listar() {
        List<Compra> compras = new ArrayList<>();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            Class.forName(JDBC_DRIVER);
            Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT id, usuario_id, data_compra, status FROM compra ORDER BY data_compra DESC");
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getInt("id"));
                Usuario usuario = usuarioDAO.obter(rs.getInt("usuario_id"));
                compra.setData(rs.getTimestamp("data"));
                compra.setStatus(rs.getString("status"));
                compras.add(compra);
            }
            rs.close();
            s.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return compras;
    }

    /*
    * Método para inserir uma nova compra e retornar seu ID.
    * @return O ID da compra inserida ou -1 em caso de falha.
     */
  
public int inserir(int usuarioId, Date data, String status, Connection conn) throws SQLException {
    int idGerado = -1;
    String sql = "INSERT INTO compra (usuario_id, data_compra, status) VALUES (?, ?, ?)";
    try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setInt(1, usuarioId);
        ps.setTimestamp(2, new Timestamp(data.getTime()));
        ps.setString(3, status);
        
        int linhasAfetadas = ps.executeUpdate();
        if (linhasAfetadas > 0) {
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                }
            }
        }
    }
    return idGerado;
}
    
    /**
     * Método para remover uma compra e seus itens associados.
     * @param id O ID da compra a ser removida.
     * @return true se a remoção for bem-sucedida, false caso contrário.
     */
    public boolean remover(int id) {
        boolean sucesso = false;
        Connection c = null;
        try {
            Class.forName(JDBC_DRIVER);
            c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            
            // Desativa o auto-commit para controlar a transação manualmente
            c.setAutoCommit(false);

            // 1. Remove os itens da compra da tabela 'item_compra'
            try (PreparedStatement psItens = c.prepareStatement("DELETE FROM item_compra WHERE compra_id = ?")) {
                psItens.setInt(1, id);
                psItens.executeUpdate();
            }

            // 2. Remove a compra da tabela 'compra'
            try (PreparedStatement psCompra = c.prepareStatement("DELETE FROM compra WHERE id = ?")) {
                psCompra.setInt(1, id);
                int linhasAfetadas = psCompra.executeUpdate();
                sucesso = (linhasAfetadas == 1);
            }

            // Se tudo correu bem, confirma a transação
            c.commit();

        } catch (Exception e) {
            System.out.println("Erro ao remover compra: " + e.getMessage());
            // Em caso de erro, desfaz a transação
            if (c != null) {
                try {
                    c.rollback();
                } catch (SQLException ex) {
                    System.out.println("Erro ao reverter transação: " + ex.getMessage());
                }
            }
            sucesso = false;
        } finally {
            // Garante que a conexão seja fechada
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
        return sucesso;
    }
    
    // Adicione este novo método à sua classe CompraDAO

/**
 * Lista todas as compras de um usuário específico, já com o valor total calculado.
 * @param usuarioId O ID do usuário.
 * @return Uma lista de compras com seus totais.
 */
public List<Compra> listarPorUsuario(int usuarioId) {
    List<Compra> compras = new ArrayList<>();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    String sql = "SELECT c.id, c.usuario_id, c.data_compra, c.status, SUM(ic.quantidade * ic.preco_unitario) as total_compra " +
                 "FROM compra c " +
                 "JOIN item_compra ic ON c.id = ic.compra_id " +
                 "WHERE c.usuario_id = ? " +
                 "GROUP BY c.id, c.usuario_id, c.data_compra, c.status " +
                 "ORDER BY c.data_compra DESC";

    try {
        Class.forName(JDBC_DRIVER);
        Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, usuarioId);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Compra compra = new Compra();
            compra.setId(rs.getInt("id"));
            // Para evitar carregar o objeto usuário inteiro, podemos setar só o ID se necessário
            // ou carregar como já está sendo feito.
            Usuario usuario = usuarioDAO.obter(rs.getInt("usuario_id"));
            compra.setUsuario(usuario);
            compra.setData(rs.getTimestamp("data_compra"));
            compra.setStatus(rs.getString("status"));
            compra.setTotal(rs.getDouble("total_compra")); // Usando o novo campo
            compras.add(compra);
        }
        rs.close();
        ps.close();
        c.close();
    } catch (Exception e) {
        System.out.println("Erro ao listar compras por usuário: " + e.getMessage());
        e.printStackTrace();
    }
    return compras;
}



public List<RelatorioCliente> getRelatorioComprasPorCliente(java.util.Date dataInicio, java.util.Date dataFim) {
    // 1. Deixe explícito que o método recebe java.util.Date
    List<RelatorioCliente> relatorio = new ArrayList<>();
    String sql = "SELECT u.id, u.nome, COUNT(c.id) as total_compras " +
                 "FROM compra c JOIN usuario u ON c.usuario_id = u.id " +
                 "WHERE CAST(c.data_compra AS DATE) BETWEEN ? AND ? " +
                 "GROUP BY u.id, u.nome ORDER BY total_compras DESC";
    try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
    
        ps.setDate(1, new java.sql.Date(dataInicio.getTime()));
        ps.setDate(2, new java.sql.Date(dataFim.getTime()));
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                RelatorioCliente item = new RelatorioCliente();
                item.setClienteId(rs.getInt("id"));
                item.setNomeCliente(rs.getString("nome"));
                item.setTotalCompras(rs.getLong("total_compras"));
                relatorio.add(item);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return relatorio;
}


public List<RelatorioVendas> getRelatorioVendasDiarias(java.util.Date dataInicio, java.util.Date dataFim) {
    List<RelatorioVendas> relatorio = new ArrayList<>();
    String sql = "SELECT CAST(c.data_compra AS DATE) as dia, SUM(ic.quantidade * ic.preco_unitario) as total_vendido " +
                 "FROM compra c JOIN item_compra ic ON c.id = ic.compra_id " +
                 "WHERE CAST(c.data_compra AS DATE) BETWEEN ? AND ? " +
                 "GROUP BY CAST(c.data_compra AS DATE) ORDER BY dia ASC";
    try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
         PreparedStatement ps = conn.prepareStatement(sql)) {

   
        ps.setDate(1, new java.sql.Date(dataInicio.getTime()));
        ps.setDate(2, new java.sql.Date(dataFim.getTime()));
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                RelatorioVendas item = new RelatorioVendas();
                item.setData(rs.getDate("dia"));
                item.setTotalVendido(rs.getDouble("total_vendido"));
                relatorio.add(item);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return relatorio;
}
}
