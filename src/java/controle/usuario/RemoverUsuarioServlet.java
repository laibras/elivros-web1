package controle.usuario;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.usuario.UsuarioDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para remover um usuário existente
 */
public class RemoverUsuarioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        int id = Integer.parseInt(request.getParameter("id"));
        // processamento
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = usuarioDAO.remover(id);
        // saída
        if (sucesso) {
            request.setAttribute("mensagem", "Usuário removido com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível remover o usuário");
        }
        RequestDispatcher rd = request.getRequestDispatcher("Logout");
        rd.forward(request, response);
    }

}
