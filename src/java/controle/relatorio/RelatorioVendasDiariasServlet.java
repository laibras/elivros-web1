package controle.relatorio;

import java.awt.Color;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.compra.CompraDAO;
import modelo.relatorio.RelatorioVendas;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

public class RelatorioVendasDiariasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date dataInicio = Date.valueOf(request.getParameter("dataInicio"));
        Date dataFim = Date.valueOf(request.getParameter("dataFim"));
        
        CompraDAO compraDAO = new CompraDAO();
        List<RelatorioVendas> dados = compraDAO.getRelatorioVendasDiarias(dataInicio, dataFim);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"relatorio_vendas_diarias.pdf\"");

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                
                // --- Configurações da Página e Tabela ---
                float margin = 50;
                float yStart = page.getMediaBox().getHeight() - margin;
                float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
                float yPosition = yStart;
                float rowHeight = 20f;

                // --- Título Dinâmico do Relatório ---
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String periodo = sdf.format(dataInicio) + " a " + sdf.format(dataFim);
                
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Relatório de Vendas Diárias");
                contentStream.endText();
                yPosition -= 15;
                
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Período: " + periodo);
                contentStream.endText();
                yPosition -= 30;

                // --- Cabeçalho da Tabela ---
                float tableYstart = yPosition;
                float nextTextX = margin;
                float colWidthData = tableWidth * 0.50f;
                float colWidthValor = tableWidth * 0.50f;

                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
                contentStream.addRect(margin, yPosition - rowHeight, tableWidth, rowHeight);
                contentStream.fill();
                contentStream.setNonStrokingColor(Color.BLACK);

                yPosition -= 15;
                contentStream.beginText();
                contentStream.newLineAtOffset(nextTextX + 5, yPosition);
                contentStream.showText("Data");
                contentStream.endText();
                nextTextX += colWidthData;
                
                contentStream.beginText();
                contentStream.newLineAtOffset(nextTextX + 5, yPosition);
                contentStream.showText("Valor Total Recebido");
                contentStream.endText();
                
                // --- Dados da Tabela ---
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                
                for (RelatorioVendas item : dados) {
                    yPosition -= rowHeight;
                    nextTextX = margin;

                    contentStream.beginText();
                    contentStream.newLineAtOffset(nextTextX + 5, yPosition);
                    contentStream.showText(sdf.format(item.getData()));
                    contentStream.endText();
                    nextTextX += colWidthData;

                    contentStream.beginText();
                    contentStream.newLineAtOffset(nextTextX + 5, yPosition);
                    contentStream.showText("R$ " + String.format("%.2f", item.getTotalVendido()));
                    contentStream.endText();
                }

                // Desenha as linhas da tabela
                contentStream.setStrokingColor(Color.DARK_GRAY);
                contentStream.addRect(margin, yPosition, tableWidth, tableYstart - yPosition);
                contentStream.stroke();
            }
            document.save(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar o relatório PDF.");
        }
    }
}