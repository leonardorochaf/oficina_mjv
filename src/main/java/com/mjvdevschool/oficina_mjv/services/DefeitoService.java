package com.mjvdevschool.oficina_mjv.services;

import com.mjvdevschool.oficina_mjv.exceptions.BusinessException;
import com.mjvdevschool.oficina_mjv.models.Defeito;

import java.util.List;

public interface DefeitoService {

    /**
     * Funcionalidade para incluir um novo {@link Defeito}.
     * @param defeito
     * @return
     * @throws BusinessException Exception disparada se o nome do defeito informado ja existir no banco de dados.
     */
    void Salvar(Defeito defeito);

    /**
     * Retorna uma lista com todos os {@link Defeito}.
     * @return a lista com os defeitos ou uma lista vazia se não existir nenhum cadastrado
     */
    List<Defeito> buscarTodos();

}
