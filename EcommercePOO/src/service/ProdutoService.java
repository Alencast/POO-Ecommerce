package service;

import entidades.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoService {
    private List<Produto> listaProdutos;

    public ProdutoService() {
        this.listaProdutos = new ArrayList<>();
    }
    
    public void cadastrarProduto(Produto produto) {
       
        listaProdutos.add(produto);
        System.out.println("Produto cadastrado com sucesso: " + produto.getNome());
    }

    public List<Produto> listarProdutos() {
        return listaProdutos;
    }

    public Produto buscarProduto(int id) {
        for (Produto p : listaProdutos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null; 
    }

    public boolean removerProduto(int id) {
        Produto produtoEncontrado = buscarProduto(id);
        if (produtoEncontrado != null) {
            listaProdutos.remove(produtoEncontrado);
            System.out.println("Produto removido com sucesso: " + produtoEncontrado.getNome());
            return true;
        }
        System.out.println("Produto não encontrado.");
        return false;
    }
}

