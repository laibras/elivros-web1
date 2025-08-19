package controle.produto;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.categoria.Categoria;
import modelo.categoria.CategoriaDAO;
import modelo.produto.Produto;
import modelo.produto.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para preparar o formulário para inserir um novo produto ou
 * atualizar um produto existente
 */
public class FormProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.listar();
        request.setAttribute("categorias", categorias);

        if (request.getParameter("id") != null && request.getParameter("id").trim().length() != 0) {
            int id = Integer.parseInt(request.getParameter("id"));
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = produtoDAO.listar(id);
            if (produto != null) {
                request.setAttribute("produto", produto);
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/produto/form.jsp");
        rd.forward(request, response);
    }

}
