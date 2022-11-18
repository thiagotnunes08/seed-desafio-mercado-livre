package br.com.desafio.deveficiente.mercadolivre.security;

import br.com.desafio.deveficiente.mercadolivre.usuario.Usuario;
import br.com.desafio.deveficiente.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com e-mail informado"));

        return new User(usuario.getLogin(), usuario.getSenha(), Collections.emptySet());
    }



}