package controle.relatorio;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.usuario.Usuario;

public class AcessarRelatoriosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Verificação de Segurança: O usuário está logado E é um administrador?
        if (usuario != null && usuario.isAdministrador()) {
            // Se sim, encaminha para a página de relatórios
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/relatorios.jsp");
            rd.forward(request, response);
        } else {
            // Se não, nega o acesso
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acesso negado.");
        }
    }
}