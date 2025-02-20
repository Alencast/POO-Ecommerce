package service;
import entidades.Produto;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoService {
    private List<Produto> itensCarrinho = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        itensCarrinho.add(produto);
        System.out.println("Produto adicionado ao carrinho: " + produto.getNome());
    }

    public void removerProduto(int id) {
        Produto produto = buscarProduto(id);
        if (produto != null) {
            itensCarrinho.remove(produto);
            System.out.println("Produto removido do carrinho.");
        } else {
            System.out.println("Produto não encontrado no carrinho.");
        }
    }

    public Produto buscarProduto(int id) {
        for (Produto p : itensCarrinho) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto p : itensCarrinho) {
            total += p.getPreco();
        }
        return total;
    }

    public void listarCarrinho() {
        if (itensCarrinho.isEmpty()) {
            System.out.println("O carrinho está vazio.");
        } else {
            for (Produto p : itensCarrinho) {
                p.exibirDetalhes();
                System.out.println("-------------------");
            }
        }
    }
}
