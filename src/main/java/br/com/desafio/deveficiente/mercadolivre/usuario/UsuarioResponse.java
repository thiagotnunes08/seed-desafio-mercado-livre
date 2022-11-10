package br.com.desafio.deveficiente.mercadolivre.usuario;

public class UsuarioResponse {
    private final Long id;
    private final String login;

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getUserName();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
