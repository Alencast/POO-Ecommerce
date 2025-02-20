package App;

import entidades.PagamentoPix;
import entidades.PagamentoCartao;
import service.PagamentoService;

public class Main {
    public static void main(String[] args) {
        PagamentoService pagamentoService = new PagamentoService();

        PagamentoPix pix = new PagamentoPix("chave-pix-exemplo");
        PagamentoCartao cartao = new PagamentoCartao("1234-5678-9012-3456", "Robson", "22/09/2025", "387");

        pagamentoService.processarPagamento(pix, 100.0);
        pagamentoService.processarPagamento(cartao, 250.0);
    }
}
