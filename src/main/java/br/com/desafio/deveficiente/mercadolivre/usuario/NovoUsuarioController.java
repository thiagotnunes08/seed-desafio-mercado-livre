package br.com.desafio.deveficiente.mercadolivre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/usuarios")
public class NovoUsuarioController {
    @Autowired
    private final UsuarioRepository repository;

    public NovoUsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public void cadastra(@Valid @RequestBody NovoUsuarioRequest request) {

        Usuario novoUsuario = request.toModel();
        repository.save(novoUsuario);


    }

}