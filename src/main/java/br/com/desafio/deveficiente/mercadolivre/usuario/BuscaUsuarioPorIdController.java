package br.com.desafio.deveficiente.mercadolivre.usuario;

import br.com.desafio.deveficiente.mercadolivre.security.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/usuarios")
public class BuscaUsuarioPorIdController {

    @Autowired
    private final UsuarioRepository repository;

    public BuscaUsuarioPorIdController(UsuarioRepository repository) {
        this.repository = repository;
    }


    //    @PreAuthorize("hasAuthority('LEITURA')")
    //no lugar de "leitura" poderia ser o nome do dominio, pra deixar mais especifico.
    @Authorization.Leitura.PodeConsultarDados
    @GetMapping("{id}")
    public ResponseEntity<?> busca(@PathVariable Long id) {

        Usuario usuario = repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "usuário não encontrado no sistema!"));

        return ResponseEntity.ok(new UsuarioResponse(usuario));

    }
}
