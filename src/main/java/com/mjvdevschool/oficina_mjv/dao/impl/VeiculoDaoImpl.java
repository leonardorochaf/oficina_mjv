package com.mjvdevschool.oficina_mjv.dao.impl;

import com.mjvdevschool.oficina_mjv.dao.VeiculoDao;
import com.mjvdevschool.oficina_mjv.models.Veiculo;
import com.mjvdevschool.oficina_mjv.modelsDTO.DefeitoPecaDTO;
import com.mjvdevschool.oficina_mjv.rowmapper.DefeitoPecaRowMapper;
import com.mjvdevschool.oficina_mjv.rowmapper.VeiculoRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class VeiculoDaoImpl implements VeiculoDao {

    private final NamedParameterJdbcTemplate template;

    private final DataSource ds;

    private static final Logger LOGGER = LoggerFactory.getLogger(VeiculoDaoImpl.class);

    public VeiculoDaoImpl(NamedParameterJdbcTemplate template, DataSource ds) {
        this.template = template;
        this.ds = ds;
    }

    @Override
    public Long salvar(Veiculo veiculo) {
        LOGGER.info("VeiculoDao: Inicio metodo salvar");

        SimpleJdbcInsert insertVeiculo = new SimpleJdbcInsert(ds).usingGeneratedKeyColumns("id");
        insertVeiculo.withTableName("veiculo");

        Map<String, Object> params = new HashMap<>();

        params.put("nome", veiculo.getNome());

        Integer id = (Integer) insertVeiculo.executeAndReturnKey(params);

        LOGGER.info("VeiculoDao: Fim metodo salvar");

        return Long.valueOf(id);
    }

    @Override
    public void salvarRelacionamentoPeca(Long veiculoId, Long pecaId) {
        LOGGER.info("VeiculoDao: Inicio metodo salvarRelacionamentoPeca");

        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO peca_veiculo VALUES (:pecaId, :veiculoId)");

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("veiculoId", veiculoId);
        params.addValue("pecaId", pecaId);

        template.update(sql.toString(), params);

        LOGGER.info("VeiculoDao: Fim metodo salvarRelacionamentoPeca");
    }

    @Override
    public List<Veiculo> buscarTodos() {
        LOGGER.info("VeiculoDao: Inicio metodo buscarTodos");

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT id, nome FROM veiculo");

        LOGGER.info("VeiculoDao: Fim metodo buscarTodos");

        return  template.query(sql.toString(), new VeiculoRowMapper());
    }

    @Override
    public List<DefeitoPecaDTO> buscarDefeitosEPecasPorVeiculo(Long veiculoId) {
        LOGGER.info("VeiculoDao: Inicio metodo buscarDefeitosEPecasPorVeiculo");

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT dp.id, d.id, d.nome, p.id, p.nome " +
                "FROM defeito d JOIN defeito_peca dp ON d.id = dp.defeito_id " +
                "JOIN peca p ON dp.peca_id = p.id " +
                "JOIN peca_veiculo pv ON p.id = pv.peca_id JOIN veiculo v ON pv.veiculo_id = v.id " +
                "WHERE v.id = :veiculoId");

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("veiculoId", veiculoId);

        LOGGER.info("VeiculoDao: Fim metodo buscarDefeitosEPecasPorVeiculo");

        return template.query(sql.toString(), params, new DefeitoPecaRowMapper());
    }

    @Override
    public Optional<Veiculo> buscarPorNome(String nome) {
        LOGGER.info("VeiculoDao: Inicio metodo buscarPorNome");

        try {
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, nome FROM veiculo WHERE UPPER(nome) = UPPER(:nome)");

            MapSqlParameterSource params = new MapSqlParameterSource();

            params.addValue("nome", nome);

            LOGGER.info("VeiculoDao: Fim metodo buscarPorNome");

            return Optional.ofNullable(template.queryForObject(sql.toString(), params, new VeiculoRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            LOGGER.info("VeiculoDao: Nao foi encontrado tipo de veiculo com o nome: " + nome);
            return Optional.empty();
        }
    }
}
