package entidades;

public class PagamentoPix implements Pagavel {

    private String chavePix;

    public PagamentoPix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public boolean processarPagamento(double valor) {
        System.out.println("=====================================");
        System.out.println("PROCESSANDO PAGAMENTO VIA PIX");
        System.out.println("=====================================");
        System.out.println("Gerando QR Code para pagamento no valor de R$ " + valor + "...");
        System.out.println("Pagamento concluído com sucesso via Pix!");
        System.out.println("=====================================");
        return true;
    }
}
