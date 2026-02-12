package com.javanauta.bffagendadordetarefas.business.dto.in;

import lombok.Builder;

@Builder
public record LoginDTORequest (String email, String senha){
}
