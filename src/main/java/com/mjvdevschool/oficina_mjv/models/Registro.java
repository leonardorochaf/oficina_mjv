package com.mjvdevschool.oficina_mjv.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Registro {

    private Long id;
    private String nomeCliente;
    private Date data;
    private String hora;
    private Veiculo veiculo;
}
