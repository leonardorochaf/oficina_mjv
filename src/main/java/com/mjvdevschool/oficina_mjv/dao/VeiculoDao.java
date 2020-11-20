package com.mjvdevschool.oficina_mjv.dao;

import com.mjvdevschool.oficina_mjv.models.Defeito;
import com.mjvdevschool.oficina_mjv.models.Peca;
import com.mjvdevschool.oficina_mjv.models.Veiculo;
import com.mjvdevschool.oficina_mjv.modelsDTO.DefeitoPecaDTO;

import java.util.List;
import java.util.Optional;

public interface VeiculoDao {

    /**
     * Funcionalidade para incluir no banco de dados um novo {@link Veiculo}.
     * @param veiculo
     * @return o id do veiculo salvo no banco de dados
     */
    Long salvar(Veiculo veiculo);

    /**
     * Funcionalidade para incluir no banco de dados o relacionamento entre {@link Veiculo} e {@link Peca} na tabela de muitos para muitos.
     * @param veiculoId
     * @param pecaId
     * @return
     */
    void salvarRelacionamentoPeca(Long veiculoId, Long pecaId);

    /**
     * Retorna uma lista com todos os {@link Veiculo}.
     * @return a lista com os veiculos ou uma lista vazia se não existir nenhum cadastrado
     */
    List<Veiculo> buscarTodos();

    /**
     * Funcionalidade para buscar no banco de dados todas as {@link Peca} e {@link Defeito} que estejam relacionados com um {@link Veiculo}.
     * @param veiculoId
     * @return uma lista com todas as pecas e defeitos ou uma lista vazia se não existir nada cadastrado
     */
    List<DefeitoPecaDTO> buscarDefeitosEPecasPorVeiculo(Long veiculoId);

    /**
     * Retorna um {@link Veiculo} com base no nome informado.
     * @param nome do veiculo que estamos buscando
     * @return o veiculo com o nome informado ou um optional vazio se não existir veiculo com o nome informado
     */
    Optional<Veiculo> buscarPorNome(String nome);
}
