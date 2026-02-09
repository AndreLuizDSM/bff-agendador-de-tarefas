package com.javanauta.bffagendadordetarefas.business.dto.in;

import lombok.Builder;

@Builder
public record TelefoneDTORequest(String ddd, String numero){

}
