package br.com.desafio.deveficiente.mercadolivre.pagamento;

public enum StatusPagSeguro {

    SUCESSO,ERRO;

    public Status normaliza() {

        if (this.equals(ERRO)){
            return Status.DEU_RUIM;
        }
        return Status.CONCLUIDA;
    }
}
