package br.com.desafio.deveficiente.mercadolivre.produto.pergunta;

import br.com.desafio.deveficiente.mercadolivre.produto.Produto;
import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * necessidades
 * A pergunta tem um título
 * Tem instante de criação
 * O usuário que fez a pergunta
 * O produto relacionado a pergunta
 * O vendedor recebe um email com a pergunta nova e o link para a página de visualização do produto(ainda vai existir)
 * o email não precisa ser de verdade. Pode ser apenas um print no console do servidor com o corpo.
 * restrições
 * O título é obrigatório
 * O produto é obrigatório
 * O usuário é obrigatório
 */
public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;

    @NotNull
    private Long produtoId;

    public String getTitulo() {
        return titulo;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public Pergunta toModel(EntityManager manager, Usuario autor) {

        Produto produto = Optional.ofNullable(manager.find(Produto.class, produtoId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao cadastrado no sistema!"));

        return new Pergunta(titulo,produto,autor);
    }
}
