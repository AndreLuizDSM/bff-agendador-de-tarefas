package com.javanauta.bffagendadordetarefas.business.dto.out;

import lombok.Builder;

@Builder
public record TelefoneDTOResponse(Long id, String ddd, String numero){

}
