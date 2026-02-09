package com.javanauta.bffagendadordetarefas.business;

import com.javanauta.bffagendadordetarefas.business.dto.in.LoginDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.out.TarefaDTOResponse;
import com.javanauta.bffagendadordetarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora() {
        String token = login(converterLoginDTO());
        log.info("login feito");

        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1); //Buscar daqui 1 hora
        LocalDateTime horaFinalUmaHora = LocalDateTime.now().plusHours(1).plusMinutes(5); // Pra cada 5 minutos,
        // fazer a notificação
        List<TarefaDTOResponse> listaTarefas = tarefaService.buscarTarefaGravadaPorPerido
                                                (horaFutura, horaFinalUmaHora, token);
        log.info("lista pega " + listaTarefas);

        for (TarefaDTOResponse lista: listaTarefas) {
            tarefaService.alterarStatus(StatusNotificacaoEnum.NOTIFICADO, lista.getId(), token);
            log.info("passado pelo email " + lista.getEmailUsuario());
            emailService.enviaEmail(lista);
        }
    }

    public String login(LoginDTORequest dto) {

        return usuarioService.loginUsuario(dto);
    }

    public LoginDTORequest converterLoginDTO() {
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
