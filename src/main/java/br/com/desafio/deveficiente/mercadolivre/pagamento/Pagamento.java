package br.com.desafio.deveficiente.mercadolivre.pagamento;

import br.com.desafio.deveficiente.mercadolivre.compra.Compra;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Pagamento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Compra compra;

    @Column(nullable = false)
    private final UUID idTransacao = UUID.randomUUID();
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private final LocalDateTime efetuadoEm = LocalDateTime.now();

    public Pagamento(Compra compra, Status status) {
        this.compra = compra;
        this.status = status;

    }

    @Deprecated //hibernate only
    public Pagamento() {
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", compra=" + compra +
                ", idTransacao=" + idTransacao +
                ", status=" + status +
                ", efetuadoEm=" + efetuadoEm +
                '}';
    }

    public String getComprador() {
        return compra.getComprador();
    }

    public void finalizaCompra() {
        this.compra.finalizaCompra();
    }
}
