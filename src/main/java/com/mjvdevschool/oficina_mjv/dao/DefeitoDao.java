package com.mjvdevschool.oficina_mjv.dao;

import com.mjvdevschool.oficina_mjv.models.Defeito;

import java.util.List;
import java.util.Optional;

public interface DefeitoDao {

    /**
     * Funcionalidade para incluir no banco de dados um novo {@link Defeito}.
     * @param defeito
     * @return
     */
    void salvar(Defeito defeito);

    /**
     * Retorna uma lista com todos os {@link Defeito}.
     * @return a lista com os defeitos ou uma lista vazia se não existir nenhum cadastrado
     */
    List<Defeito> buscarTodos();

    /**
     * Retorna um {@link Defeito} com base no nome informado.
     * @param nome do defeito que estamos buscando
     * @return o defeito com o nome informado ou um optional vazio se não existir defeito com o nome informado
     */
    Optional<Defeito> buscarPorNome(String nome);
}
