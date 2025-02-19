package entidades;

public class PagamentoCartao implements Pagavel {

	private String numeroCartao;
	private String titular;
	private String validade;
	private String cvv;
	
	
	public PagamentoCartao(String numeroCartao, String titular, String validade, String cvv) {
		this.numeroCartao = numeroCartao;
		this.titular = titular;
		this.validade = validade;
		this.cvv = cvv;
	}
	
	@Override
    public boolean processarPagamento(double valor) {
        System.out.println("Processando pagamento de R$ " + valor + " via Cartão de Crédito...");
        // Simulação de validação do cartão
        if (validade.isEmpty() || cvv.length() != 3) {
            System.out.println("Pagamento recusado: Dados do cartão inválidos.");
            return false;
        }
        System.out.println("Pagamento aprovado com sucesso!");
        return true;
    }
}
