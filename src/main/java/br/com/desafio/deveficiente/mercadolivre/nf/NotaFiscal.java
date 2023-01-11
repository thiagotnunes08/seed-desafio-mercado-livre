package br.com.desafio.deveficiente.mercadolivre.nf;
import br.com.desafio.deveficiente.mercadolivre.compra.Compra;
import javax.persistence.*;

@Entity
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Compra compra;

    @Column(nullable = false)
    private String comprador;

    public NotaFiscal(String comprador, Compra compra) {
        this.compra = compra;
        this.comprador = comprador;
    }

    @Deprecated //hibernate only
    public NotaFiscal() {
    }

    @Override
    public String toString() {
        return "NotaFiscal{" +
                "id=" + id +
                ", compra=" + compra +
                ", comprador='" + comprador + '\'' +
                '}';
    }
}
