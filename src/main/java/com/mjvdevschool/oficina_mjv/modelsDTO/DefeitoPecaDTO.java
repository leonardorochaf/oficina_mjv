package com.mjvdevschool.oficina_mjv.modelsDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DefeitoPecaDTO {

    private Long id;

    private Long idDefeito;

    private String nomeDefeito;

    private Long idPeca;

    private String nomePeca;
}
