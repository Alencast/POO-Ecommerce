package app;

import entidades.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Produto> produtos = new ArrayList<>();

        // Criando alguns produtos eletrônicos iniciais
        produtos.add(new Eletronico(1, "Smartphone", 1500.00, 10, "Samsung", "Galaxy S21", 24));
        produtos.add(new Eletronico(2, "Notebook", 3000.00, 5, "Dell", "Inspiron 15", 12));
        produtos.add(new Eletronico(3, "Tablet", 1200.00, 7, "Apple", "iPad Air", 24));

        System.out.println("=====================================");
        System.out.println("      BEM-VINDO A NOSSA LOJA    ");
        System.out.println("=====================================");
        System.out.print("Você é Cliente ou Administrador? (C/A): ");
        String tipoUsuario = scanner.nextLine().trim().toUpperCase();

        if (tipoUsuario.equals("C")) {
            System.out.print("Digite seu nome: ");
            String nomeCliente = scanner.nextLine();

            System.out.print("Digite o valor disponível: R$ ");
            double saldoCliente = scanner.nextDouble();
            scanner.nextLine();

            Cliente cliente = new Cliente(1, nomeCliente, "Endereço não especificado", "email@email.com", "000000000");

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

                if (saldoCliente >= produtoEscolhido.getPreco()) {
                    System.out.println("\nCompra realizada com sucesso!");
                    saldoCliente -= produtoEscolhido.getPreco();
                    System.out.println("Saldo restante: R$ " + saldoCliente);

                    Pedido pedido = new Pedido(1, new Date(), produtoEscolhido.getPreco(), cliente);
                    PagamentoPix pagamentoPix = new PagamentoPix("1234567890");
                    cliente.fazerPedido(pedido, pagamentoPix);
                    pedido.exibirDetalhesPedido();
                } else {
                    System.out.println("\nSaldo insuficiente para realizar a compra.");
                }
            } else {
                System.out.println("\nProduto não encontrado.");
            }
        } else if (tipoUsuario.equals("A")) {
            boolean executando = true;
            while (executando) {
                System.out.println("\n=====================================");
                System.out.println("       PAINEL DO ADMINISTRADOR       ");
                System.out.println("=====================================");
                System.out.println("1 - Adicionar Produto");
                System.out.println("2 - Remover Produto");
                System.out.println("3 - Listar Produtos");
                System.out.println("4 - Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();

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
                        System.out.print("Digite a garantia em meses: ");
                        int garantia = scanner.nextInt();

                        int novoId = produtos.size() + 1;
                        produtos.add(new Eletronico(novoId, nome, preco, quantidade, marca, modelo, garantia));
                        System.out.println("Produto adicionado com sucesso!");
                        break;
                    case 2:
                        System.out.print("Digite o ID do produto a ser removido: ");
                        int idRemover = scanner.nextInt();
                        produtos.removeIf(produto -> produto.getID() == idRemover);
                        System.out.println("Produto removido com sucesso!");
                        break;
                    case 3:
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
                        break;
                    case 4:
                        executando = false;
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            }
        } else {
            System.out.println("Opção inválida! Encerrando programa.");
        }
        scanner.close();
    }
}
