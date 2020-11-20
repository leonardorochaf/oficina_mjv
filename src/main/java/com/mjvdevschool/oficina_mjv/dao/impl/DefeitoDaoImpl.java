package com.mjvdevschool.oficina_mjv.dao.impl;

import com.mjvdevschool.oficina_mjv.dao.DefeitoDao;
import com.mjvdevschool.oficina_mjv.models.Defeito;
import com.mjvdevschool.oficina_mjv.rowmapper.DefeitoRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DefeitoDaoImpl implements DefeitoDao {

    private final NamedParameterJdbcTemplate template;

    private static final Logger LOGGER = LoggerFactory.getLogger(DefeitoDaoImpl.class);

    public DefeitoDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void salvar(Defeito defeito) {
        LOGGER.info("DefeitoDao: Inicio metodo salvar");

        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO defeito (nome) VALUES (:nome)");

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("nome", defeito.getNome());

        template.update(sql.toString(), params);

        LOGGER.info("DefeitoDao: Fim metodo salvar");
    }

    @Override
    public List<Defeito> buscarTodos() {
        LOGGER.info("DefeitoDao: Inicio metodo buscarTodos");

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT id, nome FROM defeito");

        LOGGER.info("DefeitoDao: Fim metodo buscarTodos");

        return template.query(sql.toString(), new DefeitoRowMapper());
    }

    @Override
    public Optional<Defeito> buscarPorNome(String nome) {
        LOGGER.info("DefeitoDao: Inicio metodo buscarPorNome");

        StringBuilder sql = new StringBuilder();

        try {
            sql.append("SELECT id, nome FROM defeito WHERE UPPER(nome) = UPPER(:nome)");

            MapSqlParameterSource params = new MapSqlParameterSource();

            params.addValue("nome", nome);

            LOGGER.info("DefeitoDao: Fim metodo buscarPorNome");

            return Optional.ofNullable(template.queryForObject(sql.toString(), params, new DefeitoRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            LOGGER.info("DefeitoDao: Não foi encontrado o defeito com o nome: " + nome);
            return Optional.empty();
        }
    }

}
