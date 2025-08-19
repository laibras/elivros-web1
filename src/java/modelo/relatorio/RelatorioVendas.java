
package modelo.relatorio;

/**
 *
 * @author jsrob
 */
import java.util.Date;

// Esta classe armazena o resultado do relatório de vendas diárias.
public class RelatorioVendas {
    private Date data;
    private double totalVendido;

    // Getters e Setters
    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }
    public double getTotalVendido() { return totalVendido; }
    public void setTotalVendido(double totalVendido) { this.totalVendido = totalVendido; }
}