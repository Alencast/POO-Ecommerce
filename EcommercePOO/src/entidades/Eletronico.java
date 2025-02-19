package entidades;

public class Eletronico extends Produto {
    private String marca;
    private String modelo;
    private int garantiaMeses;

    public Eletronico(int id, String nome, double preco, int quantidadeEstoque, String marca, String modelo, int garantiaMeses) {
        super(id, nome, preco, quantidadeEstoque);
        this.marca = marca;
        this.modelo = modelo;
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Preço: R$ " + getPreco());
        System.out.println("Estoque: " + getQuantidadeEstoque());
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Garantia: " + garantiaMeses + " meses");
    }

    // Getters e Setters
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

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }
}
