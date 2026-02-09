package com.javanauta.bffagendadordetarefas.controller;


import com.javanauta.bffagendadordetarefas.business.EmailService;
import com.javanauta.bffagendadordetarefas.business.dto.out.TarefaDTOResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> enviarEmail(@RequestBody TarefaDTOResponse emailDTO){
        emailService.enviaEmail(emailDTO);
            return ResponseEntity.ok().build();
    }
}
