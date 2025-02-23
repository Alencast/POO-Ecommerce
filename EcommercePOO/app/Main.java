package app;
import entidades.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Criando um cliente
        Cliente cliente = new Cliente(1, "João", "Rua A", "joao@email.com", "123456789");

        // Criando um produto eletrônico
        Eletronico eletronico1 = new Eletronico(101, "Smartphone", 1500.00, 10, "Samsung", "Galaxy S21", 24);

        // Criando um pedido
        Pedido pedido = new Pedido(1, new Date(), 1500.00, cliente); // Adicionando o total do pedido

        // Criando o método de pagamento (Pagamento via Pix)
        PagamentoPix pagamentoPix = new PagamentoPix("1234567890");

        // Fazendo o pedido e processando o pagamento
        cliente.fazerPedido(pedido, pagamentoPix); // Passando o pagamento como argumento

        // Exibindo detalhes do pedido
        pedido.exibirDetalhesPedido();

        // Exibindo informações do cliente
        cliente.infoCliente();
    }
}
