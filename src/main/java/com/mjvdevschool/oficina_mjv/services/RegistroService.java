package com.mjvdevschool.oficina_mjv.services;

import com.mjvdevschool.oficina_mjv.exceptions.BusinessException;
import com.mjvdevschool.oficina_mjv.exceptions.ResourceNotFoundException;
import com.mjvdevschool.oficina_mjv.models.Defeito;
import com.mjvdevschool.oficina_mjv.models.Peca;
import com.mjvdevschool.oficina_mjv.models.Registro;
import com.mjvdevschool.oficina_mjv.models.Veiculo;
import com.mjvdevschool.oficina_mjv.modelsDTO.DefeitoPecaDTO;

import java.util.List;

public interface RegistroService {

    /**
     * Funcionalidade para incluir um novo {@link Registro} e os relacionamentos entre registro, {@link Peca} e {@link Defeito}.
     * @param registro
     * @param ids lista de ids que correspondem a chave primaria da tabela de muitos para muitos entre defeito e peca
     * @return
     */
    void salvar(Registro registro, List<String> ids);

    /**
     * Retorna uma lista com todos os {@link Registro}.
     * @return a lista com os registros ou uma lista vazia se não existir nenhum cadastrado
     */
    List<Registro> buscarTodos();

    /**
     * Retorna uma lista com todos os {@link Registro} com base nos parametros passados.
     * @return a lista com os registros ou uma lista vazia se nenhum registro se encaixar nos requisitos dos parametros passados
     */
    List<Registro> buscarTodos(Long veiculoId, String dataIncio, String dataFim);

    /**
     * Retorna um {@link Registro} com base no id informado.
     * @param id
     * @return
     * @throws ResourceNotFoundException Exception disparada se nao existir um registro com o id informado.
     */
    Registro buscarPorId(Long id);

    /**
     * Funcionalidade para buscar todas as {@link Peca} e {@link Defeito} que estejam relacionados com um {@link Registro}.
     * @param idRegistro
     * @return uma lista com todas as pecas e defeitos ou uma lista vazia se não existir nada cadastrado
     */
    List<DefeitoPecaDTO> buscarDefeitoEPecaPorRegistro(Long idRegistro);
}
