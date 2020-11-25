package com.mjvdevschool.oficina_mjv.modelsDTO;

import com.mjvdevschool.oficina_mjv.models.Defeito;
import com.mjvdevschool.oficina_mjv.models.Peca;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DefeitoPecaDTO {

    private Long id;

    private Defeito defeito;

    private Peca peca;
}
