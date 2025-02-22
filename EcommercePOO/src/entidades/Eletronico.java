package entidades;

public class Eletronico extends Produto {

	// atributos
	private String marca;
	private String modelo;
	private int garantia;

	// construtor
	public Eletronico(int id, String nome, double preco, int quantidadeEstoque, String marca, String modelo,
			int garantia) {
		super(id, nome, preco, quantidadeEstoque);

		this.marca = marca;
		this.modelo = modelo;
		this.garantia = garantia;

	}

	@Override
	public void exibirDetalhes() {

		System.out.println("Nome do produto: " + getNome());
		System.out.println("Valor do produto: " + "R$" + getPreco());
		System.out.println("Quantidade em estoque: " + getQuantidadeEstoque());
		System.out.println("Marca do produto: " + getMarca());
		System.out.println("Modelo do produto: " + getModelo());
		System.out.println("Tempo de garantia: " + getGarantia() + "Meses");

	}

	// getters e setters
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getGarantia() {
		return garantia;
	}

	public void setGarantia(int garantia) {
		this.garantia = garantia;
	}

}
