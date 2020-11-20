package com.mjvdevschool.oficina_mjv.dao;

import com.mjvdevschool.oficina_mjv.models.Defeito;
import com.mjvdevschool.oficina_mjv.models.Peca;
import com.mjvdevschool.oficina_mjv.models.Registro;

import java.util.List;

public interface RegistroDao {

    /**
     * Funcionalidade para incluir no banco de dados um novo {@link Registro}.
     * @param registro
     * @return o id do registro salvo no banco de dados
     */
    Long salvar(Registro registro);

    /**
     * Funcionalidade para incluir no banco de dados o relacionamento entre {@link Registro}, {@link Peca} e {@link Defeito} na tabela de muitos para muitos.
     * @param registroId
     * @param idDefeitoPeca id da chave primaria da tabela de muitos para muitos entre defeito e peca
     * @return
     */
    void salvarRelacionamentoPecaDefeito(Long registroId, Long idDefeitoPeca);

    /**
     * Retorna uma lista com todos os {@link Registro}.
     * @return a lista com os registros ou uma lista vazia se não existir nenhum cadastrado
     */
    List<Registro> buscarTodos();

    /**
     * Retorna uma lista com todos os {@link Registro} com base nos parametros passados.
     * @return a lista com os registros ou uma lista vazia se nenhum registro se encaixar nos requisitos dos parametros passados
     */
    List<Registro> buscarTodos(Long veiculoId, String dataInicio, String dataFim);
}
