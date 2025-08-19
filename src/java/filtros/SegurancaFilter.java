/**
 *
 * @author Julião Chaves
 */
package filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.usuario.Usuario;

public class SegurancaFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        HttpSession session = req.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        boolean isSecure = uri.contains("/secure/");
        boolean isAdmin = uri.contains("/admin/");

        boolean autorizado = false;

        if (isAdmin && usuario != null && usuario.isAdministrador()) {
            autorizado = true;
        } else if (isSecure && usuario != null) {
            autorizado = true;
        } else if (!isSecure && !isAdmin) {
            autorizado = true;
        }

        if (autorizado) {
            chain.doFilter(request, response);
        } else {
            req.setAttribute("mensagem", "Você não tem permissão para acessar o recurso");
            req.getRequestDispatcher("/Inicio").forward(req, res);
        }
    }
}
