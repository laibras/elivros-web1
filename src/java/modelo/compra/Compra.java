
package modelo.compra;

/**
 *
 * @author jsrob
 */
import java.util.Date;
import java.util.List;
import modelo.usuario.Usuario;

public class Compra {
    private int id;
    private Usuario usuario;
    private Date data;
    private String status;
    private double total; 
    private List<ItemCompra> itens; // NOVO CAMPO para guardar os itens

    public List<ItemCompra> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompra> itens) {
        this.itens = itens;
    }

    
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
