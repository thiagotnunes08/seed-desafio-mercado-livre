package br.com.desafio.deveficiente.mercadolivre.security;

import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import br.com.desafio.deveficiente.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class JpaDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> possivelUsuario = repository.findByUserName(username);

        if (possivelUsuario.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"USUÁRIO NAO ENCONTRADO!");
        }

        Usuario usuario = possivelUsuario.get();

        return new UserAuth(usuario);
    }


}
