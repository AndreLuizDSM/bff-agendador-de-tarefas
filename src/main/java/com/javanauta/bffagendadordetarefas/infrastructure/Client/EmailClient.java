package com.javanauta.bffagendadordetarefas.infrastructure.Client;

import com.javanauta.bffagendadordetarefas.business.dto.out.TarefaDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    void enviarEmail(@RequestBody TarefaDTOResponse emailDTO);
}




