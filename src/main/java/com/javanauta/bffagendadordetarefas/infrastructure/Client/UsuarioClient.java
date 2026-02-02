package com.javanauta.bffagendadordetarefas.infrastructure.Client;

import com.javanauta.bffagendadordetarefas.business.dto.in.EnderecoDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.in.LoginDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.in.TelefoneDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.in.UsuarioDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bffagendadordetarefas.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bffagendadordetarefas.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestHeader(name = "Authorization", required = false) String token, @RequestParam("email") String email);

    @PostMapping
    UsuarioDTOResponse salvarUsuario (@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String loginUsuario (@RequestBody LoginDTORequest loginDTORequestDTO);

    @DeleteMapping("/{email}") // var na URI , nome deve ser igual ao parâmetro
    void deletaUsuarioPorEmail(@PathVariable String email, @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping
    UsuarioDTOResponse atualizarDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO, @RequestHeader(name = "Authorization", required = false) String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse salvarTelefone(@RequestHeader(name = "Authorization", required = false) String token,
                                       @RequestBody TelefoneDTORequest telefoneDTO);

    @PostMapping("/endereco")
    EnderecoDTOResponse salvarEndereco(@RequestHeader(name = "Authorization", required = false) String token,
                                       @RequestBody EnderecoDTORequest enderecoDTO);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizarDadosEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                               @RequestParam("id") Long id,
                                               @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizarDadosTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                               @RequestParam("id") Long id,
                                               @RequestHeader(name = "Authorization", required = false) String token);

}




