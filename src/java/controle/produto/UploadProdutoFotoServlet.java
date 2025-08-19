/**
 *
 * @author Julião Chaves
 */

package controle.produto;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produto.ProdutoDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;


public class UploadProdutoFotoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(request)) {
            int produtoId = -1;
            FileItem foto = null;
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setRepository(new File("/home/leoomoreira/Upload/temp"));
                ServletFileUpload fileUpload = new ServletFileUpload(factory);
                List<FileItem> fileItems = fileUpload.parseRequest(new ServletRequestContext(request));
                for (int i = 0; fileItems != null && i < fileItems.size(); i++) {
                    FileItem item = fileItems.get(i);
                    if (item.isFormField() && item.getFieldName().equals("produtoId")) {
                        produtoId = Integer.parseInt(item.getString());
                        continue;
                    }
                    if (!item.isFormField() && item.getFieldName().equals("foto")) {
                        foto = item;
                    }
                }

                if (produtoId != -1 && foto != null) {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    String caminhoFoto = "/home/leoomoreira/Upload/" + produtoId + foto.getName().substring(foto.getName().lastIndexOf("."));
                    produtoDAO.atualizarFoto(caminhoFoto, produtoId);
                    foto.write(new File(caminhoFoto));
                    request.setAttribute("mensagem", "Foto transferida com sucesso");
                }
            } catch (Exception ex) {
                request.setAttribute("mensagem", ex.getMessage());
            }
        } else {
            request.setAttribute("mensagem", "Transferência de arquivo não suportada");
        }
        request.getRequestDispatcher("/admin/AtualizarProdutoFoto").forward(request, response);
    }
}
