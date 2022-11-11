package br.com.desafio.deveficiente.mercadolivre.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class NovoUsuarioControllerTest1 {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository repository;

    @Test
    @DisplayName("deve cadastrar um usuario")
    void test1() throws Exception {

        var novoUsuario = new NovoUsuarioRequest("thiago tomaz","thiago@email", "123456");

        var payload = mapper.writeValueAsString(novoUsuario);

        var request = post("/api/usuarios")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());

        assertEquals(1, repository.findAll().size());
    }

    @Test
    @DisplayName("nao deve cadastrar um usuario, pois parametros vazios")
    void test2() throws Exception {

        var novoUsuario = new NovoUsuarioRequest("","", "");

        var payload = mapper.writeValueAsString(novoUsuario);

        var request = post("/api/usuarios")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());


    }

    @Test
    @DisplayName("nao deve cadastrar um usuario, pois parametros nulos")
    void test3() throws Exception {

        var novoUsuario = new NovoUsuarioRequest("",null, null);

        var payload = mapper.writeValueAsString(novoUsuario);

        var request = post("/api/usuarios")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());


    }

    @Test
    @DisplayName("nao deve cadastrar um usuario, pois email deve estar bem formatado")
    void test4() throws Exception {

        var novoUsuario = new NovoUsuarioRequest("aaaaa","umaString", "123456");

        var payload = mapper.writeValueAsString(novoUsuario);

        var request = post("/api/usuarios")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());


    }

    @Test
    @DisplayName("nao deve cadastrar um usuario, pois senha deve ter no minimo 6 caracteres")
    void test5() throws Exception {

        var novoUsuario = new NovoUsuarioRequest("aaaaaaa","thiago@email.com", "12345");

        var payload = mapper.writeValueAsString(novoUsuario);

        var request = post("/api/usuarios")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());


    }
}