package controle.categoria;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.categoria.Categoria;
import modelo.categoria.CategoriaDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para preparar o formulário para inserir uma nova categoria
 * ou atualizar uma categoria existente
 */
public class FormCategoriaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("id") != null && request.getParameter("id").trim().length() != 0) {
            int id = Integer.parseInt(request.getParameter("id"));
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            Categoria categoria = categoriaDAO.listar(id);
            if (categoria != null) {
                request.setAttribute("categoria", categoria);
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/categoria/form.jsp");
        rd.forward(request, response);
    }

}
