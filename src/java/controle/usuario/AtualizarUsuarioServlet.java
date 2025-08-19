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
 * Classe de controle para atualizar um usuário existente
 */
public class AtualizarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        // processamento
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = usuarioDAO.atualizar(nome, cpf, endereco, email, login, senha, id);
        // saída
        if (sucesso) {
            request.setAttribute("mensagem", "Usuário atualizado com sucesso. Sua sessão foi encerrada.");
        } else {
            request.setAttribute("mensagem", "Não foi possível atualizar o usuário. Sua sessão foi encerrada.");
        }
        RequestDispatcher rd = request.getRequestDispatcher("Logout");
        rd.forward(request, response);
    }

}
