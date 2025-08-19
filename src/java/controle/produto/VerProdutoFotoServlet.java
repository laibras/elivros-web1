/**
 *
 * @author Julião Chaves
 */
package controle.produto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produto.Produto;
import modelo.produto.ProdutoDAO;

public class VerProdutoFotoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.listar(id);
        if (produto.getFoto() != null && !produto.getFoto().isEmpty()) {
            ServletContext context = this.getServletContext();
            File produtoFoto = new File(produto.getFoto());
            FileInputStream in = new FileInputStream(produtoFoto);
            String mimeType = context.getMimeType(produto.getFoto());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setContentLength((int) produtoFoto.length());
            OutputStream out = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            in.close();
            out.close();
        }
    }
}
