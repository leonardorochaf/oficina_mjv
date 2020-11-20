package com.mjvdevschool.oficina_mjv.services;

import com.mjvdevschool.oficina_mjv.exceptions.BusinessException;
import com.mjvdevschool.oficina_mjv.models.Defeito;
import com.mjvdevschool.oficina_mjv.models.Peca;
import com.mjvdevschool.oficina_mjv.models.Veiculo;
import com.mjvdevschool.oficina_mjv.modelsDTO.DefeitoPecaDTO;

import java.util.List;

public interface VeiculoService {

    /**
     * Funcionalidade para incluir um novo {@link Veiculo} e os relacionamentos entre veiculo e {@link Peca}.
     * @param veiculo
     * @param pecaIdsRequest lista com os ids das pecas a serem relacionados com o veiculo no banco de dados
     * @return
     * @throws BusinessException Exception disparada se o nome do veiculo informado ja existir no banco de dados.
     */
    void salvar(Veiculo veiculo, List<String> pecaIdsRequest);

    /**
     * Retorna uma lista com todos os {@link Veiculo}.
     * @return a lista com os veiculos ou uma lista vazia se não existir nenhum cadastrado
     */
    List<Veiculo> buscarTodos();

    /**
     * Funcionalidade para buscar todas as {@link Peca} e {@link Defeito} que estejam relacionados com um {@link Veiculo}.
     * @param veiculoId
     * @return uma lista com todas as pecas e defeitos ou uma lista vazia se não existir nada cadastrado
     */
    List<DefeitoPecaDTO> buscarDefeitosEPecasPorVeiculo(Long veiculoId);
}
