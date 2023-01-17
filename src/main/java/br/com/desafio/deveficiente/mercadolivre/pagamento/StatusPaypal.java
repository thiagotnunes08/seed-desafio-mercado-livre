package br.com.desafio.deveficiente.mercadolivre.pagamento;

public enum StatusPaypal {

    UM, ZERO;

    public Status normaliza() {

        if (this.equals(UM)) {
            return Status.CONCLUIDA;
        }
        return Status.DEU_RUIM;

    }
}
