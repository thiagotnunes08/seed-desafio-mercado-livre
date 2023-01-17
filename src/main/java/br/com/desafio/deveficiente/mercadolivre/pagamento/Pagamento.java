package br.com.desafio.deveficiente.mercadolivre.pagamento;
import br.com.desafio.deveficiente.mercadolivre.compra.Compra;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Compra compra;
    @Column(nullable = false)
    private String idTransacao;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private final LocalDateTime efetuadoEm = LocalDateTime.now();

    public Pagamento(Compra compra, String idTransacao, Status status) {
        this.compra = compra;
        this.idTransacao = idTransacao;
        this.status = status;

    }

    @Deprecated //hibernate only
    public Pagamento() {
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", idTransacao=" + idTransacao +
                ", status=" + status +
                ", efetuadoEm=" + efetuadoEm +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pagamento)) return false;
        Pagamento pagamento = (Pagamento) o;
        return idTransacao.equals(pagamento.idTransacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacao);
    }


    public boolean foiConcluido(){
        return this.status.equals(Status.CONCLUIDA);
    }
}
