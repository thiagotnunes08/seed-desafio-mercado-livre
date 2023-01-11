package br.com.desafio.deveficiente.mercadolivre.nf;

import br.com.desafio.deveficiente.mercadolivre.compra.CompraRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DadosDaCompraRequest {

    @NotBlank
    private String comprador;
    @NotNull
    private Long compraId;


    public String getComprador() {
        return comprador;
    }

    public Long getCompraId() {
        return compraId;
    }

    @Override
    public String toString() {
        return "DadosDaCompraRequest{" +
                "comprador='" + comprador + '\'' +
                ", compraId=" + compraId +
                '}';
    }

    public NotaFiscal toModel(CompraRepository compraRepository) {

        var compra = compraRepository.findById(compraId).get();

        return new NotaFiscal(comprador,compra);
    }
}
