package controle.produto;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produto.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para remover um produto existente
 */
public class RemoverProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        int id = Integer.parseInt(request.getParameter("id"));
        // processamento
        ProdutoDAO produtoDAO = new ProdutoDAO();
        boolean sucesso = produtoDAO.remover(id);
        // saída
        if (sucesso) {
            request.setAttribute("mensagem", "Produto removido com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível remover o produto");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ListarProdutos");
        rd.forward(request, response);
    }
    
}
