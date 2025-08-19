package controle.categoria;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.categoria.CategoriaDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para remover uma categoria existente
 */
public class RemoverCategoriaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        int id = Integer.parseInt(request.getParameter("id"));
        // processamento
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        boolean sucesso = categoriaDAO.remover(id);
        // saída
        if (sucesso) {
            request.setAttribute("mensagem", "Categoria removida com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível remover a categoria");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/admin/ListarCategorias");
        rd.forward(request, response);
    }

}
