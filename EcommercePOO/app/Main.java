package app;

import entidades.*;
import java.util.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Tenta carregar os clientes já cadastrados do arquivo
        ArrayList<Cliente> clientes = new ArrayList<>(PersistenciaDados.lerClientes("clientes.txt"));
        
        //  carrega os produtos salvos no txt
        ArrayList<Produto> produtos = new ArrayList<>(PersistenciaDados.lerProdutos("produtos.txt"));
        // Se a lista estiver vazia, cria os produtos padrão
        if (produtos.isEmpty()) {
            produtos.add(new Eletronico(1, "Celular", 1500.00, 10, "Samsung", "Galaxy S21", 24));
            produtos.add(new Eletronico(2, "Notebook", 3000.00, 5, "Dell", "Inspiron 15", 12));
            produtos.add(new Eletronico(3, "Tablet", 1200.00, 7, "Apple", "iPad Air", 24));
        }

        boolean continuarPrograma = true;

        while (continuarPrograma) {
            System.out.println("=======================================");
            System.out.println(" BEM-VINDO(a) A NOSSA LOJA MAGAZINE LUCENA  ");
            System.out.println("=======================================");
            System.out.print("Você é Cliente ou Administrador? (C/A) (X para sair): ");
            char tipoUsuario = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if (tipoUsuario == 'C') {
                Cliente cliente = null;
                System.out.print("Você já possui cadastro? (S/N): ");
                char possuiCadastro = scanner.next().toUpperCase().charAt(0);
                scanner.nextLine();

                if (possuiCadastro == 'S') {
                    System.out.print("Digite seu email: ");
                    String email = scanner.nextLine();
                    // Procura o cliente na lista de clientes carregada
                    for (Cliente c : clientes) {
                        if (c.getEmail().equalsIgnoreCase(email)) {
                            cliente = c;
                            break;
                        }
                    }
                    if (cliente == null) {
                        System.out.println("Cadastro não encontrado. Será criado um novo cadastro.");
                        cliente = criarNovoCliente(scanner, clientes, email);
                    } else {
                        System.out.println("Cadastro encontrado. Bem-vindo(a) " + cliente.getNome() + "!");
                    }
                } else {
                    System.out.print("Digite seu email: ");
                    String email = scanner.nextLine();
                    cliente = criarNovoCliente(scanner, clientes, email);
                }

                double saldoCliente = 0;
                while (true) {
                    try {
                        System.out.print("Digite o valor disponível: R$ ");
                        saldoCliente = scanner.nextDouble();
                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida! Digite um número válido.");
                        scanner.nextLine();
                    }
                }

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

                    int idEscolhido = -1;
                    while (true) {
                        try {
                            System.out.print("\nEscolha o ID do produto que você deseja comprar: ");
                            idEscolhido = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida! Digite um número válido.");
                            scanner.nextLine();
                        }
                    }

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

                        int quantidadeCompra = 0;
                        while (true) {
                            try {
                                System.out.print("Quantidade desejada: ");
                                quantidadeCompra = scanner.nextInt();
                                scanner.nextLine();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Entrada inválida! Digite um número válido.");
                                scanner.nextLine();
                            }
                        }

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

                            System.out.println("\n=====================================");
                            System.out.println("          DETALHES DA COMPRA         ");
                            System.out.println("=====================================");
                            System.out.println("Produto: " + produtoEscolhido.getNome());
                            System.out.println("Marca: " + ((Eletronico) produtoEscolhido).getMarca());
                            System.out.println("Modelo: " + ((Eletronico) produtoEscolhido).getModelo());
                            System.out.println("Preço unitário: R$" + produtoEscolhido.getPreco());
                            System.out.println("Quantidade comprada: " + quantidadeCompra);
                            System.out.println("Total pago: R$" + (produtoEscolhido.getPreco() * quantidadeCompra));
                            System.out.println("=====================================");
                        } else {
                            System.out.println("\nSaldo insuficiente para realizar a compra.");
                        }
                    } else {
                        System.out.println("\nProduto não encontrado.");
                    }
                    System.out.println("=====================================");

                    System.out.print("Deseja continuar comprando? (S/N): ");
                    char resposta = scanner.next().toUpperCase().charAt(0);
                    scanner.nextLine();
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
                    System.out.println("4. Voltar ao menu principal");
                    System.out.println("5. Sair do programa");

                    int opcao = -1;
                    while (true) {
                        try {
                            System.out.print("Escolha uma opção: ");
                            opcao = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida! Digite um número válido.");
                            scanner.nextLine();
                        }
                    }

                    switch (opcao) {
                        case 1:
                            System.out.print("Digite o nome do produto: ");
                            String nome = scanner.nextLine();

                            double preco = 0;
                            while (true) {
                                try {
                                    System.out.print("Digite o preço do produto: R$ ");
                                    preco = scanner.nextDouble();
                                    scanner.nextLine();
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Entrada inválida! Digite um número válido.");
                                    scanner.nextLine();
                                }
                            }

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
                        case 5:
                            continuarPrograma = false;
                            continuar = false;
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                }
            } else if (tipoUsuario == 'X') {
                continuarPrograma = false;
            } else {
                System.out.println("Opção inválida!");
            }
        }
        
        // Ao final do programa, salva os dados em arquivos TXT
        PersistenciaDados.salvarProdutos(produtos, "produtos.txt");
        PersistenciaDados.salvarClientes(clientes, "clientes.txt");

        System.out.println("Encerrando o programa...");
        scanner.close();
    }
    
   
    private static Cliente criarNovoCliente(Scanner scanner, ArrayList<Cliente> clientes, String email) {
        System.out.print("Digite seu nome: ");
        String nomeCliente = scanner.nextLine();
        System.out.print("Digite seu endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Digite seu telefone: ");
        String telefone = scanner.nextLine();
        
        Cliente novoCliente = new Cliente(clientes.size() + 1, nomeCliente, endereco, email, telefone);
        clientes.add(novoCliente);
        System.out.println("Cadastro realizado com sucesso. Bem-vindo(a) " + nomeCliente + "!");
        return novoCliente;
    }
}
