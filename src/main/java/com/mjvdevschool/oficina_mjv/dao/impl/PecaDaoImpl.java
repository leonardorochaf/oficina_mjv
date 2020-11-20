package com.mjvdevschool.oficina_mjv.dao.impl;

import com.mjvdevschool.oficina_mjv.dao.PecaDao;
import com.mjvdevschool.oficina_mjv.models.Peca;
import com.mjvdevschool.oficina_mjv.rowmapper.PecaRowMapper;
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
public class PecaDaoImpl implements PecaDao {

    private final NamedParameterJdbcTemplate template;

    private final DataSource ds;

    private static final Logger LOGGER = LoggerFactory.getLogger(PecaDaoImpl.class);

    public PecaDaoImpl(NamedParameterJdbcTemplate template, DataSource ds) {
        this.template = template;
        this.ds = ds;
    }

    @Override
    public Long salvar(Peca peca) {
        LOGGER.info("PecaDao: Inicio metodo salvar");

        SimpleJdbcInsert insertPeca = new SimpleJdbcInsert(ds).usingGeneratedKeyColumns("id");
        insertPeca.withTableName("peca");

        Map<String, Object> params = new HashMap<>();

        params.put("nome", peca.getNome());

        Integer id = (Integer) insertPeca.executeAndReturnKey(params);

        LOGGER.info("PecaDao: Fim metodo salvar");

        return Long.valueOf(id);
    }

    @Override
    public void salvarRelacionamentoDefeito(Long pecaId, Long defeitoId) {
        LOGGER.info("PecaDao: Inicio metodo salvarComRelacionamentoDefeito");

        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO defeito_peca (defeito_id, peca_id) VALUES (:defeitoId, :pecaId)");

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("defeitoId", defeitoId);
        params.addValue("pecaId", pecaId);

        template.update(sql.toString(), params);

        LOGGER.info("PecaDao: Fim metodo salvarComRelacionamentoDefeito");
    }

    @Override
    public List<Peca> buscarTodos() {
        LOGGER.info("PecaDao: Inicio metodo buscarTodos");

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT id, nome FROM peca");

        LOGGER.info("PecaDao: Fim metodo buscarTodos");

        return template.query(sql.toString(), new PecaRowMapper());
    }

    @Override
    public Optional<Peca> buscarPorNome(String nome) {
        LOGGER.info("PecaDao: Inicio metodo buscarPorNome");

        try {
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, nome FROM peca WHERE UPPER(nome) = UPPER(:nome)");

            MapSqlParameterSource params = new MapSqlParameterSource();

            params.addValue("nome", nome);

            LOGGER.info("PecaDao: Fim metodo buscarPorNome");

            return Optional.ofNullable(template.queryForObject(sql.toString(), params, new PecaRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            LOGGER.info("PecaDao: Nao foi encontrada peca com o nome: " + nome);
            return Optional.empty();
        }
    }


}
