package app;

import entidades.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando alguns produtos eletrônicos
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(new Eletronico(1, "Fone", 1500.00, 10, "Samsung", "Galaxy S21", 24));
        produtos.add(new Eletronico(2, "Notebook", 3000.00, 5, "Dell", "Inspiron 15", 12));
        produtos.add(new Eletronico(3, "Tablet", 1200.00, 7, "Apple", "iPad Air", 24));

        System.out.println("=======================================");
        System.out.println(" BEM-VINDO(a) A NOSSA LOJA MAGAZINE LUCENA  ");
        System.out.println("=======================================");
        System.out.print("Você é Cliente ou Administrador? (C/A): ");
        char tipoUsuario = scanner.next().toUpperCase().charAt(0);
        scanner.nextLine(); // Consumir nova linha

        if (tipoUsuario == 'C') {
            System.out.print("Digite seu nome: ");
            String nomeCliente = scanner.nextLine();
            System.out.print("Digite o valor disponível: R$ ");
            double saldoCliente = scanner.nextDouble();
            scanner.nextLine(); // Consumir nova linha

            Cliente cliente = new Cliente(1, nomeCliente, "Endereço não especificado", "email@email.com", "000000000");

            boolean continuarComprando = true;
            while (continuarComprando) {
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

                System.out.print("\nEscolha o ID do produto que você deseja comprar: ");
                int idEscolhido = scanner.nextInt();
                scanner.nextLine();

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
                    System.out.print("Quantidade desejada: ");
                    int quantidadeCompra = scanner.nextInt();
                    scanner.nextLine();

                    if (quantidadeCompra > produtoEscolhido.getQuantidadeEstoque()) {
                        System.out.println("\nQuantidade indisponível em estoque!");
                    } else if (saldoCliente >= produtoEscolhido.getPreco() * quantidadeCompra) {
                        System.out.println("\nCompra realizada com sucesso!");
                        saldoCliente -= produtoEscolhido.getPreco() * quantidadeCompra;
                        produtoEscolhido.setQuantidadeEstoque(produtoEscolhido.getQuantidadeEstoque() - quantidadeCompra);
                        System.out.println("Saldo restante: R$ " + saldoCliente);

                        Pedido pedido = new Pedido(1, new Date(), produtoEscolhido.getPreco() * quantidadeCompra, cliente);
                        PagamentoPix pagamentoPix = new PagamentoPix("1234567890");
                        cliente.fazerPedido(pedido, pagamentoPix);
                        pedido.exibirDetalhesPedido();
                    } else {
                        System.out.println("\nSaldo insuficiente para realizar a compra.");
                    }
                } else {
                    System.out.println("\nProduto não encontrado.");
                }
                System.out.println("=====================================");

                System.out.print("Deseja continuar comprando? (S/N): ");
                char resposta = scanner.next().toUpperCase().charAt(0);
                scanner.nextLine(); // Consumir nova linha
                if (resposta != 'S') {
                    continuarComprando = false;
                }
            }
        } else if (tipoUsuario == 'A') {
            boolean continuar = true;
            while (continuar) {
                System.out.println("\n=====================================");
                System.out.println("           MENU ADMINISTRATIVO      ");
                System.out.println("=====================================");
                System.out.println("1. Adicionar Produto");
                System.out.println("2. Remover Produto");
                System.out.println("3. Listar Produtos");
                System.out.println("4. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o nome do produto: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite o preço do produto: R$ ");
                        double preco = scanner.nextDouble();
                        System.out.print("Digite a quantidade em estoque: ");
                        int quantidade = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite a marca: ");
                        String marca = scanner.nextLine();
                        System.out.print("Digite o modelo: ");
                        String modelo = scanner.nextLine();
                        System.out.print("Digite o tempo de garantia (meses): ");
                        int garantia = scanner.nextInt();
                        scanner.nextLine();
                        int novoId = produtos.size() + 1;
                        produtos.add(new Eletronico(novoId, nome, preco, quantidade, marca, modelo, garantia));
                        System.out.println("Produto adicionado com sucesso!");
                        break;
                    case 2:
                        System.out.print("Digite o ID do produto a ser removido: ");
                        int idRemover = scanner.nextInt();
                        scanner.nextLine();
                        produtos.removeIf(produto -> produto.getID() == idRemover);
                        System.out.println("Produto removido com sucesso!");
                        break;
                    case 3:
                        System.out.println("\n=====================================");
                        System.out.println("           LISTA DE PRODUTOS        ");
                        System.out.println("=====================================");
                        for (Produto produto : produtos) {
                            System.out.println("ID: " + produto.getID());
                            System.out.println("Nome: " + produto.getNome());
                            System.out.println("Preço: R$" + produto.getPreco());
                            System.out.println("Quantidade em estoque: " + produto.getQuantidadeEstoque());
                            System.out.println("-------------------------------------");
                        }
                        break;
                    case 4:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } else {
            System.out.println("Opção inválida! Encerrando o programa.");
        }
        scanner.close();
    }
}
