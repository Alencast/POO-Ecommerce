package entidades;
import java.util.Date;

public class Pedido {

    private int id;
    private Date dataPedido;
    private double total;
    private Cliente cliente; // Relação many to one com cliente.

    public Pedido(int id, Date dataPedido, double total, Cliente cliente) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.total = total;
        this.cliente = cliente;
    }

    public void exibirDetalhesPedido() {
        System.out.println("=====================================");
        System.out.println("DETALHES DO PEDIDO");
        System.out.println("=====================================");
        System.out.println("ID do pedido: " + getId());
        System.out.println("Data do pedido: " + getDataPedido());
        System.out.println("Valor total: R$ " + getTotal());
        System.out.println("Cliente: " + getCliente().getNome());
        System.out.println("=====================================");
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
