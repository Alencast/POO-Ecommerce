package service;
import entidades.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    private List<Pedido> listaPedidos = new ArrayList<>();

    public void criarPedido(Pedido pedido) {
        listaPedidos.add(pedido);
        System.out.println("Pedido criado com sucesso!");
    }

    public void listarPedidos() {
        if (listaPedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
        } else {
            for (Pedido p : listaPedidos) {
                System.out.println("Pedido ID: " + p.getId());
                System.out.println("Total: R$" + p.getTotal());
                System.out.println("Status: " + p.getStatus());
                System.out.println("-------------------");
            }
        }
    }

    public Pedido buscarPedido(int id) {
        for (Pedido p : listaPedidos) {
            if (p.getId() == id) return p;
        }
        return null;
    }
}
