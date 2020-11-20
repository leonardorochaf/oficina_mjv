package com.mjvdevschool.oficina_mjv.services;

import com.mjvdevschool.oficina_mjv.exceptions.BusinessException;
import com.mjvdevschool.oficina_mjv.models.Defeito;
import com.mjvdevschool.oficina_mjv.models.Peca;

import java.util.List;

public interface PecaService {

    /**
     * Funcionalidade para incluir um novo {@link Peca} e os relacionamentos entre peca e {@link Defeito}.
     * @param peca
     * @param defeitoIds lista com os ids dos defeitos a serem relacionados com a peca no banco de dados
     * @return
     * @throws BusinessException Exception disparada se o nome da peca informada ja existir no banco de dados.
     */
    void salvar(Peca peca, List<String> defeitoIds);

    /**
     * Retorna uma lista com todas as {@link Peca}.
     * @return a lista com as pecas ou uma lista vazia se não existir nenhuma cadastrada
     */
    List<Peca> buscarTodos();
}
