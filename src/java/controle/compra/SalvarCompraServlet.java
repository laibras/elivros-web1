package controle.compra;


import static config.Config.*; // Para as configs do DB
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.compra.ItemCarrinho;
import modelo.compra.CompraDAO;
import modelo.compra.ItemCompraDAO;
import modelo.produto.ProdutoDAO;
import modelo.usuario.Usuario;

public class SalvarCompraServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<ItemCarrinho> carrinho = (List<ItemCarrinho>) session.getAttribute("carrinho");

        if (usuario == null) {
            request.setAttribute("mensagem", "Você precisa estar logado para finalizar a compra.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if (carrinho == null || carrinho.isEmpty()) {
            request.setAttribute("mensagem", "Seu carrinho está vazio.");
            request.getRequestDispatcher("/carrinho?acao=ver").forward(request, response);
            return;
        }

        Connection conn = null;
        String mensagem = "";
        try {
            // 1. INICIAR A CONEXÃO
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);

            // 2. INICIAR A TRANSAÇÃO (desabilitar auto-commit)
            conn.setAutoCommit(false);

            // Instanciar os DAOs
            CompraDAO compraDAO = new CompraDAO();
            ItemCompraDAO itemCompraDAO = new ItemCompraDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();

            // 3. EXECUTAR OPERAÇÕES
            // Inserir a compra principal
            int compraId = compraDAO.inserir(usuario.getId(), new Date(), "Processando", conn);

            // Inserir os itens e atualizar o estoque
            for (ItemCarrinho item : carrinho) {
                // Inserir o item_compra
                itemCompraDAO.inserir(compraId, item.getProduto().getId(), item.getQuantidade(), item.getProduto().getPreco(), conn);
                
                // Atualizar o estoque do produto
                boolean estoqueAtualizado = produtoDAO.atualizarEstoque(item.getProduto().getId(), item.getQuantidade(), conn);
                if (!estoqueAtualizado) {
                    // Se não foi possível atualizar o estoque (ex: estoque insuficiente), lança uma exceção para cancelar tudo.
                    throw new SQLException("Estoque insuficiente para o produto: " + item.getProduto().getTitulo());
                }
            }

            // 4. COMMIT: Se tudo deu certo, efetiva as alterações no banco
            conn.commit();
            
            mensagem = "Compra realizada com sucesso!";
            session.removeAttribute("carrinho"); // Limpa o carrinho

        } catch (Exception e) {
            // 5. ROLLBACK: Se qualquer erro ocorreu, desfaz TODAS as operações
            mensagem = "Ocorreu um erro ao processar sua compra: " + e.getMessage();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("Erro ao reverter a transação: " + ex.getMessage());
                }
            }
            e.printStackTrace();
        } finally {
            // 6. FECHAR A CONEXÃO
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
        
        request.setAttribute("mensagem", mensagem);
        RequestDispatcher rd = request.getRequestDispatcher("ListarMinhasCompras");
        rd.forward(request, response);
    }
}