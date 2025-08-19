package controle.relatorio;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produto.Produto;
import modelo.produto.ProdutoDAO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListarProdutosFaltantesServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        // Usar "inline" abre o PDF no navegador; "attachment" força o download.
        response.setHeader("Content-Disposition", "inline; filename=\"relatorio_produtos_faltantes.pdf\"");

        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.listarProdutosFaltantes();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                
                // --- Configurações da Página e Tabela ---
                float margin = 50;
                float yStart = page.getMediaBox().getHeight() - margin;
                float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
                float yPosition = yStart;
                int numberOfRows = produtos.size();
                float rowHeight = 20f;
                
                // --- Título do Relatório ---
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Relatório de Produtos Faltantes no Estoque");
                contentStream.endText();
                yPosition -= 30; // Espaço após o título

                // --- Cabeçalho da Tabela ---
                float tableYstart = yPosition;
                float nextTextX = margin;
                float colWidthId = tableWidth * 0.15f;
                float colWidthTitulo = tableWidth * 0.60f;
                float colWidthPreco = tableWidth * 0.25f;

                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                
                // Desenha o fundo do cabeçalho
                contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
                contentStream.addRect(margin, yPosition - rowHeight, tableWidth, rowHeight);
                contentStream.fill();
                contentStream.setNonStrokingColor(Color.BLACK);

                // Escreve os textos do cabeçalho
                yPosition -= 15;
                contentStream.beginText();
                contentStream.newLineAtOffset(nextTextX + 5, yPosition);
                contentStream.showText("ID");
                contentStream.endText();
                nextTextX += colWidthId;
                
                contentStream.beginText();
                contentStream.newLineAtOffset(nextTextX + 5, yPosition);
                contentStream.showText("Título");
                contentStream.endText();
                nextTextX += colWidthTitulo;

                contentStream.beginText();
                contentStream.newLineAtOffset(nextTextX + 5, yPosition);
                contentStream.showText("Preço");
                contentStream.endText();

                // --- Dados da Tabela (Linhas de Produtos) ---
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                
                for (Produto p : produtos) {
                    yPosition -= rowHeight;
                    nextTextX = margin;

                    contentStream.beginText();
                    contentStream.newLineAtOffset(nextTextX + 5, yPosition);
                    contentStream.showText(String.valueOf(p.getId()));
                    contentStream.endText();
                    nextTextX += colWidthId;

                    contentStream.beginText();
                    contentStream.newLineAtOffset(nextTextX + 5, yPosition);
                    // Lógica para evitar que o texto ultrapasse a coluna
                    String titulo = p.getTitulo();
                    if (titulo.length() > 40) {
                        titulo = titulo.substring(0, 37) + "...";
                    }
                    contentStream.showText(titulo);
                    contentStream.endText();
                    nextTextX += colWidthTitulo;

                    contentStream.beginText();
                    contentStream.newLineAtOffset(nextTextX + 5, yPosition);
                    contentStream.showText("R$ " + String.format("%.2f", p.getPreco()));
                    contentStream.endText();
                }

                // Desenha as linhas da tabela
                float tableYend = yPosition;
                contentStream.setStrokingColor(Color.DARK_GRAY);
                // Linha superior
                contentStream.moveTo(margin, tableYstart);
                contentStream.lineTo(margin + tableWidth, tableYstart);
                contentStream.stroke();
                // Linha inferior
                contentStream.moveTo(margin, tableYend);
                contentStream.lineTo(margin + tableWidth, tableYend);
                contentStream.stroke();
                // Linhas verticais
                contentStream.moveTo(margin, tableYstart);
                contentStream.lineTo(margin, tableYend);
                contentStream.moveTo(margin + colWidthId, tableYstart);
                contentStream.lineTo(margin + colWidthId, tableYend);
                contentStream.moveTo(margin + colWidthId + colWidthTitulo, tableYstart);
                contentStream.lineTo(margin + colWidthId + colWidthTitulo, tableYend);
                contentStream.moveTo(margin + tableWidth, tableYstart);
                contentStream.lineTo(margin + tableWidth, tableYend);
                contentStream.stroke();

            }
            document.save(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar o relatório PDF.");
        }
    }
}