/**
 *
 * @author Julião Chaves
 */

package controle.produto;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produto.Produto;
import modelo.produto.ProdutoDAO;


public class AtualizarProdutoFotoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.listar();
        request.setAttribute("produtos", produtos);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/produto/atualizarFoto.jsp");
        rd.forward(request, response);
    }
}
