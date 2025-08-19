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
 * Classe de controle para inserir uma nova categoria ou atualizar uma categoria
 * existente
 */
public class SalvarCategoriaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        int id = -1;
        if (request.getParameter("id") != null && request.getParameter("id").trim().length() != 0) {
            id = Integer.parseInt(request.getParameter("id"));
        }
        String descricao = request.getParameter("descricao");
        // processamento
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        boolean sucesso = false;
        if (id == -1) {
            sucesso = categoriaDAO.inserir(descricao);
        } else {
            sucesso = categoriaDAO.atualizar(descricao, id);
        }
        // saída
        if (sucesso) {
            request.setAttribute("mensagem", "Categoria salva com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível salvar a categoria");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/admin/ListarCategorias?descricao=");
        rd.forward(request, response);
    }
}
