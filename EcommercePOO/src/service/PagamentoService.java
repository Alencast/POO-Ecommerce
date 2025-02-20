package service;
import entidades.Pagavel;

public class PagamentoService {
    public boolean processarPagamento(Pagavel pagamento, double valor) {
        System.out.println("Processando pagamento...");
        return pagamento.processarPagamento(valor); 
    }
}
