package controle.compra; 

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.compra.ItemCarrinho;
import modelo.produto.Produto;
import modelo.produto.ProdutoDAO;

public class CarrinhoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        if (acao == null) {
            acao = "ver";
        }

        HttpSession session = request.getSession(true);
        List<ItemCarrinho> carrinho = (List<ItemCarrinho>) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }

        switch (acao) {
            case "add":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    int quantidade = Integer.parseInt(request.getParameter("quantidade"));

                    if (quantidade < 1) {
                        quantidade = 1;
                    }
                    
                    ItemCarrinho itemExistente = null;
                    for (ItemCarrinho item : carrinho) {
                        if (item.getProduto().getId() == id) {
                            itemExistente = item;
                            break;
                        }
                    }

                    if (itemExistente != null) {
                        itemExistente.setQuantidade(itemExistente.getQuantidade() + quantidade);
                    } else {
                        ProdutoDAO produtoDAO = new ProdutoDAO();
                        Produto produto = produtoDAO.listar(id);
                        if (produto != null) {
                            carrinho.add(new ItemCarrinho(produto, quantidade));
                        }
                    }
                    
                    session.setAttribute("carrinho", carrinho);
                    response.sendRedirect("carrinho?acao=ver");
                    return;

                } catch (NumberFormatException e) {
                    // Tratar erro de ID inválido
                }
                break;

            case "remover":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    carrinho.removeIf(item -> item.getProduto().getId() == id);
                    session.setAttribute("carrinho", carrinho);
                    response.sendRedirect("carrinho?acao=ver");
                    return;
                } catch (NumberFormatException e) {
                    // Tratar erro
                }
                break;
            
            case "atualizar":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    int quantidade = Integer.parseInt(request.getParameter("quantidade"));

                    // Usando Java 8+ para encontrar o item
                    carrinho.stream()
                           .filter(item -> item.getProduto().getId() == id)
                           .findFirst()
                           .ifPresent(item -> {
                               if (quantidade > 0) {
                                   item.setQuantidade(quantidade);
                               }
                           });

                    // Remove itens cuja quantidade foi zerada
                    carrinho.removeIf(item -> item.getQuantidade() <= 0);

                    session.setAttribute("carrinho", carrinho);
                    response.sendRedirect("carrinho?acao=ver");
                    return;

                } catch (NumberFormatException e) {
                    // Tratar erro
                }
                break;
            
            case "ver":
            default:
                break;
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/carrinho.jsp");
        rd.forward(request, response);
    }
}