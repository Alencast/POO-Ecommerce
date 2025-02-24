package app;

import entidades.*;
import java.io.*;
import java.util.*;

public class PersistenciaDados {

    // Método para salvar a lista de produtos em um arquivo TXT
    public static void salvarProdutos(List<Produto> produtos, String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Produto produto : produtos) {
                String linha = produto.getID() + ";" 
                        + produto.getNome() + ";" 
                        + produto.getPreco() + ";" 
                        + produto.getQuantidadeEstoque();
                if (produto instanceof Eletronico) {
                    Eletronico eletronico = (Eletronico) produto;
                    linha += ";" + eletronico.getMarca() + ";" 
                            + eletronico.getModelo() + ";" 
                            + eletronico.getGarantia();
                }
                writer.write(linha);
                writer.newLine();
            }
            System.out.println("Produtos salvos com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    // Método para salvar a lista de clientes em um arquivo TXT
    public static void salvarClientes(List<Cliente> clientes, String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Cliente cliente : clientes) {
                String linha = cliente.getId() + ";" 
                        + cliente.getNome() + ";" 
                        + cliente.getEndereco() + ";" 
                        + cliente.getEmail() + ";" 
                        + cliente.getTelefone();
                writer.write(linha);
                writer.newLine();
            }
            System.out.println("Clientes salvos com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }
    
    // Método para ler a lista de clientes de um arquivo TXT
    public static List<Cliente> lerClientes(String nomeArquivo) {
        List<Cliente> clientes = new ArrayList<>();
        File file = new File(nomeArquivo);
        if (!file.exists()) {
            return clientes; // Retorna lista vazia se o arquivo não existir
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 5) {
                    int id = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    String endereco = partes[2];
                    String email = partes[3];
                    String telefone = partes[4];
                    Cliente cliente = new Cliente(id, nome, endereco, email, telefone);
                    clientes.add(cliente);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler clientes: " + e.getMessage());
        }
        return clientes;
    }
    
    // Novo método para ler a lista de produtos de um arquivo TXT
    public static List<Produto> lerProdutos(String nomeArquivo) {
        List<Produto> produtos = new ArrayList<>();
        File file = new File(nomeArquivo);
        if (!file.exists()) {
            return produtos; // Retorna lista vazia se o arquivo não existir
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                // Se o produto for Eletronico, esperamos 7 partes:
                // 0: id, 1: nome, 2: preco, 3: quantidade, 4: marca, 5: modelo, 6: garantia
                if (partes.length >= 7) {
                    int id = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    double preco = Double.parseDouble(partes[2]);
                    int quantidade = Integer.parseInt(partes[3]);
                    String marca = partes[4];
                    String modelo = partes[5];
                    int garantia = Integer.parseInt(partes[6]);
                    Produto produto = new Eletronico(id, nome, preco, quantidade, marca, modelo, garantia);
                    produtos.add(produto);
                }
                // Se tiver apenas 4 partes, pode ser outro tipo de produto (ou tratamento específico)
                else if (partes.length == 4) {
                    int id = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    double preco = Double.parseDouble(partes[2]);
                    int quantidade = Integer.parseInt(partes[3]);
                    // Cria um produto genérico ou trate conforme sua lógica
                    Produto produto = new Eletronico(id, nome, preco, quantidade, "N/D", "N/D", 0);
                    produtos.add(produto);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler produtos: " + e.getMessage());
        }
        return produtos;
    }
}
