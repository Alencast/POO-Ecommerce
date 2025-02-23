package app;

import entidades.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando alguns produtos eletrônicos
        Eletronico eletronico1 = new Eletronico(1, "Smartphone", 1500.00, 10, "Samsung", "Galaxy S21", 24);
        Eletronico eletronico2 = new Eletronico(2, "Notebook", 3000.00, 5, "Dell", "Inspiron 15", 12);
        Eletronico eletronico3 = new Eletronico(3, "Tablet", 1200.00, 7, "Apple", "iPad Air", 24);

        // Adicionando produtos ao estoque
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(eletronico1);
        produtos.add(eletronico2);
        produtos.add(eletronico3);

        // Interação com o usuário
        System.out.println("=====================================");
        System.out.println("      BEM-VINDO A NOSSA LOJA    ");
        System.out.println("=====================================");
        System.out.print("Digite seu nome: ");
        String nomeCliente = scanner.nextLine();

        System.out.print("Digite o valor disponível: R$ ");
        double saldoCliente = scanner.nextDouble();
        scanner.nextLine(); // Consumir o caractere de nova linha

        Cliente cliente = new Cliente(1, nomeCliente, "Endereço não especificado", "email@email.com", "000000000");

        // Exibindo catálogo de produtos
        System.out.println("\n=====================================");
        System.out.println("           CATÁLOGO DE PRODUTOS      ");
        System.out.println("=====================================");
        for (Produto produto : produtos) {
            System.out.println("ID: " + produto.getID());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Preço: R$" + produto.getPreco());
            System.out.println("Quantidade em estoque: " + produto.getQuantidadeEstoque());
            System.out.println("-------------------------------------");
        }

        // Escolhendo produto para compra
        System.out.print("\nEscolha o ID do produto que você deseja comprar: ");
        int idEscolhido = scanner.nextInt();

        Produto produtoEscolhido = null;
        for (Produto produto : produtos) {
            if (produto.getID() == idEscolhido) {
                produtoEscolhido = produto;
                break;
            }
        }

        System.out.println("\n=====================================");
        if (produtoEscolhido != null) {
            System.out.println("Produto escolhido: " + produtoEscolhido.getNome());
            System.out.println("Preço: R$" + produtoEscolhido.getPreco());

            // Verificando saldo
            if (saldoCliente >= produtoEscolhido.getPreco()) {
                System.out.println("\nCompra realizada com sucesso!");
                saldoCliente -= produtoEscolhido.getPreco();
                System.out.println("Saldo restante: R$ " + saldoCliente);

                // Criando o pedido
                Pedido pedido = new Pedido(1, new Date(), produtoEscolhido.getPreco(), cliente);

                // Processando pagamento
                PagamentoPix pagamentoPix = new PagamentoPix("1234567890");
                cliente.fazerPedido(pedido, pagamentoPix);

                // Exibindo detalhes do pedido
                pedido.exibirDetalhesPedido();
            } else {
                System.out.println("\nSaldo insuficiente para realizar a compra.");
            }
        } else {
            System.out.println("\nProduto não encontrado.");
        }
        System.out.println("=====================================");

        scanner.close();
    }
}
