package com.mjvdevschool.oficina_mjv.dao;

import com.mjvdevschool.oficina_mjv.models.Defeito;
import com.mjvdevschool.oficina_mjv.models.Peca;

import java.util.List;
import java.util.Optional;

public interface PecaDao {

    /**
     * Funcionalidade para incluir no banco de dados uma nova {@link Peca}.
     * @param peca
     * @return o id da peca salva no banco de dados
     */
    Long salvar(Peca peca);

    /**
     * Funcionalidade para incluir no banco de dados o relacionamento entre {@link Defeito} e {@link Peca} na tabela de muitos para muitos.
     * @param pecaId
     * @param defeitoId
     * @return
     */
    void salvarRelacionamentoDefeito(Long pecaId, Long defeitoId);

    /**
     * Retorna uma lista com todas as {@link Peca}.
     * @return a lista com as pecas ou uma lista vazia se não existir nenhuma cadastrada
     */
    List<Peca> buscarTodos();

    /**
     * Retorna uma {@link Peca} com base no nome informado.
     * @param nome da peca que estamos buscando
     * @return a peca com o nome informado ou um optional vazio se não existir peca com o nome informado
     */
    Optional<Peca> buscarPorNome(String nome);
}
