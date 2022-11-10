package br.com.desafio.deveficiente.mercadolivre.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public @interface Authorization {

    public @interface Gravacao{

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('GRAVACAO')")
        @Retention(RetentionPolicy.RUNTIME)
        public @interface PodeEditarDados {}

    }
    public @interface Leitura{

        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('LEITURA')")
        @Retention(RetentionPolicy.RUNTIME)
        public @interface PodeConsultarDados {}

    }
}
