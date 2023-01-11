package br.com.desafio.deveficiente.mercadolivre.vendas;

import br.com.desafio.deveficiente.mercadolivre.compra.Compra;
import br.com.desafio.deveficiente.mercadolivre.compra.CompraRepository;
import br.com.desafio.deveficiente.mercadolivre.usuario.UsuarioRepository;

import javax.validation.constraints.NotNull;

public class VendaRequest {

    @NotNull
    private Long compraId;
    @NotNull
    private Long vendedorId;


    public Long getCompraId() {
        return compraId;
    }

    public Long getVendedorId() {
        return vendedorId;
    }

    public Venda toModel(UsuarioRepository usuarioRepository, CompraRepository compraRepository) {

        var compra = compraRepository.findById(compraId).get();

        var vendedor = usuarioRepository.findById(vendedorId).get();

        return new Venda(compra,vendedor);
    }

    @Override
    public String toString() {
        return "VendaRequest{" +
                "compraId=" + compraId +
                ", vendedorId=" + vendedorId +
                '}';
    }
}
