package entidades;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Pedido {
	
	private int id;
	private List<Produto> produtos;
	private double total;
	private String status;
	private LocalDateTime dataPedido;
	
	// Construtor
    public Pedido(int id, List<Produto> produtos) {
        this.id = id;
        this.produtos = new ArrayList<>(produtos); // Copia os produtos do carrinho
        this.total = calcularTotal();
        this.status = "Em Processamento"; // Status inicial do pedido
        this.dataPedido = LocalDateTime.now();
    }
    
    private double calcularTotal() {
    	double total = 0;
    	for(Produto produto : produtos) {
    		total += produto.getPreco();
    	}
    	return total;
    }
    
    
 // Exibir detalhes do pedido
    public void exibirPedido() {
        System.out.println("Pedido #" + id);
        System.out.println("Data: " + dataPedido);
        System.out.println("Status: " + status);
        System.out.println("Produtos:");
        for (Produto produto : produtos) {
            System.out.println("- " + produto.getNome() + " | R$ " + produto.getPreco());
        }
        System.out.println("Total: R$ " + total);
    }
    
    // Métodos para manipular o status do pedido
    public void atualizarStatus(String novoStatus) {
        this.status = novoStatus;
        System.out.println("Status do pedido atualizado para: " + status);
    }

    public int getId() {
        return id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }
	

}
