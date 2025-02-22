package entidades;

public abstract class Produto {
	
	//atributos
	private int id;
	private String nome;
	private double preco;
	private int quantidadeEstoque;
	
	//construtor
	public Produto(int id, String nome, double preco, int quantidadeEstoque) {
		
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	
	public abstract void exibirDetalhes();


	//getters
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
}
