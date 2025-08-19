package controle.compra;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.compra.Compra;
import modelo.compra.CompraDAO;
import modelo.compra.ItemCompra;
import modelo.compra.ItemCompraDAO;
import modelo.usuario.Usuario;

public class ListarMinhasComprasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        CompraDAO compraDAO = new CompraDAO();
        ItemCompraDAO itemCompraDAO = new ItemCompraDAO(); // Instancie o DAO de itens

        // 1. Busca a lista de compras do usuário
        List<Compra> minhasCompras = compraDAO.listarPorUsuario(usuario.getId());

        // 2. Para cada compra na lista, busca seus itens e anexa ao objeto Compra
        for (Compra compra : minhasCompras) {
            List<ItemCompra> itens = itemCompraDAO.listar(compra.getId());
            compra.setItens(itens);
        }

        request.setAttribute("minhasCompras", minhasCompras);
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/minhasCompras.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}