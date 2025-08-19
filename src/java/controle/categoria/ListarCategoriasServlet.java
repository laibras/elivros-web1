package controle.categoria;

import java.io.IOException;
import java.util.List;
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
 * Classe de controle para listar as categorias existentes
 */
public class ListarCategoriasServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String descricao = request.getParameter("descricao");
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.listar(descricao);
        request.setAttribute("categorias", categorias);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/categoria/listar.jsp");
        rd.forward(request, response);
    }

}
