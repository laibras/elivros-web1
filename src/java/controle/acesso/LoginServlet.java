package controle.acesso;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.usuario.Usuario;
import modelo.usuario.UsuarioDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para realizar o login de um usuário
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        // processamento
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.obter(login, senha);
        // saída
        if (usuario == null) {
            request.setAttribute("mensagem", "Login ou senha incorreta");
            RequestDispatcher rd = request.getRequestDispatcher("Inicio");
            rd.forward(request, response);
        } else {
            HttpSession sessao = request.getSession(true);
            sessao.setAttribute("usuario", usuario);
            // Grava o último login como um cookie - Início
            Cookie ultimoLogin = null;
            Cookie[] cookiesExistentes = request.getCookies();
            for (Cookie c : cookiesExistentes) {
                if (c.getName().endsWith("smde.login")) {
                    ultimoLogin = c;
                    break;
                }
            }
            if (ultimoLogin == null) {
                ultimoLogin = new Cookie("smde.login", login);
            } else {
                ultimoLogin.setValue(login);
            }
            ultimoLogin.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(ultimoLogin);
            // Grava o último login como um cookie - Fim
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/principal.jsp");
            rd.forward(request, response);
        }
    }

}
