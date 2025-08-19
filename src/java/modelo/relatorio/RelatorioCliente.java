package modelo.relatorio;

/**
 *
 * @author jsrob
 */
// Esta classe armazena o resultado do relatório de compras por cliente.
public class RelatorioCliente {

    private int clienteId;
    private String nomeCliente;
    private long totalCompras;

    // Getters e Setters
    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public long getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(long totalCompras) {
        this.totalCompras = totalCompras;
    }
}
