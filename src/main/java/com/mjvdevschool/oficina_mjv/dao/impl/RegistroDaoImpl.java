package com.mjvdevschool.oficina_mjv.dao.impl;

import com.mjvdevschool.oficina_mjv.dao.RegistroDao;
import com.mjvdevschool.oficina_mjv.models.Registro;
import com.mjvdevschool.oficina_mjv.rowmapper.RegistroRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RegistroDaoImpl implements RegistroDao {

    private final NamedParameterJdbcTemplate template;

    private final DataSource ds;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistroDaoImpl.class);

    public RegistroDaoImpl(NamedParameterJdbcTemplate template, DataSource ds) {
        this.template = template;
        this.ds = ds;
    }

    @Override
    public Long salvar(Registro registro) {
        LOGGER.info("RegistroDao: Inicio metodo salvar");

        SimpleJdbcInsert insertRegistro = new SimpleJdbcInsert(ds).usingGeneratedKeyColumns("id");
        insertRegistro.withTableName("registro");

        Map<String, Object> params = new HashMap<>();

        params.put("nome_cliente", registro.getNomeCliente());
        params.put("data", registro.getData());
        params.put("hora", registro.getHora());
        params.put("veiculo_id", registro.getVeiculo().getId());

        Integer id = (Integer) insertRegistro.executeAndReturnKey(params);

        LOGGER.info("RegistroDao: Fim metodo salvar");

        return Long.valueOf(id);
    }

    @Override
    public void salvarRelacionamentoPecaDefeito(Long registroId, Long idDefeitoPeca) {
        LOGGER.info("RegistroDao: Inicio metodo salvarRelacionamentoPecaDefeito");

        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO registro_peca_defeito VALUES (:registroId, :idDefeitoPeca)");

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("registroId", registroId);
        params.addValue("idDefeitoPeca", idDefeitoPeca);

        template.update(sql.toString(), params);

        LOGGER.info("RegistroDao: Fim metodo salvarRelacionamentoPecaDefeito");
    }

    @Override
    public List<Registro> buscarTodos() {
        LOGGER.info("RegistroDao: Inicio metodo buscarTodos sem parametros");

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT r.id, r.nome_cliente, r.data, r.hora, v.id, v.nome FROM registro r JOIN veiculo v ON r.veiculo_id = v.id");

        LOGGER.info("RegistroDao: Fim metodo buscarTodos sem parametros");

        return template.query(sql.toString(), new RegistroRowMapper());
    }

    @Override
    public List<Registro> buscarTodos(Long veiculoId, String dataInicio, String dataFim) {
        LOGGER.info("RegistroDao: Inicio metodo buscarTodos com parametros");

        StringBuilder sql = new StringBuilder();

        MapSqlParameterSource params = new MapSqlParameterSource();

        sql.append("SELECT r.id, r.nome_cliente, r.data, r.hora, v.id, v.nome FROM registro r JOIN veiculo v ON r.veiculo_id = v.id WHERE 1 = 1");

        if(!StringUtils.isEmpty(veiculoId)) {
            sql.append(" AND veiculo_id = :veiculo");
            params.addValue("veiculo", veiculoId);
        }

        if(!StringUtils.isEmpty(dataInicio)) {
            sql.append(" AND data >= :dataInicio");
            params.addValue("dataInicio", dataInicio);
        }

        if(!StringUtils.isEmpty(dataFim)) {
            sql.append(" AND data <= :dataFim");
            params.addValue("dataFim", dataFim);
        }

        LOGGER.info("RegistroDao: Fim metodo buscarTodos com parametros");

        return template.query(sql.toString(), params, new RegistroRowMapper());
    }
}
