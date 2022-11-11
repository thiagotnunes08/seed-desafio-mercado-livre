package br.com.desafio.deveficiente.mercadolivre.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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

    /**
     * Restringindo acessos de forma contextual (sensível à informação) utilizando logica sql(orm.xml)
     */
    public @interface GerenciaFuncionamento{

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('GRAVACAO') or @mercadoLivreSecutiry.gerenciaLoja(#lojaId)")
        @Retention(RetentionPolicy.RUNTIME)
        public @interface AbreOuFechaEstabelecimento {}

    }
    public @interface  Loja{
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        /**
         * @PostAuthorize = faz a checagem dps da execucao do metodo.(deve ser utilizados em metodos de idempotencia)
         * obs: ocorre antes da serializacao do json
         */
        @PostAuthorize("hasAuthority('LEITURA') or @mercadoLivreSecutiry.getUsuarioId() == returnObject.usuario.id")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeGerenciar{}

    }


}
