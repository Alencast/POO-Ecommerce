package entidades;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	
	private List<Produto> produtos;
	
	public Carrinho() {
		
		this.produtos = new ArrayList<>();
	}
	
	public void adicionarProduto(Produto produto) {
		
		produtos.add(produto);
		System.out.println(produto.getNome() + " Foi adicionado ao carrinho.");
	}
	
	public void removerProduto(Produto produto) {
		
		if(produtos.remove(produto)) {
			System.out.println(produto.getNome() + " Foi removido do carrinho.");
		}
		else {
			System.out.println("Produto não encontrado no carrinho.");
		}
	}
	
	public double calcularTotal() {
		double total = 0;
		for(Produto produto : produtos) {
			total += produto.getPreco();
		}
		return total;
	}
	
	public void exibirCarrinho() {
		if(produtos.isEmpty()) {
			System.out.println("O carrinho está vazio.");
		}
		else {
			System.out.println("Produtos no carrinho: ");
			
			for(Produto produto : produtos) {
				System.out.println("- " + produto.getNome() + " | R$ " + produto.getPreco());
			}
			System.out.println("Total: R$ " + calcularTotal());
		}
	}
	
	public List<Produto> getProdutos(){
		return produtos;
	}

}
