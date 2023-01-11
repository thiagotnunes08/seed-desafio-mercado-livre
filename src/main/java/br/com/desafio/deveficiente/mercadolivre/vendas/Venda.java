package br.com.desafio.deveficiente.mercadolivre.vendas;

import br.com.desafio.deveficiente.mercadolivre.compra.Compra;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;

import javax.persistence.*;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Compra compra;
    @ManyToOne(optional = false)
    private Usuario vendedor;

    public Venda(Compra compra, Usuario vendedor) {
        this.compra = compra;
        this.vendedor = vendedor;
    }

    @Deprecated // hibernate only
    public Venda() {
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", compra=" + compra +
                ", vendedor=" + vendedor +
                '}';
    }
}
