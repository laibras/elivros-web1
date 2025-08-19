package controle.produto;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produto.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para inserir um novo produto ou atualizar um produto
 * existente
 */
public class SalvarProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        int id = -1;
        if (request.getParameter("id") != null && request.getParameter("id").trim().length() != 0) {
            id = Integer.parseInt(request.getParameter("id"));
        }
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        int ano = Integer.parseInt(request.getParameter("ano"));
        double preco = Double.parseDouble(request.getParameter("preco"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));

        // processamento
        ProdutoDAO produtoDAO = new ProdutoDAO();
        boolean sucesso = false;
        if (id == -1) {
            sucesso = produtoDAO.inserir(titulo,autor,ano, preco, null, quantidade, categoriaId);
        } else {
            sucesso = produtoDAO.atualizar(titulo,autor,ano, preco, null, quantidade, categoriaId, id);
        }
        // saída
        if (sucesso) {
            request.setAttribute("mensagem", "Produto salvo com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível salvar o produto");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ListarProdutos?titulo=");
        rd.forward(request, response);
    }

}
